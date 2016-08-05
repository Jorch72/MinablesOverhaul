package teamasm.moh.reference;

import codechicken.lib.colour.Colour;
import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.util.ArrayUtils;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class VariantReference {

    public static final String[] normalOres = new String[] { "oreGold", "oreIron", "oreAluminum", "oreCopper", "oreLead", "oreTin", "oreSilver", "oreUranium" };
    public static final List<String> normalOresList = Lists.newArrayList(ArrayUtils.arrayToLowercase(normalOres));

    public static final String[] miscOres = new String[] { "oreLapis", "oreDiamond", "oreRedstone", "oreEmerald", "oreQuartz", "oreCoal", "oreRuby", "oreSapphire", "orePeridot" };
    public static final List<String> miscOresList = Lists.newArrayList(ArrayUtils.arrayToLowercase(miscOres));

    public static final Map<String, Colour> colourMap = new HashMap<String, Colour>();

    static {
        colourMap.put("oreGold", new ColourRGBA(0x000000));//Example will need to work out the colours for everything later
    }
}
