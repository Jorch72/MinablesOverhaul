package teamasm.moh.world;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * Created by brandon3055 on 6/08/2016.
 */
public class GridPoint {
    public final int x;
    public final int z;
    private static final int NUM_X_BITS = 1 + MathHelper.calculateLogBaseTwo(MathHelper.roundUpToPowerOfTwo(30000000));
    private static final int NUM_Z_BITS = NUM_X_BITS;
    private static final int NUM_Y_BITS = 64 - NUM_X_BITS - NUM_Z_BITS;
    private static final int Y_SHIFT = 0 + NUM_Z_BITS;
    private static final int X_SHIFT = Y_SHIFT + NUM_Y_BITS;
    private static final long X_MASK = (1L << NUM_X_BITS) - 1L;
    private static final long Y_MASK = (1L << NUM_Y_BITS) - 1L;
    private static final long Z_MASK = (1L << NUM_Z_BITS) - 1L;

    public GridPoint(int gridX, int gridZ) {
        this.x = gridX;
        this.z = gridZ;
    }

    //This is just copied from BlockPos.toLong. Its nolonger useful for that purpose but that dose not batter because
    // its just a seed.
    public long getPointSeed(World world) {
        return (long)x * 341873128712L + (long)world.provider.getDimension() * 132897987541L + world.getWorldInfo().getSeed() + (long)z;
    }

}
