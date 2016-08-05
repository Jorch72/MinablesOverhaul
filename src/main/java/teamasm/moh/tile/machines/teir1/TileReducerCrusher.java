package teamasm.moh.tile.machines.teir1;

import codechicken.lib.tile.IGuiTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.reference.GuiIds;
import teamasm.moh.tile.TileProcessEnergy;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileReducerCrusher extends TileProcessEnergy implements IGuiTile{

    public TileReducerCrusher() {
        setInventory(2, 64);
    }

    @Override
    public IMOHRecipe checkForValidRecipe() {
        return null;
    }

    @Override
    public void openGui(World world, BlockPos pos, EntityPlayer player) {
        openGui(GuiIds.REDUCER_CRUSHER, world, pos, player);
    }
}
