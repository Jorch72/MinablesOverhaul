package teamasm.moh.api.recipe;

import net.minecraftforge.fml.common.Loader;

/**
 * Created by covers1624 on 8/6/2016.
 */
public class APIConstants {

    private static IRecipeRegistry recipeRegistry;

    public static IRecipeRegistry getRecipeRegistry() {
        return recipeRegistry;
    }

    public static void setRecipeRegistry(IRecipeRegistry recipeRegistry) {
        if (Loader.instance().activeModContainer().getModId().equals("minablesoverhaul")) {
            APIConstants.recipeRegistry = recipeRegistry;
        }
    }

}
