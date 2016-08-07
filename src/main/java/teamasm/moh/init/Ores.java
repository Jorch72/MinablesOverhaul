package teamasm.moh.init;

import teamasm.moh.reference.OreRegistry;

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
        OreRegistry.INSTANCE.registerOre("oreAluminum", 1, minPurity, maxPurity, 0xFF, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreCopper", 1, minPurity, maxPurity, 0xFFF78624, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreLead", 1, minPurity, maxPurity, 0xFF7788BB, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreTin", 1, minPurity, maxPurity, 0xFF9FBED5, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreSilver", 1, minPurity, maxPurity, 0xFFCDE6EE, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreUranium", 1, minPurity, maxPurity, 0xFF, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreMithril", 1, minPurity, maxPurity, 0xFF72D1F2, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreNickel", 1, minPurity, maxPurity, 0xFFD3D2B0, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("orePlatinum", 1, minPurity, maxPurity, 0xFF3CBDF8, INGOT);//TODO


        OreRegistry.INSTANCE.registerOre("oreLapis", 1, minPurity, maxPurity, 0xFF2046A5, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreDiamond", 1, minPurity, maxPurity, 0xFF92E1FA, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreRedstone", 1, minPurity, maxPurity, 0xFFB10103, DUST);//TODO
        OreRegistry.INSTANCE.registerOre("oreEmerald", 1, minPurity, maxPurity, 0xFF5ECC74, GEM);//TODO
//        OreRegistry.INSTANCE.registerOre("oreQuartz", 1, minPurity, maxPurity, 0xFF, GEM);//TODO
//        OreRegistry.INSTANCE.registerOre("oreCoal", 1, minPurity, maxPurity, 0xFF, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreRuby", 1, minPurity, maxPurity, 0xFF, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreSapphire", 1, minPurity, maxPurity, 0xFF, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("orePeridot", 1, minPurity, maxPurity, 0xFF, GEM);//TODO
    }

}
