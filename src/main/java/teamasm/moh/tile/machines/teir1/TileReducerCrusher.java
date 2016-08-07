package teamasm.moh.tile.machines.teir1;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.FMLLog;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.init.ModItems;
import teamasm.moh.item.ItemOre;
import teamasm.moh.reference.GuiIds;
import teamasm.moh.tile.TileProcessEnergy;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileReducerCrusher extends TileProcessEnergy implements ITickable {

    public TileReducerCrusher() {
        setInventory(2, 64);
    }

    float maxPurity = 0.35F;
    int runCost = 100;
    int SLOT_INPUT = 0;
    int SLOT_OUTPUT = 1;
    Map<String, Float> workCache = new HashMap<String, Float>();
    int itemsConsumed = 0;

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
//        if (inputValid()) {
//            progress++;
//            if (progress == cycleTimeTime) {
//                work();
//            }
//        }
    }

    protected void tryProcessOutput() {
        ItemStack output = getStackInSlot(SLOT_OUTPUT);
        ItemStack newStack = new ItemStack(ModItems.brokenOre);
        ItemOre item = (ItemOre) ModItems.brokenOre;
        item.setOres(workCache, newStack);
        item.setReduced(newStack, true);

        boolean wasSuccessful = false;

        if (output == null) {
            setInventorySlotContents(SLOT_OUTPUT, newStack);
            wasSuccessful = true;
        }
        else if (output.stackSize >= 64) {
            wasSuccessful = false;
        }
        else if (ItemStack.areItemStackTagsEqual(output, newStack)) {
            output.stackSize++;
            wasSuccessful = true;
        }

        if (wasSuccessful) {
            workCache.clear();
            progress = 0;
            //todo add waste (cobble) output
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

        if (item.isReduced(input)) {
            return false;
        }

        Map<String, Float> stackOre = item.getOres(input);

        for (String name : stackOre.keySet()) {
            if (stackOre.get(name) > maxPurity) {
                return false;
            }
        }

        return true;
    }

    protected double getWorkSpeed() {
        double speed = Math.min(1, energyStorage.getEnergyStored() / (double)(energyStorage.getMaxEnergyStored() / 2));
        if (Math.abs(rotationSpeed - speed) > 0.01) {
            rotationSpeed = (float)speed;
            sendShortToClient(0, (int)(rotationSpeed * 1000F));
        }
        return speed;
    }

//    public void work() {
//        ItemStack wipStack = getStackInSlot(SLOT_INPUT).copy();
//        wipStack.stackSize = 1;
//        if (wipStack == null) {
//            decrStackSize(SLOT_INPUT, 1);
//        }
//        ItemOre itemOre = (ItemOre) wipStack.getItem();
//        itemOre.setReduced(wipStack, true);
//
//        Map<String, Float> ores = itemOre.getOres(getStackInSlot(SLOT_INPUT));
//        for (String name : ores.keySet()) {
//            float newValue = itemOre.getOres(getStackInSlot(SLOT_INPUT)).get(name).floatValue() + itemOre.getOres(wipStack).get(name).floatValue();
//            itemOre.modifyPurity(name, newValue, wipStack);
//        }
//        decrStackSize(SLOT_INPUT, 1);
//        progress = 0;
//
//        //output
//        if (getStackInSlot(SLOT_OUTPUT) == null && getStackInSlot(SLOT_INPUT) == null) {
//            setInventorySlotContents(SLOT_OUTPUT, wipStack);
//            wipStack = null;
//            progress = 0;
//        }
//    }

    @Override
    public void onShortPacket(int index, int value) {
        FMLLog.info("onShortPacket " + value);
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

        itemsConsumed = compound.getByte("ItemsConsumed");
        super.readFromNBT(compound);
    }

    //endregion

    //region Inventory

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        super.setInventorySlotContents(index, stack);
        isIdle = false;
        if (!worldObj.isRemote) {
            getWorkSpeed();
            sendShortToClient(0, (int)(rotationSpeed * 1000F));
        }
    }

    //endregion

    @Override
    public IMOHRecipe checkForValidRecipe() {
        return null;
    }

    @Override
    public GuiIds getGuiID() {
        return GuiIds.REDUCER_CRUSHER;
    }
}
