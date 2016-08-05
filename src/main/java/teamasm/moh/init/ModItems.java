package teamasm.moh.init;

import codechicken.lib.asm.ObfMapping;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamasm.moh.item.Debugger;
import teamasm.moh.item.ItemOre;
import teamasm.moh.reference.Reference;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class ModItems {

    public static Item debugger;
    public static Item brokenOre;

    public static void init() {
        if (!ObfMapping.obfuscated) {
            debugger = new Debugger();
            GameRegistry.register(debugger.setRegistryName("debugger"));
        }

        brokenOre = new ItemOre();
        brokenOre.setUnlocalizedName(Reference.MOD_PREFIX + "brokenOre");
        GameRegistry.register(brokenOre.setRegistryName("brokenOre"));
    }

    @SideOnly(Side.CLIENT)//@Covers feel free to replace this with whatever you had in mind
    public static void initRendering() {
        ModelLoader.setCustomModelResourceLocation(brokenOre, 0, new ModelResourceLocation(Reference.MOD_PREFIX + "brokenOre"));
    }
}
