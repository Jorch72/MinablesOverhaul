package teamasm.moh.tile.machines.hand;

import codechicken.lib.inventory.InventoryUtils;
import codechicken.lib.vec.Vector3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import teamasm.moh.init.ModItems;
import teamasm.moh.item.ItemOre;
import teamasm.moh.tile.TileProcessorManual;

import java.util.Map;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileCrusherManual extends TileProcessorManual {

    public TileCrusherManual() {
        setInventory(0, 64);
    }

    public float maxPurity = 1F;
    public float minPurity = 0F;
    public int maxParticleSize = 3;
    public int endParticleSize = 2;
    public int newSize = 0;

    //region Logic

    public boolean handleClick(EntityPlayer player) {
        ItemStack input = player.getHeldItemMainhand();

        if (workCache.isEmpty() && !inputValid(input)) {
            return false;
        }
        else if (workCache.isEmpty() && inputValid(input)) {

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
                    newSize = item.getParticleSize(input) - 1;

                    if (newSize < endParticleSize) {
                        newSize = endParticleSize;
                    }

                    input.stackSize--;
                }
                else {
                    break;
                }
            }

            if (input.stackSize <= 0) {
                player.setHeldItem(EnumHand.MAIN_HAND, null);
            }
            else {
                player.setHeldItem(EnumHand.MAIN_HAND, input);
            }
        }

        return true;
    }

    @Override
    protected void tryProcessOutput() {
        ItemStack newStack = new ItemStack(ModItems.brokenOre);
        ItemOre item = (ItemOre) ModItems.brokenOre;
        item.setOres(workCache, newStack);
        item.setReduced(newStack, true);
        item.setParticleSize(newStack, newSize);

        InventoryUtils.dropItem(newStack, worldObj, Vector3.fromTileCenter(this).add(0, 0.5, 0));

        workCache.clear();
        progress = 0;
    }

    protected boolean inputValid(ItemStack input) {
        if (input == null || !(input.getItem() instanceof ItemOre)){
            return false;
        }

        ItemOre item = (ItemOre)input.getItem();
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
        compound.setByte("NewSize", (byte)newSize);

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

        newSize = compound.getByte("NewSize");
        super.readFromNBT(compound);
    }

    //endregion
}
