package teamasm.moh.tile.machines.hand;

import codechicken.lib.inventory.InventoryUtils;
import codechicken.lib.util.ItemNBTUtils;
import codechicken.lib.vec.Vector3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import teamasm.moh.init.ModItems;
import teamasm.moh.item.ItemOre;
import teamasm.moh.reference.OreRegistry;
import teamasm.moh.tile.TileProcessorManual;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileCentrifugeManual extends TileProcessorManual {

    public TileCentrifugeManual() {
        setInventory(0, 64);
    }

    public int particleSize = 0;

    //region Logic


    @Override
    public boolean handleClick(EntityPlayer player) {
        ItemStack input = player.getHeldItemMainhand();

        if (workCache.isEmpty() && !inputValid(input)) {
            return false;
        }
        else if (workCache.isEmpty() && inputValid(input)) {

            ItemOre item = (ItemOre) input.getItem();
            particleSize = item.getParticleSize(input);
            workCache = item.getOres(input);
            input.stackSize--;

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
        List<String> ores = new ArrayList<String>();
        ores.addAll(workCache.keySet());

        for (String name : ores) {
            ItemStack stack = new ItemStack(ModItems.oreDust, 1, OreRegistry.getOreIndex(name));
            ItemNBTUtils.setFloat(stack, "Purity", workCache.get(name));
            InventoryUtils.dropItem(stack, worldObj, Vector3.fromTileCenter(this).add(0, 0.5, 0));
            workCache.remove(name);
        }

        workCache.clear();
        progress = 0;

    }

    protected boolean inputValid(ItemStack input) {
        if (input == null || !(input.getItem() instanceof ItemOre)) {
            return false;
        }

        ItemOre item = (ItemOre) input.getItem();
        int size = item.getParticleSize(input);

        if (size > 2) {
            return false;
        }

        return true;
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
}
