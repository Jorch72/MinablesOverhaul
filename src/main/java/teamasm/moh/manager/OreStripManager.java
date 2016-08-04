package teamasm.moh.manager;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;
import org.apache.logging.log4j.Level;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by covers1624 on 8/4/2016.
 */
public class OreStripManager {

    private static final Map<ItemStack, List<String>> stackMap = new HashMap<ItemStack, List<String>>();
    private static final List<ResourceLocation> oresToStrip = new ArrayList<ResourceLocation>();

    public static void init() {
        for (Entry<ItemStack, List<String>> entry : stackMap.entrySet()) {
            for (String resource : entry.getValue()) {
                if (resource.startsWith("ore")) {
                    if (ForgeRegistries.BLOCKS.containsKey(entry.getKey().getItem().getRegistryName())) {
                        oresToStrip.add(entry.getKey().getItem().getRegistryName());
                        break;
                    } else {
                        FMLLog.log("MinablesOverhaul", Level.WARN, "Found ore in OreDictionary that has prefix of \"ore\" but has no block registered..");
                        break;
                    }
                }
            }
        }
    }

    public static boolean shouldStrip(Block block) {
        return oresToStrip.contains(block.getRegistryName());
    }

    @SubscribeEvent
    public static void oreRegisterEvent(OreRegisterEvent event) {
        ItemStack stack = event.getOre();
        String name = event.getName();
        List<String> names = new LinkedList<String>();
        if (stackMap.containsKey(stack)) {
            names = stackMap.get(stack);
        }
        names.add(name);
        stackMap.put(stack, names);
    }

}
