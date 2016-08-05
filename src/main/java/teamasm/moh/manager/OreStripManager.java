package teamasm.moh.manager;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Level;
import teamasm.moh.reference.MOHRegistry;
import teamasm.moh.reference.OreRegistry;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by covers1624 on 8/4/2016.
 */
public class OreStripManager {

    private static final Map<ItemStack, List<String>> stackMap = new HashMap<ItemStack, List<String>>();
    private static final List<String> oresToStrip = new ArrayList<String>();

    public static void init() {
        scanOreDictionaryBecauseForgeIsFuckingRetardedAndHasTheOreDictionaryRegisterEventAsAnInnerClassCausingInitialOreDictionaryEntriesToNotHaveAnEventFired();
        for (Entry<ItemStack, List<String>> entry : stackMap.entrySet()) {
            for (String resource : entry.getValue()) {
                if (resource.startsWith("ore") && OreRegistry.INSTANCE.isOreRegistered(resource)) {
                    if (ForgeRegistries.BLOCKS.containsKey(entry.getKey().getItem().getRegistryName())) {
                        Block block = ForgeRegistries.BLOCKS.getValue(entry.getKey().getItem().getRegistryName());
                        String stateString = block.getStateFromMeta(entry.getKey().getMetadata()).toString();
                        FMLLog.log("MinablesOverhaul", Level.INFO, "Adding Block {%s} with OreDictionary entry {%s} to the Ore strip list.", stateString, resource);
                        oresToStrip.add(stateString);
                        break;
                    } else {
                        FMLLog.log("MinablesOverhaul", Level.WARN, "Found ore in OreDictionary that has prefix of \"ore\" but has no block registered..");
                        break;
                    }
                }
            }
        }
    }

    public static boolean shouldStrip(IBlockState state) {//TODO needs to obey IBlockState
        return oresToStrip.contains(state.toString());
    }

    public static void scanOreDictionaryBecauseForgeIsFuckingRetardedAndHasTheOreDictionaryRegisterEventAsAnInnerClassCausingInitialOreDictionaryEntriesToNotHaveAnEventFired() {
        String[] ores = OreDictionary.getOreNames();
        for (String ore : ores) {
            for (ItemStack stack : OreDictionary.getOres(ore)) {
                List<String> names = new LinkedList<String>();
                if (stackMap.containsKey(stack)) {
                    names = stackMap.get(stack);
                }
                if (!names.contains(ore)) {
                    names.add(ore);
                    stackMap.put(stack, names);
                }
            }
        }
    }
}
