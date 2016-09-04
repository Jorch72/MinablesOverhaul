package teamasm.moh.init;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Level;
import teamasm.moh.reference.OreRegistry;

import java.util.Iterator;

import static teamasm.moh.util.EnumFinalProduct.*;

/**
 * Created by covers1624 on 8/6/2016.
 */
public class Ores {

    public static void init() {
        float minPurity = 0.01F;
        float maxPurity = 0.1F;

        //This is just so the above values can be actual percentages.
        minPurity /= 100F;
        maxPurity /= 100F;

        OreRegistry.INSTANCE.registerOre("oreGold", 1, minPurity, maxPurity, 0xFFFFDF6C, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreIron", 1, minPurity, maxPurity, 0xFFC5A188, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreAluminum", 1, minPurity, maxPurity, 0xFFCECECE, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreCopper", 1, minPurity, maxPurity, 0xFFF78624, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreLead", 1, minPurity, maxPurity, 0xFF7788BB, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreTin", 1, minPurity, maxPurity, 0xFF9FBED5, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreSilver", 1, minPurity, maxPurity, 0xFFCDE6EE, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreUranium", 1, minPurity, maxPurity, 0xFF6CC613, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreMithril", 1, minPurity, maxPurity, 0xFF72D1F2, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreNickel", 1, minPurity, maxPurity, 0xFFD3D2B0, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("orePlatinum", 1, minPurity, maxPurity, 0xFF3CBDF8, INGOT);//TODO


        OreRegistry.INSTANCE.registerOre("oreLapis", 1, minPurity, maxPurity, 0xFF2046A5, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreDiamond", 1, minPurity, maxPurity, 0xFF92E1FA, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreRedstone", 1, minPurity, maxPurity, 0xFFB10103, DUST);//TODO
        OreRegistry.INSTANCE.registerOre("oreEmerald", 1, minPurity, maxPurity, 0xFF5ECC74, GEM);//TODO
//        OreRegistry.INSTANCE.registerOre("oreQuartz", 1, minPurity, maxPurity, 0xFF, GEM);//TODO
//        OreRegistry.INSTANCE.registerOre("oreCoal", 1, minPurity, maxPurity, 0xFF, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreRuby", 1, minPurity, maxPurity, 0xFFAD3D3B, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreSapphire", 1, minPurity, maxPurity, 0xFF5035A6, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("orePeridot", 1, minPurity, maxPurity, 0xFF47A03A, GEM);//TODO
    }

    public static void postInit() {
        Iterator<OreRegistry.WeightedOre> i = OreRegistry.INSTANCE.weightedOres.iterator();

        while (i.hasNext()) {
            OreRegistry.WeightedOre ore = i.next();
            if (OreDictionary.getOres(ore.name).isEmpty()) {
                i.remove();
                FMLLog.log("Minables Overhaul", Level.INFO, String.format("Removing %s because there are no mods installed that use it", ore.name));
            }
        }
    }
}
