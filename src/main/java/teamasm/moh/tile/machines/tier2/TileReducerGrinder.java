package teamasm.moh.tile.machines.tier2;

import teamasm.moh.reference.GuiIds;
import teamasm.moh.tile.machines.teir1.TileReducerCrusher;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileReducerGrinder extends TileReducerCrusher {

    public TileReducerGrinder() {
        setInventory(2, 64);
        this.maxPurity = 0.7F;
        this.minPurity = 0.34F;
        this.maxParticleSize = 10;
        this.endParticleSize = 1;
    }

    @Override
    public GuiIds getGuiID() {
        return GuiIds.REDUCER_GRINDER;
    }
}
