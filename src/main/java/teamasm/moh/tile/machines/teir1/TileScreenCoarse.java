package teamasm.moh.tile.machines.teir1;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.init.ModItems;
import teamasm.moh.item.ItemOre;
import teamasm.moh.reference.GuiIds;
import teamasm.moh.tile.TileProcessEnergy;

import java.util.Map;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileScreenCoarse extends TileProcessEnergy {

    public TileScreenCoarse() {
        setInventory(2, 64);
        cycleTime = 1;//TODO Before event change back to 50
    }

    public float maxPurity = 0.25F;
    public float minPurity = 0F;
    public int allowedSize = 2;
    public int SLOT_INPUT = 0;
    public int SLOT_OUTPUT = 1;

    public int itemsConsumed = 0; //Will be used to add stone to the junk output slot

    //region Logic

    @Override
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

    @Override
    protected void addItemsToCache() {
        ItemStack input = getStackInSlot(SLOT_INPUT);

        if (input == null || !(input.getItem() instanceof ItemOre)) {
            return;
        }

        ItemOre item = (ItemOre) input.getItem();
        Map<String, Float> stackOre = item.getOres(input);
        Map<String, Float> newCache = item.getOres(input);

        int count = Math.min(input.stackSize, 8);
        itemsConsumed = 0;
        particleSize = item.getParticleSize(input);
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
                input.stackSize--;
                itemsConsumed++;
            }
            else {
                break;
            }
        }

        if (input.stackSize <= 0) {
            setInventorySlotContents(SLOT_INPUT, null);
        }
        else {
            setInventorySlotContents(SLOT_INPUT, input);
        }
    }

    @Override
    protected boolean inputValid() {
        ItemStack input = getStackInSlot(SLOT_INPUT);

        if (input == null || !(input.getItem() instanceof ItemOre)) {
            return false;
        }

        ItemOre item = (ItemOre) input.getItem();
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
        compound.setByte("ItemsConsumed", (byte) itemsConsumed);
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

    protected void inventoryChanged() {
        isIdle = false;
        if (!worldObj.isRemote) {
            getWorkSpeed();
            sendShortToClient(0, (int) (rotationSpeed * 1000F));
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
