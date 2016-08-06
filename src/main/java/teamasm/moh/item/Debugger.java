package teamasm.moh.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class Debugger extends Item {

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {

        if (world.isRemote) {
            FMLLog.info("" + 34.5 % 1);
        }

        //        if (!world.isRemote) {
        //
        //            if (player.isSneaking()) {
        //                CapabilityHelper.setResearch(player, "Test", CapabilityHelper.getResearch(player, "Test") + 1);
        //            }
        //            else {
        //                FMLLog.info(""+CapabilityHelper.getResearch(player, "Test"));
        //            }
        //        }
        //        else {
        //            FMLLog.info("Client: "+CapabilityHelper.getResearch(player, "Test"));
        //        }

        return super.onItemRightClick(stack, world, player, hand);
    }
}
