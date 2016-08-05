package teamasm.moh.client;

import codechicken.lib.model.loader.CCBakedModelLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class ClientHelper {

    public static void reloadChunks() {
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
        try {
            Field field = CCBakedModelLoader.class.getDeclaredField("modelCache");
            field.setAccessible(true);
            Map<String , IBakedModel> cache = (Map<String, IBakedModel>) field.get(null);
            cache.clear();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
