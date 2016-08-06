package teamasm.moh.manager;

import net.minecraft.item.ItemStack;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.api.recipe.IRecipeRegistry;
import teamasm.moh.recipe.ReducerCrusherRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class RecipeManager implements IRecipeRegistry {

    public static RecipeManager INSTANCE = new RecipeManager();

    private Map<String, List<IMOHRecipe>> machineRecipeMap = new HashMap<String, List<IMOHRecipe>>();

    public void registerRecipe(String machine, IMOHRecipe recipe) {
        List<IMOHRecipe> recipes = new ArrayList<IMOHRecipe>();
        if (machineRecipeMap.containsKey(machine)) {
            recipes = machineRecipeMap.get(machine);
        }
        recipes.add(recipe);
        machineRecipeMap.put(machine, recipes);
    }

    public IMOHRecipe getRecipeFromMatrix(String machine, ItemStack[] matrix) {
        if (machineRecipeMap.containsKey(machine)) {
            for (IMOHRecipe recipe : machineRecipeMap.get(machine)) {
                if (recipe.isRecipe(matrix)) {
                    return recipe;
                }
            }
        }
        return null;
    }

    public void registerCrusherRecipe(ReducerCrusherRecipe recipe) {
        registerRecipe("crusher", recipe);
    }

    public ReducerCrusherRecipe getCrusherRecipe(ItemStack[] matrix) {
        return (ReducerCrusherRecipe) getRecipeFromMatrix("crusher", matrix);
    }

}
