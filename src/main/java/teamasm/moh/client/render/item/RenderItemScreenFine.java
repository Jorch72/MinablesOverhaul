package teamasm.moh.client.render.item;

import net.minecraft.item.ItemStack;
import teamasm.moh.client.render.tile.RenderTileScreenFine;
import teamasm.moh.handler.EventHandler;

/**
 * Created by covers1624 on 8/7/2016.
 */
public class RenderItemScreenFine extends RenderItemMachine {
    @Override
    public void renderItem(ItemStack item) {
        RenderTileScreenFine.render(0, 0, 0, 2, -(EventHandler.clientTicks + EventHandler.renderTickFrame));
    }
}
