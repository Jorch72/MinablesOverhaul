package teamasm.moh.init;

import codechicken.lib.block.ItemBlockMultiType;
import codechicken.lib.render.ModelRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamasm.moh.block.BlockDebug;
import teamasm.moh.block.BlockMachine;
import teamasm.moh.block.BlockOre;
import teamasm.moh.client.render.item.*;
import teamasm.moh.client.render.tile.*;
import teamasm.moh.reference.Reference;
import teamasm.moh.tile.TileDebug;
import teamasm.moh.tile.machines.teir1.*;
import teamasm.moh.tile.machines.tier2.TileReducerGrinder;
import teamasm.moh.tile.machines.tier2.TileSeparatorElectrostatic;

import static teamasm.moh.reference.VariantReference.machinesList;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class ModBlocks {

    public static BlockOre blockOre;
    public static BlockMachine blockMachine;

    public static Block testBlock;

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
        blockMachine.registerSubItemAndTile(3, "screenFine", TileScreenFine.class);
        blockMachine.registerSubItemAndTile(4, "separatorMagnetic", TileSeparatorMagnetic.class);
        blockMachine.registerSubItemAndTile(5, "separatorGravity", TileSeparatorGravity.class);
        blockMachine.registerSubItemAndTile(6, "separatorFlotation", TileSeparatorFlotation.class);
        blockMachine.registerSubItemAndTile(7, "separatorElectrostatic", TileSeparatorElectrostatic.class);
        blockMachine.registerSubItemAndTile(8, "dryerRotary", TileDryerRotary.class);

        testBlock = new BlockDebug();
        testBlock.setRegistryName("testBlock");
        GameRegistry.register(testBlock);
        GameRegistry.register(new ItemBlock(testBlock).setRegistryName("testBlock"));
        GameRegistry.registerTileEntity(TileDebug.class, "testBlock");

    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {

        ClientRegistry.bindTileEntitySpecialRenderer(TileDebug.class, new RenderTileDebug());

        ClientRegistry.bindTileEntitySpecialRenderer(TileReducerCrusher.class, new RenderTileCrusherAutomatic());
        ClientRegistry.bindTileEntitySpecialRenderer(TileReducerGrinder.class, new RenderTileGrinderAutomatic());
        ClientRegistry.bindTileEntitySpecialRenderer(TileScreenCoarse.class, new RenderTileScreenCoarse());
        ClientRegistry.bindTileEntitySpecialRenderer(TileScreenFine.class, new RenderTileScreenFine());
        //ClientRegistry.bindTileEntitySpecialRenderer(TileSeparatorMagnetic.class, new RenderTileSeparatorMagnetic());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSeparatorGravity.class, new RenderTileSeparatorGravity());

        for (int i = 0; i < machinesList.size(); i++) {
            String variant = machinesList.get(i);
            ModelResourceLocation location = new ModelResourceLocation(blockMachine.getRegistryName(), "type=" + variant);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMachine), i, location);
        }

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockOre), 0, new ModelResourceLocation(blockOre.getRegistryName(), "normal"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(testBlock), 0, new ModelResourceLocation(testBlock.getRegistryName(), "inventory"));

        ModelRegistryHelper.register(new ModelResourceLocation(blockMachine.getRegistryName(), "type=reducerCrusher"), new RenderItemCrusherAutomatic());
        ModelRegistryHelper.register(new ModelResourceLocation(blockMachine.getRegistryName(), "type=reducerGrinder"), new RenderItemGrinderAutomatic());
        ModelRegistryHelper.register(new ModelResourceLocation(blockMachine.getRegistryName(), "type=screenCoarse"), new RenderItemScreenCoarse());
        ModelRegistryHelper.register(new ModelResourceLocation(blockMachine.getRegistryName(), "type=screenFine"), new RenderItemScreenFine());
        //ModelRegistryHelper.register(new ModelResourceLocation(blockMachine.getRegistryName(), "type=separatorMagnetic"), new RenderItemSeparatorMagnetic());
        ModelRegistryHelper.register(new ModelResourceLocation(blockMachine.getRegistryName(), "type=separatorGravity"), new RenderItemSeparatorGravity());
        ModelRegistryHelper.register(new ModelResourceLocation(testBlock.getRegistryName(), "inventory"), new RenderItemDebug());

    }

}
