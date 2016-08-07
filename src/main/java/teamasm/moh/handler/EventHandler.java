package teamasm.moh.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamasm.moh.client.OreDustTextureManager;
import teamasm.moh.entity.capabilities.CapabilityHelper;
import teamasm.moh.entity.capabilities.ResearchProvider;
import teamasm.moh.manager.OreStripManager;
import teamasm.moh.reference.Reference;

import static teamasm.moh.entity.capabilities.ResearchStorage.RESEARCH_CAP;

/**
 * Created by covers1624 on 8/4/2016.
 */
public class EventHandler {
    @SideOnly(Side.CLIENT)
    public static int clientTicks;
    @SideOnly(Side.CLIENT)
    public static float renderTickFrame;

    @SubscribeEvent
    public void onOreGen(OreGenEvent.GenerateMinable event) {

        if (event.getGenerator() instanceof WorldGenMinable) {
            WorldGenMinable worldGenMinable = (WorldGenMinable) event.getGenerator();
            if (OreStripManager.shouldStrip(worldGenMinable.oreBlock)) {
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
        if (event.getOriginal().hasCapability(RESEARCH_CAP, null) && event.getEntityPlayer().hasCapability(RESEARCH_CAP, null)) {
            event.getEntityPlayer().getCapability(RESEARCH_CAP, null).setResearch(event.getOriginal().getCapability(RESEARCH_CAP, null).getResearch());
        }
    }

    @SubscribeEvent
    public void playerLoggedIn(PlayerLoggedInEvent event) {
        CapabilityHelper.syncResearch(event.player);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void clientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase == Phase.END) {
            clientTicks++;
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void renderTickEvent(TickEvent.RenderTickEvent event) {
        if (event.phase == Phase.START) {
            renderTickFrame = event.renderTickTime;
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void textureStitchEvent(TextureStitchEvent.Pre event) {
        OreDustTextureManager.loadTextures(event.getMap());
    }
}
