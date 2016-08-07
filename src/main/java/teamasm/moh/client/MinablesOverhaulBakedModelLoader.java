package teamasm.moh.client;

import codechicken.lib.model.loader.IBakedModelLoader;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList.Builder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

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
            return null;//builder.toString();
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
        //No Textures! They are all generated in code!
    }

    @Override
    public IBakedModel bakeModel(String key) {
        return null;
    }
}
