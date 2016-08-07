package teamasm.moh.proxy;

import codechicken.lib.packet.PacketCustom;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamasm.moh.client.render.tile.RenderTileCrusherAutomatic;
import teamasm.moh.client.render.tile.RenderTileGrinderAutomatic;
import teamasm.moh.init.ModBlocks;
import teamasm.moh.init.ModItems;
import teamasm.moh.network.ClientPacketHandler;
import teamasm.moh.network.PacketDispatcher;
import teamasm.moh.tile.machines.teir1.TileReducerCrusher;
import teamasm.moh.tile.machines.tier2.TileReducerGrinder;

/**
 * Created by covers1624 on 8/4/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        //CCBakedModelLoader.registerLoader(MinablesOverhaulBakedModelLoader.INSTANCE);
        ModItems.registerModels();
        ModBlocks.registerModels();
        ClientRegistry.bindTileEntitySpecialRenderer(TileReducerCrusher.class, new RenderTileCrusherAutomatic());
        ClientRegistry.bindTileEntitySpecialRenderer(TileReducerGrinder.class, new RenderTileGrinderAutomatic());
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
