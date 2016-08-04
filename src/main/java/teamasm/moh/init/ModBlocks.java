package teamasm.moh.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import teamasm.moh.block.BlockOre;
import teamasm.moh.block.itemblock.ItemBlockOre;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class ModBlocks {

    public static BlockOre blockOre = new BlockOre();

    public static void init() {
        GameRegistry.register(blockOre.setRegistryName("blockOre"));
        GameRegistry.register(new ItemBlockOre(blockOre).setRegistryName("blockOre"));
    }

}
