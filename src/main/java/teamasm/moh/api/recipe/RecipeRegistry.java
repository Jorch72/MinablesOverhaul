package teamasm.moh.api.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class RecipeRegistry {
    public static List<ReducerCrusherRecipe> reducerCrusherRecipes = new ArrayList<ReducerCrusherRecipe>();

    public static ReducerCrusherRecipe registerReducerCrusherRecipe(Object[] inputs, ItemStack[] outputs){
        ReducerCrusherRecipe recipe = new ReducerCrusherRecipe(inputs, outputs);
        reducerCrusherRecipes.add(recipe);
        return recipe;
    }
}
