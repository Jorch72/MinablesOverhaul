package teamasm.moh.client.model.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ScreenCoarse - Undefined
 * Created using Tabula 5.1.0
 */
public class ModelScreenCoarse extends ModelBase {
    public ModelRenderer sideLeft;
    public ModelRenderer sideRight;
    public ModelRenderer grate1;
    public ModelRenderer transBottom;
    public ModelRenderer transTop;
    public ModelRenderer crankShaft;
    public ModelRenderer motorFront;
    public ModelRenderer motorBack;
    public ModelRenderer motorTop;
    public ModelRenderer motorBottom;
    public ModelRenderer grate2;
    public ModelRenderer grate3;
    public ModelRenderer grate4;
    public ModelRenderer grate5;
    public ModelRenderer stator;
    public ModelRenderer statorMagnet1;
    public ModelRenderer statorMagnet2;
    public ModelRenderer statorMagnet3;
    public ModelRenderer statorMagnet4;
    public ModelRenderer motorCoil1;
    public ModelRenderer motorCoil2;
    public ModelRenderer motorCoil3;
    public ModelRenderer motorCoil4;

    public ModelScreenCoarse() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.stator = new ModelRenderer(this, 1, 0);
        this.stator.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.stator.addBox(-0.5F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.transBottom = new ModelRenderer(this, 12, 0);
        this.transBottom.setRotationPoint(2.0F, 17.0F, -8.0F);
        this.transBottom.addBox(1.0F, 0.0F, -1.0F, 13, 6, 1, 0.0F);
        this.setRotateAngle(transBottom, 0.0F, -1.5707963267948966F, 0.0F);
        this.statorMagnet2 = new ModelRenderer(this, 0, 18);
        this.statorMagnet2.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.statorMagnet2.addBox(-0.5F, 1.0F, -0.5F, 3, 1, 1, 0.0F);
        this.motorCoil2 = new ModelRenderer(this, 0, 14);
        this.motorCoil2.setRotationPoint(0.5F, 3.0F, -1.0F);
        this.motorCoil2.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.grate4 = new ModelRenderer(this, 16, 0);
        this.grate4.setRotationPoint(0.0F, 3.0F, 9.0F);
        this.grate4.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.crankShaft = new ModelRenderer(this, 0, 0);
        this.crankShaft.setRotationPoint(3.0F, 16.0F, 0.0F);
        this.crankShaft.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.sideRight = new ModelRenderer(this, 12, 23);
        this.sideRight.setRotationPoint(1.0F, 16.0F, -8.0F);
        this.sideRight.addBox(0.0F, 0.0F, -1.0F, 15, 8, 1, 0.0F);
        this.setRotateAngle(sideRight, 0.0F, -1.5707963267948966F, 0.0F);
        this.grate1 = new ModelRenderer(this, 16, 0);
        this.grate1.setRotationPoint(-7.0F, 17.0F, -7.0F);
        this.grate1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.statorMagnet4 = new ModelRenderer(this, 0, 18);
        this.statorMagnet4.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.statorMagnet4.addBox(-0.5F, -0.5F, 1.0F, 3, 1, 1, 0.0F);
        this.motorBottom = new ModelRenderer(this, 0, 0);
        this.motorBottom.setRotationPoint(3.0F, 20.0F, -3.0F);
        this.motorBottom.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1, 0.0F);
        this.setRotateAngle(motorBottom, 1.5707963267948966F, 0.0F, 0.0F);
        this.motorCoil4 = new ModelRenderer(this, 0, 14);
        this.motorCoil4.setRotationPoint(0.5F, 2.0F, 1.0F);
        this.motorCoil4.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.grate5 = new ModelRenderer(this, 16, 0);
        this.grate5.setRotationPoint(0.0F, 4.0F, 12.0F);
        this.grate5.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.grate3 = new ModelRenderer(this, 16, 0);
        this.grate3.setRotationPoint(0.0F, 2.0F, 6.0F);
        this.grate3.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.motorCoil3 = new ModelRenderer(this, 0, 14);
        this.motorCoil3.setRotationPoint(0.5F, 2.0F, -1.0F);
        this.motorCoil3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.motorCoil1 = new ModelRenderer(this, 0, 14);
        this.motorCoil1.setRotationPoint(0.5F, 3.0F, 1.0F);
        this.motorCoil1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.sideLeft = new ModelRenderer(this, 12, 23);
        this.sideLeft.setRotationPoint(-8.0F, 16.0F, -8.0F);
        this.sideLeft.addBox(0.0F, 0.0F, -1.0F, 15, 8, 1, 0.0F);
        this.setRotateAngle(sideLeft, 0.0F, -1.5707963267948966F, 0.0F);
        this.statorMagnet3 = new ModelRenderer(this, 0, 18);
        this.statorMagnet3.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.statorMagnet3.addBox(-0.5F, -0.5F, -2.0F, 3, 1, 1, 0.0F);
        this.statorMagnet1 = new ModelRenderer(this, 0, 18);
        this.statorMagnet1.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.statorMagnet1.addBox(-0.5F, -2.0F, -0.5F, 3, 1, 1, 0.0F);
        this.motorFront = new ModelRenderer(this, 0, 0);
        this.motorFront.setRotationPoint(3.0F, 12.0F, -4.0F);
        this.motorFront.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1, 0.0F);
        this.motorBack = new ModelRenderer(this, 0, 0);
        this.motorBack.setRotationPoint(3.0F, 12.0F, 3.0F);
        this.motorBack.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1, 0.0F);
        this.grate2 = new ModelRenderer(this, 16, 0);
        this.grate2.setRotationPoint(0.0F, 1.0F, 3.0F);
        this.grate2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.transTop = new ModelRenderer(this, 17, 0);
        this.transTop.setRotationPoint(2.0F, 14.0F, -3.5F);
        this.transTop.addBox(1.0F, 0.0F, -1.0F, 5, 3, 1, 0.0F);
        this.setRotateAngle(transTop, 0.0F, -1.5707963267948966F, 0.0F);
        this.motorTop = new ModelRenderer(this, 0, 0);
        this.motorTop.setRotationPoint(3.0F, 13.0F, -3.0F);
        this.motorTop.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1, 0.0F);
        this.setRotateAngle(motorTop, 1.5707963267948966F, 0.0F, 0.0F);
        this.crankShaft.addChild(this.stator);
        this.crankShaft.addChild(this.statorMagnet2);
        this.motorBack.addChild(this.motorCoil2);
        this.grate1.addChild(this.grate4);
        this.crankShaft.addChild(this.statorMagnet4);
        this.motorBottom.addChild(this.motorCoil4);
        this.grate1.addChild(this.grate5);
        this.grate1.addChild(this.grate3);
        this.motorTop.addChild(this.motorCoil3);
        this.motorFront.addChild(this.motorCoil1);
        this.crankShaft.addChild(this.statorMagnet3);
        this.crankShaft.addChild(this.statorMagnet1);
        this.grate1.addChild(this.grate2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float animationRotation, float scale) {
        animationRotation /= 3F;
        this.crankShaft.rotateAngleX = animationRotation;
        this.transBottom.render(scale);
        this.crankShaft.render(scale);
        this.sideRight.render(scale);

        grate1.rotationPointY = 17F + (float)(Math.sin(animationRotation) * 0.5F);
        this.grate1.render(scale);

        this.motorBottom.render(scale);
        this.sideLeft.render(scale);
        this.motorFront.render(scale);
        this.motorBack.render(scale);
        this.transTop.render(scale);
        this.motorTop.render(scale);
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
