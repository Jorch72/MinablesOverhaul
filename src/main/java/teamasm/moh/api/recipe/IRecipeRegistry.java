package teamasm.moh.api.recipe;

import net.minecraft.item.ItemStack;

/**
 * Created by covers1624 on 8/6/2016.
 */
public interface IRecipeRegistry {

    void registerRecipe(String machine, IMOHRecipe recipe);

    IMOHRecipe getRecipeFromMatrix(String machine, ItemStack[] matrix);
}
