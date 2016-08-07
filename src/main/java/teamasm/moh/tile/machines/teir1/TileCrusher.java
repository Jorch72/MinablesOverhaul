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
public class TileCrusher extends TileProcessEnergy {

    public TileCrusher() {
        setInventory(2, 64);
        cycleTime = 1;//TODO Before event change back to 50
    }

    public float maxPurity = 1F;
    public float minPurity = 0F;
    public int maxParticleSize = 3;
    public int endParticleSize = 2;
    public int SLOT_INPUT = 0;
    public int SLOT_OUTPUT = 1;

    //region Logic

    @Override
    protected void tryProcessOutput() {
        ItemStack output = getStackInSlot(SLOT_OUTPUT);
        ItemStack newStack = new ItemStack(ModItems.brokenOre);
        ItemOre item = (ItemOre) ModItems.brokenOre;
        item.setOres(workCache, newStack);
        item.setReduced(newStack, true);
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

        int count = 1;//Math.min(stack.stackSize, 8);TODO Clean this up. This is currently just a quick hack to make it only consume 1 item
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
                particleSize = item.getParticleSize(input) - 1;

                if (particleSize < endParticleSize) {
                    particleSize = endParticleSize;
                }

                input.stackSize--;
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

        Map<String, Float> stackOre = item.getOres(input);

        boolean maxPurity = false;

        for (String name : stackOre.keySet()) {
            if (stackOre.get(name) >= 1F) {
                maxPurity = true;
                break;
            }
        }

        if ((item.isReduced(input) && !maxPurity) || size > maxParticleSize || size <= endParticleSize) {
            return false;
        }

        boolean foundMin = false; //todo Nolonger needed as this dose not combine ore now
//
        for (String name : stackOre.keySet()) {
//            if (stackOre.get(name) > maxPurity) {
//                return false;
//            }

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
        compound.setByte("NewSize", (byte) particleSize);

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

        particleSize = compound.getByte("NewSize");
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
        return GuiIds.CRUSHER;
    }
}
