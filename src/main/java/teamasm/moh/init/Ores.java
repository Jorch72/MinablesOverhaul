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

        OreRegistry.INSTANCE.registerOre("oreGold", 1, minPurity, maxPurity, 1, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreIron", 1, minPurity, maxPurity, 1, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreAluminum", 1, minPurity, maxPurity, 1, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreCopper", 1, minPurity, maxPurity, 1, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreLead", 1, minPurity, maxPurity, 1, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreTin", 1, minPurity, maxPurity, 1, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreSilver", 1, minPurity, maxPurity, 1, INGOT);//TODO
        OreRegistry.INSTANCE.registerOre("oreUranium", 1, minPurity, maxPurity, 1, INGOT);//TODO

        OreRegistry.INSTANCE.registerOre("oreLapis", 1, minPurity, maxPurity, 1, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreDiamond", 1, minPurity, maxPurity, 1, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreRedstone", 1, minPurity, maxPurity, 1, DUST);//TODO
        OreRegistry.INSTANCE.registerOre("oreEmerald", 1, minPurity, maxPurity, 1, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreQuartz", 1, minPurity, maxPurity, 1, GEM);//TODO
//        OreRegistry.INSTANCE.registerOre("oreCoal", 1, minPurity, maxPurity, 1, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreRuby", 1, minPurity, maxPurity, 1, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("oreSapphire", 1, minPurity, maxPurity, 1, GEM);//TODO
        OreRegistry.INSTANCE.registerOre("orePeridot", 1, minPurity, maxPurity, 1, GEM);//TODO
    }

}
