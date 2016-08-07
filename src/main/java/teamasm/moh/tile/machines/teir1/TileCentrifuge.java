package teamasm.moh.tile.machines.teir1;

import codechicken.lib.inventory.InventoryRange;
import codechicken.lib.inventory.InventoryUtils;
import codechicken.lib.util.ItemNBTUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.FMLLog;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.init.ModItems;
import teamasm.moh.item.ItemOre;
import teamasm.moh.reference.GuiIds;
import teamasm.moh.reference.OreRegistry;
import teamasm.moh.tile.TileProcessEnergy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileCentrifuge extends TileProcessEnergy implements ITickable {

    public TileCentrifuge() {
        setInventory(7, 64);
        cycleTimeTime = 1;//TODO Before event change back to 50
    }

    public int runCost = 100;
    public int SLOT_INPUT = 0;
    //public int SLOT_OUTPUT = 1;
    public Map<String, Float> workCache = new HashMap<String, Float>();
    public int particleSize = 0;

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
        List<String> ores = new ArrayList<String>();
        ores.addAll(workCache.keySet());

        for (String name : ores) {
            ItemStack stack = new ItemStack(ModItems.oreDust, 1, OreRegistry.getOreIndex(name));
            ItemNBTUtils.setFloat(stack, "Purity", workCache.get(name));

            insertOverride = true;

            if (InventoryUtils.insertItem(new InventoryRange(this, 1, 7), stack, false) == 0) {
                workCache.remove(name);
            }

            insertOverride = false;
        }

        if (workCache.size() == 0) {
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
        particleSize = item.getParticleSize(stack);
        workCache = item.getOres(stack);
        stack.stackSize--;

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

        FMLLog.info(""+size);
        if (size > 2) {
            return false;
        }

//        Map<String, Float> stackOre = item.getOres(input);
//
//        boolean foundMin = false;
//
//        for (String name : stackOre.keySet()) {
//            if (stackOre.get(name) > maxPurity) {
//                return false;
//            }
//
//            if (stackOre.get(name) >= minPurity) {
//                foundMin = true;
//            }
//        }

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
        return GuiIds.CENTRIFUGE;
    }
}
