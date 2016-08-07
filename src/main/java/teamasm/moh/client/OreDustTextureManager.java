package teamasm.moh.client;

import codechicken.lib.colour.Colour;
import codechicken.lib.render.TextureDataHolder;
import codechicken.lib.render.TextureSpecial;
import codechicken.lib.render.TextureUtils;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLLog;
import teamasm.moh.reference.OreRegistry;
import teamasm.moh.reference.Reference;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by covers1624 on 8/8/2016.
 */
public class OreDustTextureManager {

    private static Colour oreTextureGrad[];

    private static Map<ResourceLocation, TextureSpecial> spriteMap = new HashMap<ResourceLocation, TextureSpecial>();

    public static void loadTextures(TextureMap textureMap) {
        spriteMap.clear();

        oreTextureGrad = TextureUtils.loadTextureColours(new ResourceLocation(Reference.MOD_ID, "textures/items/ores/oreDustGrad.png"));

        for (Entry<String, Colour> entry : OreRegistry.INSTANCE.getOreColourMap().entrySet()) {
            ResourceLocation location = new ResourceLocation(Reference.MOD_ID, "textures/items/ore/generated/" + entry.getKey());
            FMLLog.info("Generating texture for item at location: " + location.toString());
            TextureSpecial texture = TextureUtils.getTextureSpecial(textureMap, location.toString());
            int[] imageData = applyGradient(entry.getValue());
            texture.addTexture(new TextureDataHolder(imageData, 16).copyData());
            spriteMap.put(location, texture);
        }
    }


    public static TextureAtlasSprite getSprite(ResourceLocation location){
        if (!spriteMap.containsKey(location)){
            return TextureUtils.getTexture(TextureMap.LOCATION_MISSING_TEXTURE);
        }
        return spriteMap.get(location);
    }


    public static int[] applyGradient(Colour gradient){
        int[] imageData = new int[256];
        for (int i = 0; i < 256; i++) {
            imageData[i] = oreTextureGrad[i].copy().multiply(gradient).argb();
        }
        return imageData;
    }

}
