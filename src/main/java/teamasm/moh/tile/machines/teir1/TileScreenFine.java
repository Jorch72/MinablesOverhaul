package teamasm.moh.tile.machines.teir1;

import teamasm.moh.reference.GuiIds;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileScreenFine extends TileScreenCoarse {

    public TileScreenFine() {
        setInventory(2, 64);
        this.maxPurity = 1F;
        this.minPurity = 0.5F;
        this.maxParticleSize = 10;
        this.minParticleSize = 1;
    }

    @Override
    public GuiIds getGuiID() {
        return GuiIds.SCREEN_FINE;
    }
}
