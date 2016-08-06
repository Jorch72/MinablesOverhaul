package teamasm.moh.proxy;

import codechicken.lib.model.loader.CCBakedModelLoader;
import codechicken.lib.packet.PacketCustom;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamasm.moh.client.MinablesOverhaulBakedModelLoader;
import teamasm.moh.client.render.tile.RenderTileCrusherAutomatic;
import teamasm.moh.init.ModItems;
import teamasm.moh.network.ClientPacketHandler;
import teamasm.moh.network.PacketDispatcher;
import teamasm.moh.tile.machines.teir1.TileReducerCrusher;

/**
 * Created by covers1624 on 8/4/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        CCBakedModelLoader.registerLoader(MinablesOverhaulBakedModelLoader.INSTANCE);
        ModItems.registerModelVariants();
        ClientRegistry.bindTileEntitySpecialRenderer(TileReducerCrusher.class, new RenderTileCrusherAutomatic());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        PacketCustom.assignHandler(PacketDispatcher.NET_CHANNEL, new ClientPacketHandler());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
