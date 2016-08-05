package teamasm.moh.world;

import net.minecraft.world.World;

/**
 * Created by brandon3055 on 6/08/2016.
 */
public class GridPoint {
    public final int x;
    public final int z;

    public GridPoint(int gridX, int gridZ) {
        this.x = gridX;
        this.z = gridZ;
    }

    //TODO this seed gen may need to me impruved
    public long getPointSeed(World world) {
        return world.getWorldInfo().getSeed() + world.provider.getDimension() + (long) x * 23423561212L + (long) z * 23423523L;
    }

}
