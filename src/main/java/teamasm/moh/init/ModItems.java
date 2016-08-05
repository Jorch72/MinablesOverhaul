package teamasm.moh.init;

import codechicken.lib.asm.ObfMapping;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamasm.moh.item.Debugger;
import teamasm.moh.item.ItemOreDrop;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class ModItems {

    public static Item debugger;
    public static Item itemOreDrop;

    public static void init() {
        if (!ObfMapping.obfuscated) {
            debugger = new Debugger();
            GameRegistry.register(debugger.setRegistryName("debugger"));
        }

        itemOreDrop = new ItemOreDrop();
        GameRegistry.register(itemOreDrop.setRegistryName("oreDrop"));
    }

}
