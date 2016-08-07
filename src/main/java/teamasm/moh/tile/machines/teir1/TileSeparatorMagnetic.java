package teamasm.moh.tile.machines.teir1;

import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.reference.GuiIds;
import teamasm.moh.tile.TileProcessEnergy;

/**
 * Created by brandon3055 on 5/08/2016.
 */
@Deprecated //Will skip this oen for the moment.
public class TileSeparatorMagnetic extends TileProcessEnergy {

    public TileSeparatorMagnetic() {
        setInventory(2, 64);
    }

    @Override
    public IMOHRecipe checkForValidRecipe() {
        return null;
    }

    @Override
    public GuiIds getGuiID() {
        return GuiIds.UNKNOWN;
    }
}
