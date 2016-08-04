package teamasm.moh;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamasm.moh.proxy.CommonProxy;

import static teamasm.moh.reference.Reference.*;

/**
 * Created by covers1624 on 8/4/2016.
 */
@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)
public class MinablesOverhaul {

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Instance(MOD_NAME)
    public static MinablesOverhaul instance;

    public MinablesOverhaul() {
        instance = this;
    }

    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
