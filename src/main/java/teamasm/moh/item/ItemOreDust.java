package teamasm.moh.item;

import codechicken.lib.inventory.InventoryUtils;
import codechicken.lib.util.ItemNBTUtils;
import codechicken.lib.vec.Vector3;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
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
        float purity = ItemNBTUtils.getFloat(stack, "Purity");
        tooltip.add(TextFormatting.GOLD + "" + format.format(purity * 100F) + "% Pure");
        int ingots = (int) (purity * 4F);
        float extra = purity * 4F % 1F * 100F;
        tooltip.add(TextFormatting.AQUA + "Yield: " + (int)ingots + TextFormatting.GRAY +" +1 ("+format.format(extra) + "% Chance)");
        tooltip.add("");
        tooltip.add("Final stage processing not yet implemented...");
        tooltip.add("For now just right click to craft into ingots.");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote) {
            return super.onItemRightClick(stack, world, player, hand);
        }

        int count = stack.stackSize;
        float purity = ItemNBTUtils.getFloat(stack, "Purity");
        int ingots = (int) (purity * 4F);
        float extra = purity * 4F % 1F;
        String ore = OreRegistry.getProduct(OreRegistry.getFromIndex(stack.getItemDamage()));

        if (ore == null || OreDictionary.getOres(ore).isEmpty()) {
            player.addChatComponentMessage(new TextComponentString("Error invalid ore..."));
            stack = null;
            return super.onItemRightClick(stack, world, player, hand);
        }

        ItemStack oreStack = OreDictionary.getOres(ore).get(0);

        for (int i = 0; i < count; i++) {
            int randNumber = ingots + (extra > world.rand.nextFloat() ? 1 : 0);
            ItemStack drop = oreStack.copy();
            drop.stackSize = randNumber;
            InventoryUtils.dropItem(drop, world, Vector3.fromEntityCenter(player));
        }

        player.setHeldItem(hand, null);

        return super.onItemRightClick(stack, world, player, hand);
    }
}
