package teamasm.moh.tile.machines.hand;

import net.minecraft.entity.player.EntityPlayer;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.tile.TileProcessorManual;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileManualDryer extends TileProcessorManual {

    public TileManualDryer() {
        setInventory(2, 64);
    }

    @Override
    public IMOHRecipe checkForValidRecipe() {
        return null;
    }

    @Override
    protected void tryProcessOutput() {

    }

    @Override
    public boolean handleClick(EntityPlayer player) {
        return false;
    }

    @Override
    public float getAnimRotStat(float partialTicks) {
        return 0;
    }
}
