package teamasm.moh.client.render.tile;

import codechicken.lib.colour.Colour;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.texture.TextureUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import teamasm.moh.client.OreDustTextureManager;
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
        render(x, y, z, 30, true);


    }

    public static void render(double x, double y, double z, int div, boolean label) {
        TextureUtils.bindBlockTexture();
        GlStateManager.pushMatrix();

        Map<String, Colour> nameToColour = OreRegistry.INSTANCE.getOreColourMap();
        String name = "oreCopper";
        try {
            int index = (int) Minecraft.getMinecraft().theWorld.getWorldTime() / div % nameToColour.size();
            name = new ArrayList<String>(nameToColour.keySet()).get(index);
        } catch (Exception ignored) {
        }
        TextureAtlasSprite icon = OreDustTextureManager.getSprite(new ResourceLocation(Reference.MOD_ID, "textures/items/ore/generated/" + name));

        CCRenderState state = CCRenderState.instance();
        VertexBuffer buffer = state.startDrawing(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);

        buffer.setTranslation(x, y + 0.001, z);
        buffer.pos(1, 0, 0).tex(icon.getMinU(), icon.getMinV()).normal(0, 1, 0).endVertex();
        buffer.pos(0, 0, 0).tex(icon.getMinU(), icon.getMaxV()).normal(0, 1, 0).endVertex();
        buffer.pos(0, 0, 1).tex(icon.getMaxU(), icon.getMaxV()).normal(0, 1, 0).endVertex();
        buffer.pos(1, 0, 1).tex(icon.getMaxU(), icon.getMinV()).normal(0, 1, 0).endVertex();
        buffer.setTranslation(0, 0, 0);

        state.draw();

        if (label){
            RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

            float viewY = renderManager.playerViewY;
            float viewX = renderManager.playerViewX;
            boolean thirdPerson = renderManager.options.thirdPersonView == 2;
            EntityRenderer.func_189692_a(renderManager.getFontRenderer(), name, (float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F, 0, viewY, viewX, thirdPerson, false);
        }

        GlStateManager.popMatrix();
    }
}
