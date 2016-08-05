package teamasm.moh.tile;

import net.minecraft.nbt.NBTTagCompound;
import teamasm.moh.api.recipe.IMOHRecipe;

/**
 * Created by brandon3055 on 5/08/2016.
 * This is the base tile for all "Processors" (Machines that turn one material into another via some process)
 * This dose not implement energy because there will be manual machines that do not use energy.
 */
public abstract class TileProcessorBase extends TileInventoryBase {

    /**
     * The currently crafting recipe.
     */
    protected IMOHRecipe activeRecipe = null;
    /**
     * The progress in ticks for the current recipe.
     * This is a double because progress will slow when energy is low.
     */
    public double progress = 0;
    /**
     * The number of ticks required by the current recipe.
     */
    public int recipeTime = 0;

    public abstract IMOHRecipe checkForValidRecipe();

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setDouble("Progress", progress);
        compound.setInteger("RecipeTime", recipeTime);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        progress = compound.getDouble("Progress");
        recipeTime = compound.getInteger("RecipeTime");
        super.readFromNBT(compound);
    }
}
