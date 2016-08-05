package teamasm.moh.item;

import codechicken.lib.util.ItemNBTUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import teamasm.moh.MinablesOverhaul;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brandon3055 on 6/08/2016.
 */
public class ItemOre extends Item {

    public ItemOre() {
        setCreativeTab(MinablesOverhaul.MOH_TAB);
        //setHasSubtypes(true);
        setMaxStackSize(64);
        //setMaxDamage(0);
    }

    //region Getters & Setters

    /**
     * @param stack the stack.
     * @return A map of all minerals contained within this ore and their purity.
     */
    public Map<String, Float> getOres(ItemStack stack) {
        Map<String, Float> oreMap = new HashMap<String, Float>();
        NBTTagCompound compound = stack.getTagCompound();

        if (compound == null || !compound.hasKey("Ores")) {

            return oreMap;
        }

        NBTTagList oreList = compound.getTagList("Ores", 10);
        for (int i = 0; i < oreList.tagCount(); i++) {
            oreMap.put(oreList.getCompoundTagAt(i).getString("Name"), oreList.getCompoundTagAt(i).getFloat("Purity"));
        }

        return oreMap;
    }

    /**
     * Overwrites the minerals contained within this ore with the given ore map.
     * @param ores A map of String (Ore name) to Float (Purity)
     * @param stack the stack.
     */
    public void setOres(Map<String, Float> ores, ItemStack stack) {
        NBTTagList oreList = new NBTTagList();
        for (String name : ores.keySet()) {
            NBTTagCompound oreCompound = new NBTTagCompound();
            oreCompound.setString("Name", name);
            oreCompound.setFloat("Purity", ores.get(name));
            oreList.appendTag(oreCompound);
        }

        NBTTagCompound itemCompound = ItemNBTUtils.validateTagExists(stack);
        itemCompound.setTag("Ores", oreList);
    }

    /**
     * Sets the purity for teh specified mineral and ads it to the ore if id did not previously exist.
     *
     * @param ore The ore name (Ore dictionary name)
     * @param purity the purity (A value between 0 and 1 indicating a purity between 0 and 100%)
     * @param stack the stack.
     */
    public void setOre(String ore, float purity, ItemStack stack) {
        Map<String, Float> ores = getOres(stack);
        ores.put(ore, purity);
        setOres(ores, stack);
    }

    /**
     * Note: Particles in this case do not refer to visual effects.<br>
     * But instead refer to the size of the ore chunks/fragments AKA particles.
     *
     * @param stack the stack.
     * @return the particle size for this ore stack.
     */
    public float getParticleSize(ItemStack stack) {
        return ItemNBTUtils.getFloat(stack, "ParticleSize");
    }

    /**
     * Sets the particle size for this ore stack.
     *
     * Note: Particles in this case do not refer to visual effects.<br>
     * But instead refer to the size of the ore chunks/fragments AKA particles.
     *
     * @param stack the stack.
     * @param particleSize a value between 0 and 1.
     */
    public void setParticleSize(ItemStack stack, float particleSize) {
        ItemNBTUtils.setFloat(stack, "ParticleSize", particleSize);
    }

    /**
     * @param stack the stack.
     * @return Returns true is this stack has been reduced by a crusher or grinder and therefor is ready to be screened.
     */
    public boolean isReduced(ItemStack stack) {
        return ItemNBTUtils.getBoolean(stack, "IsReduced");
    }

    /**
     * @param stack The stack.
     * @param reduced a boolean
     */
    public void setReduced(ItemStack stack, boolean reduced) {
        ItemNBTUtils.setBoolean(stack, "IsReduced", reduced);
    }

    //endregion

    //region Display

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {

    }

    //endregion
}
