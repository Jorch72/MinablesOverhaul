package teamasm.moh.recipe.output;

import net.minecraft.item.ItemStack;
import teamasm.moh.api.recipe.output.IRecipeOutput;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class DefaultRecipeOutput implements IRecipeOutput {

    protected ItemStack[] stacks;

    public DefaultRecipeOutput(ItemStack... stacks){
        this.stacks = stacks;
    }

    @Override
    public int numStacks() {
        return stacks.length;
    }

    @Override
    public ItemStack getStack(int slot) {
        return stacks[slot];
    }

    @Override
    public ItemStack[] getAllStacks() {
        return stacks;
    }
}
