package teamasm.moh.api.recipe.output;

import net.minecraft.item.ItemStack;

/**
 * Created by covers1624 on 8/5/2016.
 */
public interface IRecipeOutput {

    int numStacks();

    ItemStack getStack(int slot);

    ItemStack[] getAllStacks();

}
