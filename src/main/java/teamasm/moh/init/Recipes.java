package teamasm.moh.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class Recipes {

    public static void init() {
        ItemStack crusherFrame = new ItemStack(ModItems.component, 1, 0);
        ItemStack crusherPlates = new ItemStack(ModItems.component, 1, 1);
        ItemStack crusherWheel = new ItemStack(ModItems.component, 1, 2);
        ItemStack screenFrameC = new ItemStack(ModItems.component, 1, 3);
        ItemStack screenFrameF = new ItemStack(ModItems.component, 1, 4);
        ItemStack separatorFrame = new ItemStack(ModItems.component, 1, 5);
        ItemStack separatorDrum = new ItemStack(ModItems.component, 1, 6);
        ItemStack motorHousing = new ItemStack(ModItems.component, 1, 7);
        ItemStack motorStator = new ItemStack(ModItems.component, 1, 8);
        ItemStack motor = new ItemStack(ModItems.component, 1, 9);
        ItemStack crank = new ItemStack(ModItems.component, 1, 10);

        ItemStack crusher = new ItemStack(ModBlocks.blockMachine, 1, 0);
        ItemStack grinder = new ItemStack(ModBlocks.blockMachine, 1, 1);
        ItemStack screenCoarse = new ItemStack(ModBlocks.blockMachine, 1, 2);
        ItemStack screenFine = new ItemStack(ModBlocks.blockMachine, 1, 3);
        ItemStack centrifuge = new ItemStack(ModBlocks.blockMachine, 1, 5);

        ItemStack crusherManual = new ItemStack(ModBlocks.blockMachine, 1, 9);
        ItemStack screenManual = new ItemStack(ModBlocks.blockMachine, 1, 10);
        ItemStack centrifugeManual = new ItemStack(ModBlocks.blockMachine, 1, 11);

        GameRegistry.addRecipe(new ShapedOreRecipe(crusherFrame, "A A", "A A", "BCB", 'A', "plankWood", 'B', Blocks.STONE_SLAB, 'C', ModItems.brokenOre));
        GameRegistry.addRecipe(crusherPlates, "A A", "A A", "A A", 'A', ModItems.brokenOre);
        GameRegistry.addRecipe(new ShapedOreRecipe(crusherWheel, "ABA", "BCB", "ABA", 'A', ModItems.brokenOre, 'B', Blocks.SANDSTONE, 'C', "gemDiamond"));
        GameRegistry.addRecipe(new ShapedOreRecipe(screenFrameC, "ABA", "ABA", "ABA", 'A', "plankWood", 'B', ModItems.brokenOre));
        GameRegistry.addRecipe(new ShapedOreRecipe(screenFrameF, "ABA", "CBC", "ABA", 'A', "ingotIron", 'B', Blocks.IRON_BARS, 'C', screenFrameC));
        GameRegistry.addRecipe(separatorFrame, "A A", "A A", "AAA", 'A', ModItems.brokenOre);
        GameRegistry.addRecipe(new ShapedOreRecipe(separatorDrum, "ABA", "ACA", "ABA", 'A', "plankWood", 'B', "slabWood", 'C', ModItems.brokenOre));
        GameRegistry.addRecipe(new ShapedOreRecipe(motorHousing, "ABA", "BCB", "ABA", 'A', ModItems.brokenOre, 'B', "ingotIron", 'C', Blocks.REDSTONE_BLOCK));
        GameRegistry.addRecipe(new ShapedOreRecipe(motorStator, "ABA", "BCB", "ABA", 'A', "gemLapis", 'B', "ingotIron", 'C', ModItems.brokenOre));
        GameRegistry.addRecipe(new ShapedOreRecipe(motor, "AAA", "BCD", "AAA", 'A', "ingotIron", 'B', motorHousing, 'C', "gemDiamond", 'D', motorStator));
        GameRegistry.addRecipe(new ShapedOreRecipe(crank, "ABB", "A  ", "A  ", 'A', ModItems.brokenOre, 'B', "stickWood"));

        GameRegistry.addRecipe(crusherManual, " A ", " BC", "   ", 'A', crusherPlates, 'B', crusherFrame, 'C', crank);
        GameRegistry.addRecipe(new ShapedOreRecipe(screenManual, "AAA", "BCD", "AAA", 'A', Blocks.STONE_SLAB, 'B', screenFrameC, 'C', "stone", 'D', crank));
        GameRegistry.addRecipe(centrifugeManual, " A ", " BC", "   ", 'A', separatorFrame, 'B', separatorDrum, 'C', crank);
        GameRegistry.addRecipe(new ShapedOreRecipe(crusher, "ABA", "ACD", "AAA", 'A', "ingotIron", 'B', crusherPlates, 'C', crusherFrame, 'D', motor));
        GameRegistry.addRecipe(new ShapedOreRecipe(grinder, "ABA", "ACD", "AAA", 'A', "ingotIron", 'B', crusherWheel, 'C', crusherFrame, 'D', motor));
        GameRegistry.addRecipe(new ShapedOreRecipe(screenCoarse, "AAA", "BCD", "AAA", 'A', "ingotIron", 'B', screenFrameC, 'C', "ingotIron", 'D', motor));
        GameRegistry.addRecipe(new ShapedOreRecipe(screenFine, "AAA", "BCD", "AAA", 'A', "ingotIron", 'B', screenFrameF, 'C', "ingotIron", 'D', motor));
        GameRegistry.addRecipe(new ShapedOreRecipe(centrifuge, "ABA", "ACD", "AAA", 'A', "ingotIron", 'B', separatorDrum, 'C', separatorFrame, 'D', motor));
    }

}
