package teamasm.moh.item;

import codechicken.lib.world.placement.BlockPlacementBatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLLog;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class Debugger extends Item {

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {

        //if (world.isRemote) {
            BlockPos pos = new BlockPos(player);


            //world.markBlockRangeForRenderUpdate(pos.add(-10, -10, -10), pos.add(10, 10, 10));
        //world.getChunkFromBlockCoords(pos).enqueueRelightChecks();




          //world.getChunkFromBlockCoords(pos).checkLight();
       // world.getChunkFromBlockCoords(pos).generateSkylightMap();



        if (world instanceof WorldServer) {
            FMLLog.info("Run");

            BlockPlacementBatcher batcher = new BlockPlacementBatcher((WorldServer) world);

            for (int x = -100; x < 100; x++) {
                for (int y = 1; y < 100; y++) {
                    for (int z = -100; z < 100; z++) {
                    //world.getChunkFromBlockCoords(pos.add(x * 16, 0, z * 16)).generateSkylightMap();
                        BlockPos posAt = new BlockPos(player.posX + x, y, player.posZ + z);

                        if (world.getBlockState(posAt).getBlock() == Blocks.STONE) {
                            batcher.setBlockState(posAt, Blocks.AIR.getDefaultState());
                        }

                    }
                }
            }

            batcher.finish();
        }

        for (int x = -10; x < 10; x++) {
            for (int z = -10; z < 10; z++) {
                world.getChunkFromBlockCoords(pos.add(x * 16, 0, z * 16)).generateSkylightMap();
            }
        }
       // }

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
