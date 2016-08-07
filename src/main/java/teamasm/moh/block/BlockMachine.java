package teamasm.moh.block;

import codechicken.lib.block.property.PropertyString;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import teamasm.moh.MinablesOverhaul;

import java.util.List;

import static teamasm.moh.reference.VariantReference.machinesList;

/**
 * Created by covers1624 on 8/6/2016.
 */
public class BlockMachine extends BaseBlock {//TODO More stuff.

    public static final PropertyString TYPE = new PropertyString("type", machinesList);
    //public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockMachine() {
        super(Material.ROCK);
        setHardness(2.0F);
        setCreativeTab(MinablesOverhaul.MOH_TAB);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i < machinesList.size(); i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    //region Render
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }
    //endregion

    //region Blockstate
    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getBlockState().getBaseState().withProperty(TYPE, machinesList.get(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return machinesList.indexOf(String.valueOf(state.getValue(TYPE)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TYPE);
    }
    //endregion
}
