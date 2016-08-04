package teamasm.moh.init;

import codechicken.lib.block.ItemBlockMultiType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamasm.moh.block.BlockOre;
import teamasm.moh.block.tileentity.TileOre;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class ModBlocks {

    public static BlockOre blockOre = new BlockOre();

    public static void init() {
        GameRegistry.register(blockOre.setRegistryName("blockOre"));
        GameRegistry.register(new ItemBlockMultiType(blockOre).setRegistryName("blockOre"));
        blockOre.registerSubItemAndTile(0, "blockOre", TileOre.class);
    }

}
