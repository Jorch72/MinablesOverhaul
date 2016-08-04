package teamasm.moh.proxy;

import codechicken.lib.packet.PacketCustom;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamasm.moh.handler.EventHandler;
import teamasm.moh.init.ModBlocks;
import teamasm.moh.init.ModItems;
import teamasm.moh.init.Recipes;
import teamasm.moh.manager.OreStripManager;
import teamasm.moh.network.PacketDispatcher;
import teamasm.moh.network.ServerPacketHandler;

/**
 * Created by covers1624 on 8/4/2016.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        EventHandler handler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(handler);
        MinecraftForge.TERRAIN_GEN_BUS.register(handler);
        MinecraftForge.ORE_GEN_BUS.register(handler);

        ModBlocks.init();
        ModItems.init();
        Recipes.init();
    }

    public void init(FMLInitializationEvent event) {
        PacketCustom.assignHandler(PacketDispatcher.NET_CHANNEL, new ServerPacketHandler());
    }

    public void postInit(FMLPostInitializationEvent event) {
        OreStripManager.init();
    }

}
