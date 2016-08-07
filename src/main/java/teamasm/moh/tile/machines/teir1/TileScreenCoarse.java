package teamasm.moh.tile.machines.teir1;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.init.ModItems;
import teamasm.moh.item.ItemOre;
import teamasm.moh.reference.GuiIds;
import teamasm.moh.tile.TileProcessEnergy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileScreenCoarse extends TileProcessEnergy implements ITickable {

    public TileScreenCoarse() {
        setInventory(2, 64);
        cycleTimeTime = 1;//TODO Before event change back to 50
    }

    public float maxPurity = 0.25F;
    public float minPurity = 0F;
    public int allowedSize = 2;
    public int runCost = 100;
    public int SLOT_INPUT = 0;
    public int SLOT_OUTPUT = 1;
    public Map<String, Float> workCache = new HashMap<String, Float>();
    public int particleSize = 0;

    public int itemsConsumed = 0; //Will be used to add stone to the junk output slot

    //region Logic

    @Override
    public void update() {
        if (worldObj.isRemote || isIdle) {
            rotation += rotationSpeed;
            return;
        }

        if (workCache.isEmpty() && !inputValid()) {
            isIdle = true;
            sendShortToClient(0, 0);
            return;
        }
        else if (workCache.isEmpty() && inputValid()) {
            addItemsToCache();
            return;
        }
        else if (!workCache.isEmpty() && progress < cycleTimeTime) {
            double speed = getWorkSpeed();
            energyStorage.modifyEnergyStored(- (int)(speed * runCost));
            progress += speed;
        }
        else if (!workCache.isEmpty() && progress >= cycleTimeTime) {
            tryProcessOutput();
        }
    }

    protected void tryProcessOutput() {
        ItemStack output = getStackInSlot(SLOT_OUTPUT);
        ItemStack newStack = new ItemStack(ModItems.brokenOre);
        ItemOre item = (ItemOre) ModItems.brokenOre;
        item.setOres(workCache, newStack);
        item.setReduced(newStack, false);
        item.setParticleSize(newStack, particleSize);

        boolean wasSuccessful = false;

        if (output == null) {
            setInventorySlotContents(SLOT_OUTPUT, newStack);
            wasSuccessful = true;
        }
        else if (output.stackSize >= 64) {
            wasSuccessful = false;
        }
        else if (ItemStack.areItemStackTagsEqual(output, newStack) && output.getItemDamage() == newStack.getItemDamage()) {
            output.stackSize++;
            wasSuccessful = true;
        }

        if (wasSuccessful) {
            workCache.clear();
            progress = 0;
            //todo add waste (cobble) output
            //create an add ItemsStack[] to inventory with a simulate option
        }
        else {
            isIdle = true;
            sendShortToClient(0, 0);
        }
    }

    protected void addItemsToCache(){
        ItemStack stack = getStackInSlot(SLOT_INPUT);

        if (stack == null || !(stack.getItem() instanceof ItemOre)) {
            return;
        }

        ItemOre item = (ItemOre) stack.getItem();
        Map<String, Float> stackOre = item.getOres(stack);
        Map<String, Float> newCache = item.getOres(stack);

        int count = Math.min(stack.stackSize, 8);
        itemsConsumed = 0;
        particleSize = item.getParticleSize(stack);
        for (int i = 0; i < count; i++) {
            boolean shouldAdd = true;

            for (String name : stackOre.keySet()) {
                if (workCache.containsKey(name) && workCache.get(name) > maxPurity) {
                    shouldAdd = false;
                    break;
                }
                else {
                    float combine = workCache.containsKey(name) ? workCache.get(name) : 0;
                    combine += stackOre.get(name);
                    newCache.put(name, combine);
                }
            }

            if (shouldAdd) {
                workCache.putAll(newCache);
                stack.stackSize--;
                itemsConsumed++;
            }
            else {
                break;
            }
        }

        if (stack.stackSize <= 0) {
            setInventorySlotContents(SLOT_INPUT, null);
        }
        else {
            setInventorySlotContents(SLOT_INPUT, stack);
        }
    }

    protected boolean inputValid() {
        ItemStack input = getStackInSlot(SLOT_INPUT);

        if (input == null || !(input.getItem() instanceof ItemOre)){
            return false;
        }

        ItemOre item = (ItemOre)input.getItem();
        int size = item.getParticleSize(input);

//        if (!item.isReduced(input) || size != allowedSize) {
//            return false;
//        }

        if (size != allowedSize) {
            return false;
        }

        Map<String, Float> stackOre = item.getOres(input);

        boolean foundMin = false;//TODO rethinking

        for (String name : stackOre.keySet()) {
            if (stackOre.get(name) > maxPurity) {
                return false;
            }

            if (stackOre.get(name) >= minPurity) {
                foundMin = true;
            }
        }

        return foundMin;
    }

    protected double getWorkSpeed() {
        double speed = Math.min(1, energyStorage.getEnergyStored() / (double)(energyStorage.getMaxEnergyStored() / 2));
        if (Math.abs(rotationSpeed - speed) > 0.01) {
            rotationSpeed = (float)speed;
            sendShortToClient(0, (int)(rotationSpeed * 1000F));
        }
        return speed;
    }


    @Override
    public void onShortPacket(int index, int value) {
        if (index == 0) {
            rotationSpeed = value / 1000F;
        }
    }

    //endregion

    //region Save

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();

        for (String name : workCache.keySet()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("Name", name);
            tag.setFloat("Purity", workCache.get(name));
            list.appendTag(tag);
        }

        compound.setTag("WorkCache", list);
        compound.setByte("ItemsConsumed", (byte)itemsConsumed);
        compound.setByte("ParticleSize", (byte) particleSize);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        NBTTagList list = compound.getTagList("WorkCache", 10);
        workCache.clear();

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound tag = list.getCompoundTagAt(i);
            workCache.put(tag.getString("Name"), tag.getFloat("Purity"));
        }

        particleSize = compound.getByte("ParticleSize");
        itemsConsumed = compound.getByte("ItemsConsumed");
        super.readFromNBT(compound);
    }

    //endregion

    protected void inventoryChanged(){
        isIdle = false;
        if (!worldObj.isRemote) {
            getWorkSpeed();
            sendShortToClient(0, (int)(rotationSpeed * 1000F));
        }
    }

    @Override
    public IMOHRecipe checkForValidRecipe() {
        return null;
    }

    @Override
    public GuiIds getGuiID() {
        return GuiIds.SCREEN_COARSE;
    }
}
