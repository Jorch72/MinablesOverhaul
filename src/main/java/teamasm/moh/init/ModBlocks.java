package teamasm.moh.init;

import codechicken.lib.block.ItemBlockMultiType;
import codechicken.lib.render.ModelRegistryHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamasm.moh.block.BlockMachine;
import teamasm.moh.block.BlockOre;
import teamasm.moh.client.render.item.RenderItemCrusherAutomatic;
import teamasm.moh.client.render.item.RenderItemGrinderAutomatic;
import teamasm.moh.client.render.item.RenderItemScreenCoarse;
import teamasm.moh.client.render.tile.RenderTileCrusherAutomatic;
import teamasm.moh.client.render.tile.RenderTileGrinderAutomatic;
import teamasm.moh.client.render.tile.RenderTileScreenCoarse;
import teamasm.moh.reference.Reference;
import teamasm.moh.tile.machines.teir1.*;
import teamasm.moh.tile.machines.tier2.TileReducerGrinder;
import teamasm.moh.tile.machines.tier2.TileSeperatorElectrostatic;

import static teamasm.moh.reference.VariantReference.machinesList;

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

    @SideOnly(Side.CLIENT)
    public static void registerModels() {

        ClientRegistry.bindTileEntitySpecialRenderer(TileReducerCrusher.class, new RenderTileCrusherAutomatic());
        ClientRegistry.bindTileEntitySpecialRenderer(TileReducerGrinder.class, new RenderTileGrinderAutomatic());
        ClientRegistry.bindTileEntitySpecialRenderer(TileScreenCoarse.class, new RenderTileScreenCoarse());

        for (int i = 0; i < machinesList.size(); i++) {
            String variant = machinesList.get(i);
            ModelResourceLocation location = new ModelResourceLocation(blockMachine.getRegistryName(), "type=" + variant);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMachine), i, location);
        }

        ModelRegistryHelper.register(new ModelResourceLocation(blockMachine.getRegistryName(), "type=reducerCrusher"), new RenderItemCrusherAutomatic());
        ModelRegistryHelper.register(new ModelResourceLocation(blockMachine.getRegistryName(), "type=reducerGrinder"), new RenderItemGrinderAutomatic());
        ModelRegistryHelper.register(new ModelResourceLocation(blockMachine.getRegistryName(), "type=screenCoarse"), new RenderItemScreenCoarse());

    }

}
