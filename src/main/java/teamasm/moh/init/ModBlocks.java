package teamasm.moh.init;

import codechicken.lib.block.ItemBlockMultiType;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamasm.moh.block.BlockOre;
import teamasm.moh.block.BlockMachine;
import teamasm.moh.tile.machines.teir1.TileReducerCrusher;
import teamasm.moh.reference.Reference;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class ModBlocks {

    public static BlockOre blockOre;
    public static BlockMachine blockMachine;

    public static void init() {

        blockOre = new BlockOre();
        blockOre.setUnlocalizedName(Reference.MOD_PREFIX + "blockOre");
        GameRegistry.register(blockOre.setRegistryName("blockOre"));
        GameRegistry.register(new ItemBlock(blockOre).setRegistryName("blockOre"));

        blockMachine = new BlockMachine();
        GameRegistry.register(blockMachine.setRegistryName("machine"));
        GameRegistry.register(new ItemBlockMultiType(blockMachine).setRegistryName("machine"));

        blockMachine.registerSubItemAndTile(0, "reducerCrusher", TileReducerCrusher.class);

    }

}
