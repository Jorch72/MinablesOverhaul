package teamasm.moh.recipe;

import net.minecraft.item.ItemStack;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.api.recipe.output.IRecipeOutput;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class DewateringRecipe implements IMOHRecipe {
    @Override
    public int getEnergyCost() {
        return 0;
    }

    @Override
    public int getProcessTime() {
        return 0;
    }

    @Override
    public boolean isRecipe(ItemStack[] stacks) {
        return false;
    }

    @Override
    public IRecipeOutput craftItem(ItemStack[] stacks) {
        return null;
    }
}
