package teamasm.moh.init;

import codechicken.lib.block.ItemBlockMultiType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamasm.moh.block.BlockOre;
import teamasm.moh.block.itemblock.BlockMachine;
import teamasm.moh.reference.Reference;
import teamasm.moh.tile.machines.SomeMachine;

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
        GameRegistry.register(new ItemBlockMultiType(blockOre).setRegistryName("blockOre"));

        blockMachine = new BlockMachine();
        GameRegistry.register(blockMachine.setRegistryName("machine"));
        GameRegistry.register(new ItemBlockMultiType(blockMachine).setRegistryName("machine"));

        blockMachine.addSubItemAndTile(0, "someMachine", SomeMachine.class);

    }

}
