package teamasm.moh.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import teamasm.moh.MinablesOverhaul;
import teamasm.moh.reference.OreRegistry;

import java.util.List;

/**
 * Created by brandon3055 on 8/08/2016.
 */
public class ItemOreDust extends Item {

    public ItemOreDust() {
        setCreativeTab(MinablesOverhaul.MOH_TAB);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (String name : OreRegistry.INSTANCE.getOreList()) {
            subItems.add(new ItemStack(itemIn, 1, OreRegistry.getOreIndex(name)));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "." + OreRegistry.getFromIndex(stack.getItemDamage());
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
    }
}
