package teamasm.moh.util;

import net.minecraft.util.EnumFacing;

/**
 * Created by covers1624 on 8/6/2016.
 * TODO MOVE TO CCL!
 */
public class RotationHelper {

    public static int sideToEntity(EnumFacing rotation) {
        switch (rotation) {
        case SOUTH:
            return 0;

        case WEST:
            return 1;

        case NORTH:
            return 2;

        default:
            return 3;
        }
    }
}
