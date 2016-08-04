package teamasm.moh.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamasm.moh.item.Debugger;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class ModItems {

    public static Item debugger = new Debugger();

    public static void init() {
        GameRegistry.register(debugger.setRegistryName("debugger"));
    }

}
