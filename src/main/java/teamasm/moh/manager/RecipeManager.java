package teamasm.moh.manager;

import net.minecraft.item.ItemStack;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.recipe.ComminutionRecipe;
import teamasm.moh.recipe.ConcentrationRecipe;
import teamasm.moh.recipe.DewateringRecipe;
import teamasm.moh.recipe.SizingRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class RecipeManager {

    private static Map<String, List<IMOHRecipe>> machineRecipeMap = new HashMap<String, List<IMOHRecipe>>();

    public static void registerRecipe(String machine, IMOHRecipe recipe) {
        List<IMOHRecipe> recipes = new ArrayList<IMOHRecipe>();
        if (machineRecipeMap.containsKey(machine)) {
            recipes = machineRecipeMap.get(machine);
        }
        recipes.add(recipe);
        machineRecipeMap.put(machine, recipes);
    }

    public static IMOHRecipe getRecipeFromMatrix(String machine, ItemStack[] matrix) {
        if (machineRecipeMap.containsKey(machine)) {
            for (IMOHRecipe recipe : machineRecipeMap.get(machine)) {
                if (recipe.isRecipe(matrix)) {
                    return recipe;
                }
            }
        }
        return null;
    }

    public static void registerCrusherRecipe(ComminutionRecipe recipe) {
        registerRecipe("crusher", recipe);
    }

    public static void registerSeparatorRecipe(ConcentrationRecipe recipe) {
        registerRecipe("separator", recipe);
    }

    public static void registerSizingRecipe(SizingRecipe recipe) {
        registerRecipe("sizing", recipe);
    }

    public static void registerDryingRecipe(DewateringRecipe recipe) {
        registerRecipe("drying", recipe);
    }

    public static ComminutionRecipe getCrusherRecipe(ItemStack[] matrix) {
        return (ComminutionRecipe) getRecipeFromMatrix("crusher", matrix);
    }

    public static ConcentrationRecipe getSeparatorRecipe(ItemStack[] matrix) {
        return (ConcentrationRecipe) getRecipeFromMatrix("separator", matrix);
    }

    public static SizingRecipe getSizingRecipe(ItemStack[] matrix) {
        return (SizingRecipe) getRecipeFromMatrix("sizing", matrix);
    }

    public static DewateringRecipe getDryingRecipe(ItemStack[] matrix) {
        return (DewateringRecipe) getRecipeFromMatrix("drying", matrix);
    }
}
