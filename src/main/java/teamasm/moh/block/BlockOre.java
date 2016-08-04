package teamasm.moh.block;

import codechicken.lib.block.BlockMultiTile;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamasm.moh.block.tileentity.TileOre;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class BlockOre extends BlockMultiTile implements ITileEntityProvider{

    public BlockOre() {
        super(Material.ROCK);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tile = worldIn.getTileEntity(pos);

        if (tile instanceof TileOre) {
            tile.markDirty();
        }

        super.onBlockAdded(worldIn, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileOre();
    }
}
