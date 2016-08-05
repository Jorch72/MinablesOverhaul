package teamasm.moh.manager;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public interface IOverhaulRecipe {

    /**
     * @return the energy cost for this recipe.
     */
    int getEnergyCost();

    /**
     * @return the time in ticks required for this recipe (Assuming stable energy supply)
     */
    int getProcessTime();

}
