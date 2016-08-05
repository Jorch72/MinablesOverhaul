package teamasm.moh.client;

import codechicken.lib.model.loader.IBakedModelLoader;
import com.google.common.collect.ImmutableList.Builder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import teamasm.moh.reference.Reference;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class MinablesOverhaulBakedModelLoader implements IBakedModelLoader {

    public static final MinablesOverhaulBakedModelLoader INSTANCE = new MinablesOverhaulBakedModelLoader();

    public static class MinablesOverhaulKeyProvider implements IModKeyProvider {

        public static final MinablesOverhaulKeyProvider INSTANCE = new MinablesOverhaulKeyProvider();

        @Override
        public String getMod() {
            return "minablesoverhaul";
        }

        @Override
        public String createKey(ItemStack stack) {
            return null;
        }

        @Override
        public String createKey(IBlockState state, EnumFacing face) {
            return null;
        }
    }

    @Override
    public IModKeyProvider createKeyProvider() {
        return MinablesOverhaulKeyProvider.INSTANCE;
    }

    @Override
    public void addTextures(Builder<ResourceLocation> builder) {
        String oresLocation = Reference.MOD_PREFIX + "blocks/ores/";
        //Base
        builder.add(new ResourceLocation(oresLocation + "oreBase"));
        builder.add(new ResourceLocation(oresLocation + "oreUnknown"));

        //"normal"
        String normalLocation = oresLocation + "normal/";
        builder.add(new ResourceLocation(normalLocation + "oreGold"));
        builder.add(new ResourceLocation(normalLocation + "oreIron"));

        //"misc"
        String miscLocation = oresLocation + "misc/";
        builder.add(new ResourceLocation(miscLocation + "oreCoal"));
        builder.add(new ResourceLocation(miscLocation + "oreDiamond"));
        builder.add(new ResourceLocation(miscLocation + "oreEmerald"));
        builder.add(new ResourceLocation(miscLocation + "oreLapis"));
        builder.add(new ResourceLocation(miscLocation + "oreRedstone"));
    }

    @Override
    public IBakedModel bakeModel(String key) {
        return null;
    }
}
