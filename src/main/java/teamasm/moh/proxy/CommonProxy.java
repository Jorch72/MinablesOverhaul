package teamasm.moh.proxy;

import codechicken.lib.packet.PacketCustom;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamasm.moh.network.PacketDispatcher;
import teamasm.moh.network.ServerPacketHandler;

/**
 * Created by covers1624 on 8/4/2016.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {
        PacketCustom.assignHandler(PacketDispatcher.NET_CHANNEL, new ServerPacketHandler());
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
