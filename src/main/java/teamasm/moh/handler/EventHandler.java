package teamasm.moh.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamasm.moh.entity.capabilities.ResearchProvider;
import teamasm.moh.entity.capabilities.ResearchStorage;
import teamasm.moh.manager.OreStripManager;
import teamasm.moh.reference.Reference;

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

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent.Entity event) {
        if (event.getEntity() instanceof EntityPlayer && !(event.getEntity() instanceof FakePlayer)) {
            event.addCapability(new ResourceLocation(Reference.MOD_ID, "IResearch"), new ResearchProvider());
        }
    }

    @SubscribeEvent
    public void maintainCapability(PlayerEvent.Clone event) {
        if (event.getOriginal().hasCapability(ResearchStorage.RESEARCH_CAP, null) && event.getEntityPlayer().hasCapability(ResearchStorage.RESEARCH_CAP, null)) {
            event.getEntityPlayer().getCapability(ResearchStorage.RESEARCH_CAP, null).setResearch(event.getOriginal().getCapability(ResearchStorage.RESEARCH_CAP, null).getResearch());
        }
    }
}
