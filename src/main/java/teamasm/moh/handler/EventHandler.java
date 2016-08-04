package teamasm.moh.handler;

import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamasm.moh.manager.OreStripManager;

/**
 * Created by covers1624 on 8/4/2016.
 */
public class EventHandler {

    @SubscribeEvent
    public void onOreGen(OreGenEvent.GenerateMinable event) {

        if (event.getGenerator() instanceof WorldGenMinable) {
            WorldGenMinable worldGenMinable = (WorldGenMinable) event.getGenerator();
            if (OreStripManager.shouldStrip(worldGenMinable.oreBlock)) {
                //FMLLog.info("Canceling ore gen for %s", worldGenMinable.oreBlock.toString());
                event.setResult(Result.DENY);
            }
        }
    }

}
