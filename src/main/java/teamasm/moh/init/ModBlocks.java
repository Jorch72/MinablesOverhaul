package teamasm.moh.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import teamasm.moh.block.BlockOre;
import teamasm.moh.block.itemblock.ItemBlockOre;
import teamasm.moh.reference.VariantReference;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class ModBlocks {

    public static BlockOre blockOreNormal;
    public static BlockOre blockOreMisc;

    public static void init() {
        blockOreNormal = new BlockOre(VariantReference.normalOresList);
        blockOreMisc = new BlockOre(VariantReference.miscOresList);
        GameRegistry.register(blockOreNormal.setRegistryName("oreNormal"));
        GameRegistry.register(blockOreMisc.setRegistryName("oreMisc"));

        GameRegistry.register(new ItemBlockOre(blockOreNormal).setRegistryName("oreNormal"));
    }

}
