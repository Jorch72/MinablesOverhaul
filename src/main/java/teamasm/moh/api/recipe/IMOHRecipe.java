package teamasm.moh.api.recipe;

import net.minecraft.item.ItemStack;

/**
 * Created by covers1624 on 8/5/2016.
 */
public interface IMOHRecipe {

    /**
     * @return the energy cost for this recipe.
     */
    int getEnergyCost();

    /**
     * @return the time in ticks required for this recipe (Assuming stable energy supply)
     */
    int getProcessTime();

    /**
     * Checks if the crafting matrix is a valid recipe.
     *
     * @param stacks Crafting matrix.
     * @return True if recipe matches.
     */
    boolean isRecipe(ItemStack[] stacks);

    /**
     * Gets the results for the recipe
     *
     * @return The results of the recipe.
     */
    ItemStack[] getRecipeResults();

    /**
     * Gets the inputs of the recipe;
     *
     * @return Will be an ItemStack or a OreStack.
     */
    Object[] getRecipeInputs();

}
