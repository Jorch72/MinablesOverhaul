package teamasm.moh.tile.machines.tier2;

import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.tile.TileProcessEnergy;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileReducerGrinder extends TileProcessEnergy {

    public TileReducerGrinder() {
        setInventory(2, 64);
    }

    @Override
    public IMOHRecipe checkForValidRecipe() {
        return null;
    }
}
