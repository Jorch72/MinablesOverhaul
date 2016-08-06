package teamasm.moh.recipe;

import codechicken.lib.util.ItemUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.api.recipe.OreStack;

import java.util.List;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public abstract class BaseRecipe implements IMOHRecipe {

    Object[] inputs;
    ItemStack[] outputs;

    /**
     * Allow Items, Blocks, ItemStacks, OreStacks and strings as params to the base recipe.
     * Inputs are always stored as either an ItemStack or an OreStack.
     * Outputs are always stored as an ItemStack.
     *
     * @param inputs  Inputs for the recipe.
     * @param outputs Outputs for the recipe.
     */
    public BaseRecipe(Object[] inputs, Object[] outputs) {
        this.inputs = new Object[inputs.length];
        this.outputs = new ItemStack[outputs.length];

        for (int i = 0; i < inputs.length; i++) {
            Object input = inputs[i];
            if (input instanceof Item) {
                this.inputs[i] = new ItemStack((Item) input);
            } else if (input instanceof Block) {
                this.inputs[i] = new ItemStack((Block) input);
            } else if (input instanceof ItemStack) {
                this.inputs[i] = input;
            } else if (input instanceof String) {
                this.inputs[i] = new OreStack((String) input, 1);
            } else if (input instanceof OreStack) {
                this.inputs[i] = input;
            } else {
                throw new IllegalArgumentException(String.format("Unknown argument %s of inputs for %s recipe!", i, this.getClass().getCanonicalName()));
            }
        }
        for (int i = 0; i < outputs.length; i++) {
            Object output = outputs[i];
            if (output instanceof Item) {
                this.outputs[i] = new ItemStack((Item) output);
            } else if (output instanceof Block) {
                this.outputs[i] = new ItemStack((Block) output);
            } else if (output instanceof ItemStack) {
                this.outputs[i] = (ItemStack) output;
            } else if (output instanceof String || output instanceof OreStack) {
                OreStack stack = new OreStack(output);
                List<ItemStack> stacks = OreDictionary.getOres(stack.mat);
                if (stacks == null || stacks.isEmpty()) {
                    throw new IllegalArgumentException(String.format("Unable to get %s from ore dictionary!", stack.mat));
                }
                this.outputs[i] = ItemUtils.copyStack(stacks.get(0), stack.stackSize);
            } else {
                throw new IllegalArgumentException(String.format("Unknown argument %s of inputs for %s recipe!", i, this.getClass().getCanonicalName()));
            }
        }

    }

    @Override
    public ItemStack[] getRecipeResults() {
        return outputs;
    }

    @Override
    public Object[] getRecipeInputs() {
        return inputs;
    }

    @Override
    public boolean isRecipe(ItemStack[] stacks) {
        return false;
    }
}
