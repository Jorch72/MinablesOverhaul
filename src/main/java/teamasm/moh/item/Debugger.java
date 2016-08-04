package teamasm.moh.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class Debugger extends Item {

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {

        if (!worldIn.isRemote) {

        }

        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }
}
