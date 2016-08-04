package teamasm.moh.reference;

import codechicken.lib.util.ArrayUtils;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class VariantReference {

    public static final String[] normalOres = new String[] { "oreGold", "oreIron", "oreAluminum", "oreCopper", "oreLead", "oreTin", "oreSilver", "oreUranium" };
    public static final List<String> normalOresList = Lists.newArrayList(ArrayUtils.arrayToLowercase(normalOres));

    public static final String[] miscOres = new String[] { "oreLapis", "oreDiamond", "oreRedstone", "oreEmerald", "oreQuartz", "oreCoal", "oreRuby", "oreSapphire", "orePeridot" };
    public static final List<String> miscOresList = Lists.newArrayList(ArrayUtils.arrayToLowercase(miscOres));
}
