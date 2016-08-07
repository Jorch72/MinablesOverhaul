package teamasm.moh.tile.machines.tier2;

import teamasm.moh.reference.GuiIds;
import teamasm.moh.tile.machines.teir1.TileReducerCrusher;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileReducerGrinder extends TileReducerCrusher {

    public TileReducerGrinder() {
        setInventory(2, 64);
        this.maxPurity = 1F;
        this.minPurity = 0.2F;
        this.maxParticleSize = 2;
        this.endParticleSize = 1;
    }

    @Override
    public GuiIds getGuiID() {
        return GuiIds.REDUCER_GRINDER;
    }
}
