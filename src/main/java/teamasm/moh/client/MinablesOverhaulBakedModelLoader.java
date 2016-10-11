package teamasm.moh.client;

import codechicken.lib.model.SimplePerspectiveAwareBakedModel;
import codechicken.lib.model.loader.IBakedModelLoader;
import codechicken.lib.util.TransformUtils;
import codechicken.lib.util.ArrayUtils;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IPerspectiveAwareModel.MapWrapper;
import net.minecraftforge.client.model.ItemLayerModel;
import teamasm.moh.init.ModItems;
import teamasm.moh.reference.OreRegistry;
import teamasm.moh.reference.Reference;

import java.util.Map;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class MinablesOverhaulBakedModelLoader implements IBakedModelLoader {

    public static final MinablesOverhaulBakedModelLoader INSTANCE = new MinablesOverhaulBakedModelLoader();

    private static final Function<ResourceLocation, TextureAtlasSprite> oreBakedTextureGetter = new Function<ResourceLocation, TextureAtlasSprite>() {
        @Override
        public TextureAtlasSprite apply(ResourceLocation input) {
            return OreDustTextureManager.getSprite(input);
        }
    };

    public static class MinablesOverhaulKeyProvider implements IModKeyProvider {

        public static final MinablesOverhaulKeyProvider INSTANCE = new MinablesOverhaulKeyProvider();

        @Override
        public String getMod() {
            return "minablesoverhaul";
        }

        @Override
        public String createKey(ItemStack stack) {
            StringBuilder builder = new StringBuilder();
            if (stack.getItem().equals(ModItems.oreDust)) {
                builder.append("type=oreDust");
                String ore = OreRegistry.getFromIndex(stack.getMetadata());
                builder.append(",ore=").append(ore != null ? ore : "!UNKNOWN!");
            }
            return builder.toString();
        }

        @Override
        public String createKey(IBlockState state) {
            return null;
        }
    }

    @Override
    public IModKeyProvider createKeyProvider() {
        return MinablesOverhaulKeyProvider.INSTANCE;
    }

    @Override
    public void addTextures(Builder<ResourceLocation> builder) {
        //No Textures! They are all generated in code!
    }

    @Override
    public IBakedModel bakeModel(String key) {
        Map<String, String> kvMap = ArrayUtils.convertKeyValueArrayToMap(key.split(","));
        String type = kvMap.get("type");
        if (type.equals("oreDust")) {
            String oreName = kvMap.get("ore");
            IBakedModel layerModel = new ItemLayerModel(ImmutableList.of(new ResourceLocation(Reference.MOD_ID, "textures/items/ore/generated/" + oreName))).bake(TransformUtils.DEFAULT_ITEM, DefaultVertexFormats.ITEM, oreBakedTextureGetter);

            return new SimplePerspectiveAwareBakedModel(ImmutableList.copyOf(layerModel.getQuads(null, null, 0)), null, MapWrapper.getTransforms(TransformUtils.DEFAULT_ITEM));
        }
        return null;
    }
}
