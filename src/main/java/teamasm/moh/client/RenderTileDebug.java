package teamasm.moh.client;

import codechicken.lib.colour.Colour;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.TextureUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import teamasm.moh.reference.OreRegistry;
import teamasm.moh.reference.Reference;
import teamasm.moh.tile.TileDebug;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by covers1624 on 8/7/2016.
 */
public class RenderTileDebug extends TileEntitySpecialRenderer<TileDebug> {

    @Override
    public void renderTileEntityAt(TileDebug te, double x, double y, double z, float partialTicks, int destroyStage) {
        TextureUtils.bindBlockTexture();
        GlStateManager.pushMatrix();
        TextureUtils.dissableBlockMipmap();

        Map<String, Colour> nameToColour = OreRegistry.INSTANCE.getOreColourMap();
        String name = "oreCopper";
        try {
            int index = (int) Minecraft.getMinecraft().theWorld.getWorldTime() / 10 % nameToColour.size();
            name = new ArrayList<String>(nameToColour.keySet()).get(index);
        } catch (Exception e){

        }
        TextureAtlasSprite icon = OreDustTextureManager.getSprite(new ResourceLocation(Reference.MOD_ID, "textures/items/ore/generated/" + name));

        VertexBuffer buffer = CCRenderState.startDrawing(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);

        buffer.setTranslation(x, y + 0.001, z);
        buffer.pos(1, 0, 0).tex(icon.getMinU(), icon.getMinV()).normal(0, 1, 0).endVertex();
        buffer.pos(0, 0, 0).tex(icon.getMinU(), icon.getMaxV()).normal(0, 1, 0).endVertex();
        buffer.pos(0, 0, 1).tex(icon.getMaxU(), icon.getMaxV()).normal(0, 1, 0).endVertex();
        buffer.pos(1, 0, 1).tex(icon.getMaxU(), icon.getMinV()).normal(0, 1, 0).endVertex();
        buffer.setTranslation(0, 0, 0);


        CCRenderState.draw();

        TextureUtils.restoreBlockMipmap();
        GlStateManager.popMatrix();
    }
}
