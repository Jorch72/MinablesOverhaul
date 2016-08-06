package teamasm.moh.init;

import codechicken.lib.block.ItemBlockMultiType;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamasm.moh.block.BlockOre;
import teamasm.moh.block.BlockMachine;
import teamasm.moh.tile.machines.teir1.*;
import teamasm.moh.reference.Reference;
import teamasm.moh.tile.machines.tier2.TileReducerGrinder;
import teamasm.moh.tile.machines.tier2.TileSeperatorElectrostatic;

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
        blockMachine.registerSubItemAndTile(1, "reducerGrinder", TileReducerGrinder.class);
        blockMachine.registerSubItemAndTile(2, "screenCoarse", TileScreenCoarse.class);
        blockMachine.registerSubItemAndTile(3, "screenMedium", TileScreenMedium.class);
        blockMachine.registerSubItemAndTile(4, "screenFine", TileScreenFine.class);
        blockMachine.registerSubItemAndTile(5, "separatorMagnetic", TileSeperatorMagnetic.class);
        blockMachine.registerSubItemAndTile(6, "separatorGravity", TileSeperatorGravity.class);
        blockMachine.registerSubItemAndTile(7, "separatorFlotation", TileSeperatorFlotation.class);
        blockMachine.registerSubItemAndTile(8, "separatorElectrostatic", TileSeperatorElectrostatic.class);
        blockMachine.registerSubItemAndTile(9, "dryerRotary", TileDryerRotary.class);

    }

}
