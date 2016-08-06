package teamasm.moh.recipe;

import net.minecraft.item.ItemStack;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class ReducerCrusherRecipe extends BaseRecipe {

    public ReducerCrusherRecipe(Object[] inputs, ItemStack[] outputs) {
        super(inputs, outputs);
    }

    @Override
    public int getEnergyCost() {
        return 0;
    }

    @Override
    public int getProcessTime() {
        return 0;
    }
}
