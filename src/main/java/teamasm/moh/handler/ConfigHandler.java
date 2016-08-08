package teamasm.moh.handler;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;

/**
 * Created by brandon3055 on 8/08/2016.
 */
public class ConfigHandler {

    public static int oreGridSize = 512;
    /**
     * Ore pocket size should be no more then half grid size to avoid overlap.
     */
    public static int orePocketSizeFactor = 128;
    public static int orePocketHeightFactor = 32;
    public static double chancePerPoint = 0.3F;

    public static boolean stripOres = false;

    public static void loadConfig(Configuration config) {

        try {

            config.setCategoryComment("WorldGen", "Dose world related things and stuff... Yea... not the best description but i have been awake for 25 hours at this point so... *falls asleep*");

            oreGridSize = config.get("WorldGen", "oreGridSize", oreGridSize, "Determines the spacing of the ore veins.").getInt(oreGridSize);
            orePocketSizeFactor = config.get("WorldGen", "orePocketSizeFactor", orePocketSizeFactor, "Controls the diameter of the ore veins.").getInt(orePocketSizeFactor);
            orePocketHeightFactor = config.get("WorldGen", "orePocketHeightFactor", orePocketHeightFactor, "Controls the height of the ore veins.").getInt(orePocketHeightFactor);
            chancePerPoint = config.get("WorldGen", "chancePerPoint", chancePerPoint, "Sets the chance for a vein to spawn at each grid point. 1 = 100%").getDouble(chancePerPoint);
            stripOres = config.get("WorldGen", "stripOres", stripOres, "Set to false to prevent MOH from stripping all ore from the world.").getBoolean(stripOres);

            if (config.hasChanged()) {
                config.save();
            }
        }
        catch (Exception e) {
            FMLLog.warning("Something is borked!");
            e.printStackTrace();
        }
    }

}
