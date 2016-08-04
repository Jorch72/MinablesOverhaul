package teamasm.moh.block;

import codechicken.lib.block.BlockMultiTile;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamasm.moh.tile.TileOre;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class BlockOre extends BlockMultiTile {

    public BlockOre() {
        super(Material.ROCK);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileOre();
    }
}
