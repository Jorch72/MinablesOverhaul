package teamasm.moh.reference;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.WeightedRandom;
import teamasm.moh.init.ModItems;

import java.util.*;

/**
 * Created by brandon3055 on 6/08/2016.
 * This will be the registry that handles ALL the ores.
 */
public class OreRegistry {
    private Random random = new Random();

    private List<String> oreList = new ArrayList<String>();
    private List<WeightedOre> weightedOres = new ArrayList<WeightedOre>();
    private Map<String, Integer> nameToGenWeight = new HashMap<String, Integer>();
    private Map<String, Integer> nameToColour = new HashMap<String, Integer>();
    //todo Add getters as needed

    /**
     * The maximum possible ores per vein.
     */
    private static int maxOresPerOre = 5;

    /**
     * @param oreName The ore dictionary name of the ore.
     * @param genWeight The generation weight of the ore. Higher numbers have a higher chance to generate.
     * @param maxPurity The maximum purity this ore can spawn with. range 0 > 1. Should always be greater than 0. Purity of 1 = 2 ingots ber block.
     * @param oreColour The colour for the ore item.
     */
    public void registerOre(String oreName, int genWeight, float maxPurity, int oreColour) {
        oreList.add(oreName);
        nameToGenWeight.put(oreName, genWeight);
        nameToColour.put(oreName, oreColour);
        weightedOres.add(new WeightedOre(oreName, genWeight));
    }

    public List<String> getOreList() {
        return oreList;
    }

    public ItemStack getRandomOreForSeed(long seed) { //TODO refine this and add support for purity when that mechanic gets implemented.
        random.setSeed(seed);
        int oreCount = 1 + random.nextInt(maxOresPerOre);
        ItemStack stack = new ItemStack(ModItems.itemOreDrop);
        NBTTagList oreList = new NBTTagList();

        for (int i = 0; i < oreCount; i++) {

        }
        return null;
    }

    private static class WeightedOre extends WeightedRandom.Item {

        private final String name;

        public WeightedOre(String name, int itemWeightIn) {
            super(itemWeightIn);
            this.name = name;
        }
    }
}
