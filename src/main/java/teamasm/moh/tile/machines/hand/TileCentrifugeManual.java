package teamasm.moh.tile.machines.hand;

import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.tile.TileProcessorManual;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileCentrifugeManual extends TileProcessorManual {

    public TileCentrifugeManual() {
        setInventory(2, 64);
    }

    @Override
    public IMOHRecipe checkForValidRecipe() {
        return null;
    }

    @Override
    public float getAnimRotStat(float partialTicks) {
        return 0;
    }
}
