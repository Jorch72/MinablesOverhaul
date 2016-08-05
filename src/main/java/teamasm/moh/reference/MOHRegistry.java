package teamasm.moh.reference;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brandon3055 on 6/08/2016.
 */
public class MOHRegistry {

    public static void init() {

    }


    /**
     * Returns a map of ores contained within the given stack and their purity.
     * @param stack the stack.
     * @return a map or ore name to purity.
     */
    public static Map<String, Float> getOres(ItemStack stack) {
        Map<String, Float> oreMap = new HashMap<String, Float>();
        NBTTagCompound compound = stack.getTagCompound();

        if (compound == null || !compound.hasKey("Ores")) {

            return oreMap;
        }

        NBTTagList oreList = compound.getTagList("Ores", 10);
        for (int i = 0; i < oreList.tagCount(); i++) {
            oreMap.put(oreList.getCompoundTagAt(i).getString("Name"), oreList.getCompoundTagAt(i).getFloat("Purity"));
        }

        return oreMap;
    }
}
