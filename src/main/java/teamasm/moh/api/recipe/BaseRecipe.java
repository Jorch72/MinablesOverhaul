package teamasm.moh.api.recipe;

import net.minecraft.item.ItemStack;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class BaseRecipe {
    Object[] inputs;
    ItemStack[] outputs;

    public BaseRecipe(Object[] inputs, ItemStack[] outputs){
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public Object[] getInputs(){
        return this.inputs;
    }

    public ItemStack[] getOutputs(){
        return this.getOutputs();
    }
}
