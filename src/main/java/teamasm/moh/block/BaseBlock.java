package teamasm.moh.block;

import codechicken.lib.block.BlockMultiTile;
import codechicken.lib.tile.IDisplayTickTile;
import codechicken.lib.tile.IGuiTile;
import codechicken.lib.tile.IHarvestTile;
import codechicken.lib.tile.IRotatableTile;
import codechicken.lib.util.ItemUtils;
import codechicken.lib.util.RotationUtils;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class BaseBlock extends BlockMultiTile {

    public BaseBlock(Material material) {
        super(material);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IRotatableTile) {
            IRotatableTile tile = (IRotatableTile) tileEntity;
            tile.setRotation(RotationUtils.getPlacedRotationHorizontal(placer));
        }
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return false;
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        if (te instanceof IHarvestTile) {
            IHarvestTile harvestTile = (IHarvestTile) te;
            if (harvestTile.getHarvestItems() != null) {
                for (ItemStack harvestStack : harvestTile.getHarvestItems()) {
                    if (harvestStack != null) {
                        ItemUtils.dropItem(worldIn, pos, harvestStack);
                    }
                }
            }
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        if (!playerIn.isSneaking()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof IGuiTile) {
                IGuiTile tile = (IGuiTile) tileEntity;
                tile.openGui(worldIn, pos, playerIn);
                return true;
            }
        }

        return false;
    }

    @Override
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof IDisplayTickTile) {
            IDisplayTickTile tile = (IDisplayTickTile) te;
            tile.randomDisplayTick(worldIn, pos, state, rand);
        }
    }
}
