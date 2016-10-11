package teamasm.moh.init;

import codechicken.lib.asm.ObfMapping;
import codechicken.lib.model.CCOverrideBakedModel;
import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.util.ArrayUtils;
import com.google.common.collect.Lists;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamasm.moh.item.Debugger;
import teamasm.moh.item.ItemComponent;
import teamasm.moh.item.ItemOre;
import teamasm.moh.item.ItemOreDust;
import teamasm.moh.reference.OreRegistry;
import teamasm.moh.reference.Reference;

import java.util.List;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class ModItems {

    public static Item debugger;
    public static Item brokenOre;
    public static Item oreDust;
    public static Item component;

    public static void init() {
        if (!ObfMapping.obfuscated) {
            debugger = new Debugger();
            GameRegistry.register(debugger.setRegistryName("debugger"));
        }

        brokenOre = new ItemOre();
        brokenOre.setUnlocalizedName(Reference.MOD_PREFIX + "brokenOre");
        GameRegistry.register(brokenOre.setRegistryName("brokenOre"));

        oreDust = new ItemOreDust();//TODO Custom Item
        oreDust.setUnlocalizedName(Reference.MOD_PREFIX + "oreDust");
        GameRegistry.register(oreDust.setRegistryName("oreDust"));

        component = new ItemComponent();
        component.setUnlocalizedName(Reference.MOD_PREFIX + "component");
        GameRegistry.register(component.setRegistryName("component"));
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(brokenOre, 0, new ModelResourceLocation(Reference.MOD_PREFIX + "brokenOre", "broken"));
        ModelLoader.setCustomModelResourceLocation(brokenOre, 1, new ModelResourceLocation(Reference.MOD_PREFIX + "brokenOre", "crushed"));
        ModelLoader.setCustomModelResourceLocation(brokenOre, 2, new ModelResourceLocation(Reference.MOD_PREFIX + "brokenOre", "ground"));

        for (int i = 0; i < OreRegistry.INSTANCE.getOreList().size(); i++) {
            String oreName = OreRegistry.INSTANCE.getOreList().get(i);
            ModelResourceLocation location = new ModelResourceLocation(oreDust.getRegistryName(), "type=" + oreName);
            ModelLoader.setCustomModelResourceLocation(oreDust, i, location);
            ModelRegistryHelper.register(location, new CCOverrideBakedModel());
        }

        for (String name : componentList) {
            ModelLoader.setCustomModelResourceLocation(component, componentList.indexOf(name), new ModelResourceLocation(Reference.MOD_PREFIX + "components", "type=" + name));
        }
    }


    private static String[] components = new String[] {"crusherFrame", "crusherPlates", "crusherWheel", "screenFrameC", "screenFrameF", "separatorFrame", "separatorDrum", "motorHousing", "motorStator", "motor", "crank"};
    public static final List<String> componentList = Lists.newLinkedList(Lists.newArrayList(ArrayUtils.arrayToLowercase(components)));

}
