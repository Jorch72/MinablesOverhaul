package teamasm.moh.handler;

import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by covers1624 on 8/4/2016.
 */
public class EventHandler {

    @SubscribeEvent
    public void onOreGen(OreGenEvent.GenerateMinable event){
        //FMLLog.info("EVENT! " + event.getType());
        switch (event.getType()){
        case DIAMOND:
        case GOLD:
        case IRON:
        case LAPIS:
        case REDSTONE:
        case QUARTZ:
        case EMERALD:
            FMLLog.info("Canceling gen of ore %s", event.getType().name());
            event.setResult(Result.DENY);
            break;

        case CUSTOM:
            if (event.getGenerator()instanceof WorldGenMinable){
                FMLLog.info("Canceling gen of ore %s", ((WorldGenMinable)event.getGenerator()).oreBlock.toString());
                event.setResult(Result.DENY);
            }
            break;

        case COAL:
        case SILVERFISH:
        case GRANITE:
        case DIORITE:
        case ANDESITE:
        case GRAVEL:
        case DIRT:
        default:
            break;
        }
    }


}
