package teamasm.moh.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import teamasm.moh.client.model.tile.ModelCrusherAutomatic;
import teamasm.moh.reference.Reference;
import teamasm.moh.tile.machines.teir1.TileReducerCrusher;

/**
 * Created by covers1624 on 8/6/2016.
 */
public class RenderTileCrusherAutomatic extends TileEntitySpecialRenderer<TileReducerCrusher> {

    private static final ModelCrusherAutomatic model = new ModelCrusherAutomatic();
    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_PREFIX + "textures/blocks/crusher.png");

    @Override
    public void renderTileEntityAt(TileReducerCrusher te, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
        GlStateManager.rotate(180, 0, 0, 1);
        bindTexture(texture);
//        IRotatableTile tile = (IRotatableTile) te;
//        GlStateManager.rotate(RotationHelper.sideToEntity(tile.getRotation()) * 90, 0.0F, 1.0F, 0.0F);
        //boolean active = ((IActiveTile)te).isActive();
        model.render(null, 0, 0, 0, 0, -(Minecraft.getMinecraft().theWorld.getWorldTime() + partialTicks), 1F / 16F);
        GlStateManager.popMatrix();
    }
}
