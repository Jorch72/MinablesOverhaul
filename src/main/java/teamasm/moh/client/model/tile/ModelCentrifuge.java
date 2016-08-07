package teamasm.moh.client.model.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SeperatorAutomatic - brandon3055
 * Created using Tabula 5.1.0
 */
public class ModelCentrifuge extends ModelBase {
    public ModelRenderer crankShaft;
    public ModelRenderer motorFront;
    public ModelRenderer motorBack;
    public ModelRenderer motorTop;
    public ModelRenderer motorBottom;
    public ModelRenderer supportLeft;
    public ModelRenderer supportRight;
    public ModelRenderer base;
    public ModelRenderer stator;
    public ModelRenderer statorMagnet1;
    public ModelRenderer statorMagnet2;
    public ModelRenderer statorMagnet3;
    public ModelRenderer statorMagnet4;
    public ModelRenderer drumBottom;
    public ModelRenderer drumTop;
    public ModelRenderer drumFront;
    public ModelRenderer drumRight;
    public ModelRenderer drumLeft;
    public ModelRenderer drumBack;
    public ModelRenderer motorCoil1;
    public ModelRenderer motorCoil2;
    public ModelRenderer motorCoil3;
    public ModelRenderer motorCoil4;

    public ModelCentrifuge() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.drumTop = new ModelRenderer(this, 0, 24);
        this.drumTop.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumTop.addBox(-3.5F, -5.0F, -3.5F, 7, 1, 7, 0.0F);
        this.motorCoil2 = new ModelRenderer(this, 0, 14);
        this.motorCoil2.setRotationPoint(0.5F, 3.0F, -1.0F);
        this.motorCoil2.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.statorMagnet2 = new ModelRenderer(this, 0, 18);
        this.statorMagnet2.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.statorMagnet2.addBox(-0.5F, 1.0F, -0.5F, 3, 1, 1, 0.0F);
        this.motorCoil3 = new ModelRenderer(this, 0, 14);
        this.motorCoil3.setRotationPoint(1.5F, 2.0F, -1.0F);
        this.motorCoil3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.motorBottom = new ModelRenderer(this, 0, 0);
        this.motorBottom.setRotationPoint(4.0F, 20.0F, -3.0F);
        this.motorBottom.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1, 0.0F);
        this.setRotateAngle(motorBottom, 1.5707963267948966F, 0.0F, 0.0F);
        this.motorCoil4 = new ModelRenderer(this, 0, 14);
        this.motorCoil4.setRotationPoint(0.5F, 2.0F, 1.0F);
        this.motorCoil4.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.drumBack = new ModelRenderer(this, 32, 20);
        this.drumBack.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumBack.addBox(-3.5F, -5.0F, 3.5F, 7, 10, 1, 0.0F);
        this.statorMagnet4 = new ModelRenderer(this, 0, 18);
        this.statorMagnet4.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.statorMagnet4.addBox(-0.5F, -0.5F, 1.0F, 3, 1, 1, 0.0F);
        this.motorBack = new ModelRenderer(this, 0, 0);
        this.motorBack.setRotationPoint(4.0F, 12.0F, 3.0F);
        this.motorBack.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1, 0.0F);
        this.drumRight = new ModelRenderer(this, 32, 20);
        this.drumRight.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumRight.addBox(-3.5F, -5.0F, -4.5F, 7, 10, 1, 0.0F);
        this.setRotateAngle(drumRight, 0.0F, -1.5707963267948966F, 0.0F);
        this.drumBottom = new ModelRenderer(this, 0, 24);
        this.drumBottom.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumBottom.addBox(-3.5F, 4.0F, -3.5F, 7, 1, 7, 0.0F);
        this.stator = new ModelRenderer(this, 1, 0);
        this.stator.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.stator.addBox(-0.5F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.supportLeft = new ModelRenderer(this, 26, 0);
        this.supportLeft.setRotationPoint(-8.0F, 14.0F, -2.0F);
        this.supportLeft.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4, 0.0F);
        this.supportRight = new ModelRenderer(this, 26, 0);
        this.supportRight.setRotationPoint(3.0F, 14.0F, -2.0F);
        this.supportRight.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4, 0.0F);
        this.statorMagnet3 = new ModelRenderer(this, 0, 18);
        this.statorMagnet3.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.statorMagnet3.addBox(-0.5F, -0.5F, -2.0F, 3, 1, 1, 0.0F);
        this.motorCoil1 = new ModelRenderer(this, 0, 14);
        this.motorCoil1.setRotationPoint(0.5F, 3.0F, 1.0F);
        this.motorCoil1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.drumLeft = new ModelRenderer(this, 32, 20);
        this.drumLeft.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumLeft.addBox(-3.5F, -5.0F, -4.5F, 7, 10, 1, 0.0F);
        this.setRotateAngle(drumLeft, 0.0F, 1.5707963267948966F, 0.0F);
        this.drumFront = new ModelRenderer(this, 32, 20);
        this.drumFront.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.drumFront.addBox(-3.5F, -5.0F, -4.5F, 7, 10, 1, 0.0F);
        this.motorTop = new ModelRenderer(this, 0, 0);
        this.motorTop.setRotationPoint(3.0F, 13.0F, -3.0F);
        this.motorTop.addBox(1.0F, 0.0F, 0.0F, 4, 6, 1, 0.0F);
        this.setRotateAngle(motorTop, 1.5707963267948966F, 0.0F, 0.0F);
        this.statorMagnet1 = new ModelRenderer(this, 0, 18);
        this.statorMagnet1.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.statorMagnet1.addBox(-0.5F, -2.0F, -0.5F, 3, 1, 1, 0.0F);
        this.crankShaft = new ModelRenderer(this, 26, 0);
        this.crankShaft.setRotationPoint(4.0F, 16.0F, 0.0F);
        this.crankShaft.addBox(-11.5F, -0.5F, -0.5F, 12, 1, 1, 0.0F);
        this.motorFront = new ModelRenderer(this, 0, 0);
        this.motorFront.setRotationPoint(4.0F, 12.0F, -4.0F);
        this.motorFront.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1, 0.0F);
        this.base = new ModelRenderer(this, 12, 0);
        this.base.setRotationPoint(-8.0F, 23.0F, -7.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 12, 1, 14, 0.0F);
        this.crankShaft.addChild(this.drumTop);
        this.motorBack.addChild(this.motorCoil2);
        this.crankShaft.addChild(this.statorMagnet2);
        this.motorTop.addChild(this.motorCoil3);
        this.motorBottom.addChild(this.motorCoil4);
        this.crankShaft.addChild(this.drumBack);
        this.crankShaft.addChild(this.statorMagnet4);
        this.crankShaft.addChild(this.drumRight);
        this.crankShaft.addChild(this.drumBottom);
        this.crankShaft.addChild(this.stator);
        this.crankShaft.addChild(this.statorMagnet3);
        this.motorFront.addChild(this.motorCoil1);
        this.crankShaft.addChild(this.drumLeft);
        this.crankShaft.addChild(this.drumFront);
        this.crankShaft.addChild(this.statorMagnet1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float animationRotation, float scale) {
        animationRotation /= 3F;
        this.crankShaft.rotateAngleX = animationRotation;
        this.motorBottom.render(scale);
        this.motorBack.render(scale);
        this.supportLeft.render(scale);
        this.supportRight.render(scale);
        this.motorTop.render(scale);
        this.crankShaft.render(scale);
        this.motorFront.render(scale);
        this.base.render(scale);
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
