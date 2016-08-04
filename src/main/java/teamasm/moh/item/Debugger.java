package teamasm.moh.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import teamasm.moh.entity.capabilities.IResearch;

import static teamasm.moh.entity.capabilities.ResearchStorage.RESEARCH_CAP;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class Debugger extends Item {

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {

        if (!world.isRemote && player.hasCapability(RESEARCH_CAP, null)) {
            IResearch research = player.getCapability(RESEARCH_CAP, null);


            if (player.isSneaking()) {
                research.setResearch("Test", research.getResearch().containsKey("Test") ? research.getResearch().get("Test") + 1 : 0);

            }
            else {
                FMLLog.info(""+research.getResearch());
            }

        }

        return super.onItemRightClick(stack, world, player, hand);
    }
}
