package teamasm.moh.tile.machines.tier2;

import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.reference.GuiIds;
import teamasm.moh.tile.TileProcessEnergy;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileSeparatorElectrostatic extends TileProcessEnergy {

    public TileSeparatorElectrostatic() {
        setInventory(2, 64);
    }

    @Override
    public IMOHRecipe checkForValidRecipe() {
        return null;
    }

    @Override
    protected void addItemsToCache() {

    }

    @Override
    protected void tryProcessOutput() {

    }

    @Override
    protected boolean inputValid() {
        return false;
    }

    @Override
    public GuiIds getGuiID() {
        return GuiIds.UNKNOWN;
    }
}
