package teamasm.moh.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamasm.moh.MinablesOverhaul;
import teamasm.moh.reference.VariantReference;

import java.util.List;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class ItemBlockOre extends ItemBlock {

    public ItemBlockOre(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
        setMaxStackSize(64);
        setCreativeTab(MinablesOverhaul.MOH_TAB);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta <= 15) {//0 is an index
            return "item." + VariantReference.normalOres[meta];
        } else if (meta > 16 && meta <= 31) {//0 is an index
            return "item." + VariantReference.miscOres[meta - 17];
        }
        return "item.UNKNOWN_ORE_META";
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
        if (super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState)) {

        }
        return false;
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < VariantReference.normalOres.length; i++) {
            subItems.add(new ItemStack(this, 1, i));
        }
        for (int i = 0; i < VariantReference.miscOres.length; i++) {
            subItems.add(new ItemStack(this, 1, i + 17));
        }
    }
}
