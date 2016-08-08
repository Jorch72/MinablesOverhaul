package teamasm.moh.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import teamasm.moh.init.ModItems;

import java.util.List;

/**
 * Created by brandon3055 on 8/08/2016.
 */
public class ItemComponent extends Item {

    public ItemComponent() {
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < ModItems.componentList.size(); i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getItemDamage() >= ModItems.componentList.size()) {
            return "Error.Unknown";
        }
        return super.getUnlocalizedName(stack) + "." + ModItems.componentList.get(stack.getItemDamage());
    }
}
