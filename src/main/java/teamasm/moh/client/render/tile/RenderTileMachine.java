package teamasm.moh.client.render.tile;

import codechicken.lib.render.TextureUtils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import teamasm.moh.api.tile.ITileItemRenderer;
import teamasm.moh.tile.TileProcessorBase;
import teamasm.moh.util.RotationHelper;

/**
 * Created by covers1624 on 8/7/2016.
 */
public class RenderTileMachine extends TileEntitySpecialRenderer<TileProcessorBase> implements ITileItemRenderer {

    private final ModelBase model;
    private final ResourceLocation texture;

    public RenderTileMachine(ModelBase model, ResourceLocation texture) {
        this.model = model;
        this.texture = texture;
    }

    @Override
    public void renderTileEntityAt(TileProcessorBase te, double x, double y, double z, float partialTicks, int destroyStage) {
        render(x, y, z, RotationHelper.sideToEntity(te.getRotation()), -te.getAnimRotStat(partialTicks));
    }

    @Override
    public void render(double x, double y, double z, int rotation, float animationRotation) {
        TextureUtils.changeTexture(texture);
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
        GlStateManager.rotate(180, 0, 1, 0);
        GlStateManager.rotate(180, 0, 0, 1);
        GlStateManager.rotate((rotation % 4) * 90, 0.0F, 1.0F, 0.0F);
        model.render(null, 0, 0, 0, 0, animationRotation, 1F / 16F);
        GlStateManager.popMatrix();
        TextureUtils.bindBlockTexture();
    }
}
