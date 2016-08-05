package teamasm.moh;

import codechicken.lib.gui.SimpleCreativeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import teamasm.moh.proxy.CommonProxy;

import static teamasm.moh.reference.Reference.*;

/**
 * Created by covers1624 on 8/4/2016.
 */
@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)
public class MinablesOverhaul {

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    public static final SimpleCreativeTab MOH_TAB = new SimpleCreativeTab("minablesoverhaul", "minablesoverhaul:oreNormal");

    @Instance(MOD_NAME)
    public static MinablesOverhaul instance;

    public MinablesOverhaul() {
        instance = this;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
