package teamasm.moh.item;

import net.minecraft.item.Item;
import teamasm.moh.MinablesOverhaul;

/**
 * Created by brandon3055 on 6/08/2016.
 */
public class ItemOreDrop extends Item {

    public ItemOreDrop() {
        setCreativeTab(MinablesOverhaul.MOH_TAB);
        setHasSubtypes(true);
        setMaxStackSize(64);
        setMaxDamage(0);
    }
}
