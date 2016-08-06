package teamasm.moh.client.model.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SeperatorManual - brandon3055
 * Created using Tabula 5.1.0
 */
public class ModelSeparatorManual extends ModelBase {
    public ModelRenderer crankShaft;
    public ModelRenderer supportLeft;
    public ModelRenderer supportRight;
    public ModelRenderer base;
    public ModelRenderer drumBottom;
    public ModelRenderer drumFront;
    public ModelRenderer drumBack;
    public ModelRenderer drumLeft;
    public ModelRenderer drumRight;
    public ModelRenderer drumTop;
    public ModelRenderer handleA;
    public ModelRenderer handleB;

    public ModelSeparatorManual() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.drumBack = new ModelRenderer(this, 32, 20);
        this.drumBack.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumBack.addBox(-3.5F, -5.0F, 3.5F, 7, 10, 1, 0.0F);
        this.drumRight = new ModelRenderer(this, 32, 20);
        this.drumRight.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumRight.addBox(-3.5F, -5.0F, -4.5F, 7, 10, 1, 0.0F);
        this.setRotateAngle(drumRight, 0.0F, -1.5707963267948966F, 0.0F);
        this.supportRight = new ModelRenderer(this, 26, 0);
        this.supportRight.setRotationPoint(3.0F, 14.0F, -2.0F);
        this.supportRight.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4, 0.0F);
        this.handleB = new ModelRenderer(this, 36, 24);
        this.handleB.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handleB.addBox(1.5F, 4.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(handleB, 1.5707963267948966F, 0.0F, 0.0F);
        this.base = new ModelRenderer(this, 12, 0);
        this.base.setRotationPoint(-8.0F, 23.0F, -7.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 12, 1, 14, 0.0F);
        this.supportLeft = new ModelRenderer(this, 26, 0);
        this.supportLeft.setRotationPoint(-8.0F, 14.0F, -2.0F);
        this.supportLeft.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4, 0.0F);
        this.drumFront = new ModelRenderer(this, 32, 20);
        this.drumFront.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumFront.addBox(-3.5F, -5.0F, -4.5F, 7, 10, 1, 0.0F);
        this.drumLeft = new ModelRenderer(this, 32, 20);
        this.drumLeft.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumLeft.addBox(-3.5F, -5.0F, -4.5F, 7, 10, 1, 0.0F);
        this.setRotateAngle(drumLeft, 0.0F, 1.5707963267948966F, 0.0F);
        this.drumBottom = new ModelRenderer(this, 0, 24);
        this.drumBottom.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumBottom.addBox(-3.5F, 4.0F, -3.5F, 7, 1, 7, 0.0F);
        this.crankShaft = new ModelRenderer(this, 26, 0);
        this.crankShaft.setRotationPoint(4.0F, 16.0F, 0.0F);
        this.crankShaft.addBox(-11.5F, -0.5F, -0.5F, 12, 1, 1, 0.0F);
        this.handleA = new ModelRenderer(this, 0, 0);
        this.handleA.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handleA.addBox(0.5F, -0.5F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(handleA, 1.5707963267948966F, 0.0F, 0.0F);
        this.drumTop = new ModelRenderer(this, 0, 24);
        this.drumTop.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumTop.addBox(-3.5F, -5.0F, -3.5F, 7, 1, 7, 0.0F);
        this.crankShaft.addChild(this.drumBack);
        this.crankShaft.addChild(this.drumRight);
        this.crankShaft.addChild(this.handleB);
        this.crankShaft.addChild(this.drumFront);
        this.crankShaft.addChild(this.drumLeft);
        this.crankShaft.addChild(this.drumBottom);
        this.crankShaft.addChild(this.handleA);
        this.crankShaft.addChild(this.drumTop);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.supportRight.render(f5);
        this.base.render(f5);
        this.supportLeft.render(f5);
        this.crankShaft.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
