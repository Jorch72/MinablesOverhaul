package teamasm.moh.item;

import codechicken.lib.util.ItemNBTUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import teamasm.moh.MinablesOverhaul;
import teamasm.moh.reference.OreRegistry;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by brandon3055 on 8/08/2016.
 */
public class ItemOreDust extends Item {
    private NumberFormat format = new DecimalFormat("###.###");

    public ItemOreDust() {
        setCreativeTab(MinablesOverhaul.MOH_TAB);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (String name : OreRegistry.INSTANCE.getOreList()) {
            ItemStack stack = new ItemStack(itemIn, 1, OreRegistry.getOreIndex(name));
            ItemNBTUtils.setFloat(stack, "Purity", 1F);
            subItems.add(stack);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "." + OreRegistry.getFromIndex(stack.getItemDamage());
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(TextFormatting.GOLD + "" + format.format(ItemNBTUtils.getFloat(stack, "Purity") * 100F) + "% Pure");
    }
}
