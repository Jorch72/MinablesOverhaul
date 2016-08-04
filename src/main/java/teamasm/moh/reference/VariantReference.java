package teamasm.moh.reference;

import codechicken.lib.util.ArrayUtils;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class VariantReference {


    private static final String[] normalOres = new String[]{"oreGold", "oreTin", "oreAluminum", "oreCopper", "oreLead", "oreTin", "oreSilver", "oreUranium"};
    private static final String[] miscOres = new String[]{"oreLapis", "oreDiamond", "oreRedstone", "oreEmerald", "oreQuartz", "oreCoal", "oreRuby", "oreSapphire", "orePeridot"};

    //Everything that is not block states.
    public static final String[] oreNames = org.apache.commons.lang3.ArrayUtils.addAll(normalOres, miscOres);
    //Block states
    public static final List<String> oreNamesList = Lists.newArrayList(ArrayUtils.arrayToLowercase(oreNames));
}
