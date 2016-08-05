package teamasm.moh.api.recipe;

import net.minecraft.item.ItemStack;
import teamasm.moh.api.recipe.output.IRecipeOutput;

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
     * Called by the tile to consume items from the craft matrix and spit out the result.
     *
     * @param stacks Craft matrix.
     * @return The resulting StackHolder.
     */
    IRecipeOutput craftItem(ItemStack[] stacks);

}
