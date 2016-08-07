package teamasm.moh.client;

import codechicken.lib.colour.Colour;
import codechicken.lib.colour.ColourARGB;
import codechicken.lib.render.TextureDataHolder;
import codechicken.lib.render.TextureSpecial;
import codechicken.lib.render.TextureUtils;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import teamasm.moh.reference.Reference;

/**
 * Created by covers1624 on 8/7/2016.
 */
public class CustomTextureDebug {

    public static final int colours[] = { 0xFFB3312C, 0xFF336600, 0xFF51301A, 0xFF253192, 0xFF7B2FBE, 0xFF287697, 0xFF848484, 0xFF434343, 0xFFD88198, 0xFF41CD34, 0xFFDECF2A, 0xFF6689D3, 0xFFC354CD, 0xFFEB8844 };

    private static TextureSpecial[] icons = new TextureSpecial[colours.length];
    private static int[] imageData = new int[256];

    public static void load(TextureMap map) {
        for (int i = 0; i < icons.length; i++) {
            icons[i] = TextureUtils.getTextureSpecial(map, Reference.MOD_PREFIX + "testTexture_" + i);
        }

        for (int i = 0; i < colours.length; i++) {
            processTexture(colours[i], i);
        }
    }

    public static TextureAtlasSprite getIcon(int index){
        return icons[index];
    }

    private static void processTexture(int colour, int i) {
        createTexture(new ColourARGB(colour));
        icons[i].addTexture(new TextureDataHolder(imageData, 16).copyData());
    }

    private static void createTexture(Colour colour) {
        for (int i = 0; i < 256; i++) {
            imageData[i] = colour.argb();
        }
    }

}
