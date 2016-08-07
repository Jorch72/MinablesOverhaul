package teamasm.moh.block;

import codechicken.lib.block.property.PropertyString;
import codechicken.lib.tile.IActiveTile;
import codechicken.lib.tile.IRotatableTile;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamasm.moh.tile.TileProcessorBase;

import static codechicken.lib.block.StateReference.FACING_HOZ;
import static teamasm.moh.reference.VariantReference.machinesList;

/**
 * Created by covers1624 on 8/6/2016.
 */
public class BlockMachine extends BaseBlock {//TODO More stuff.

    public static final PropertyString TYPE = new PropertyString("type", machinesList);
    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockMachine() {
        super(Material.ROCK);
        setHardness(2.0F);
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
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        boolean active = false;
        EnumFacing rotation = EnumFacing.NORTH;
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IActiveTile) {
            active = ((IActiveTile) tileEntity).isActive();
        }
        if (tileEntity instanceof IRotatableTile) {
            rotation = ((IRotatableTile) tileEntity).getRotation();
        }
        return state.withProperty(ACTIVE, active).withProperty(FACING_HOZ, rotation);
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
        return new BlockStateContainer(this, TYPE, FACING_HOZ, ACTIVE);
    }

    //endregion

    //region Place

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);

        EnumFacing rotation = placer.getHorizontalFacing().getOpposite();
        TileEntity tile = world.getTileEntity(pos);

        if (!world.isRemote && tile instanceof TileProcessorBase) {
            ((TileProcessorBase) tile).setRotation(rotation);
            ((TileProcessorBase) tile).updateBlock();
        }
    }

    //endregion
}
