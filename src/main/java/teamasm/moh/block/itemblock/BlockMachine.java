package teamasm.moh.block.itemblock;

import codechicken.lib.block.property.PropertyString;
import codechicken.lib.tile.IActiveTile;
import codechicken.lib.tile.IRotatableTile;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import teamasm.moh.block.BaseBlock;

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
    }

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
}
