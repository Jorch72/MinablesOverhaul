package teamasm.moh.client.render.item;

import net.minecraft.item.ItemStack;
import teamasm.moh.client.render.tile.RenderTileDebug;

/**
 * Created by covers1624 on 8/8/2016.
 */
public class RenderItemDebug extends RenderItemMachine {
    @Override
    public void renderItem(ItemStack item) {
        RenderTileDebug.render(0, 0, 0, 10);
    }
}
