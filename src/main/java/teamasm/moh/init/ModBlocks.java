package teamasm.moh.init;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamasm.moh.block.BlockOre;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class ModBlocks {

    public static Block blockOre = new BlockOre().setRegistryName("blockOre");

    public static void init() {
        GameRegistry.register(blockOre);
    }

}
