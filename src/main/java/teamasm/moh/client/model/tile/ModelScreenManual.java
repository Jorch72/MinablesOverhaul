package teamasm.moh.client.model.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ScreenManual - brandon3055
 * Created using Tabula 5.1.0
 */
public class ModelScreenManual extends ModelBase {
    public ModelRenderer sideLeft;
    public ModelRenderer sideRight;
    public ModelRenderer grate1;
    public ModelRenderer transBottom;
    public ModelRenderer transTop;
    public ModelRenderer crankShaft;
    public ModelRenderer grate2;
    public ModelRenderer grate3;
    public ModelRenderer grate4;
    public ModelRenderer grate5;
    public ModelRenderer crankHandleA;
    public ModelRenderer crankHandleB;

    public ModelScreenManual() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.transBottom = new ModelRenderer(this, 12, 0);
        this.transBottom.setRotationPoint(2.0F, 17.0F, -8.0F);
        this.transBottom.addBox(1.0F, 0.0F, -1.0F, 13, 6, 1, 0.0F);
        this.setRotateAngle(transBottom, 0.0F, -1.5707963267948966F, 0.0F);
        this.crankHandleA = new ModelRenderer(this, 19, 0);
        this.crankHandleA.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.crankHandleA.addBox(-0.5F, -0.5F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(crankHandleA, 0.0F, -1.5707963267948966F, 0.0F);
        this.sideLeft = new ModelRenderer(this, 12, 23);
        this.sideLeft.setRotationPoint(-8.0F, 16.0F, -8.0F);
        this.sideLeft.addBox(0.0F, 0.0F, -1.0F, 15, 8, 1, 0.0F);
        this.setRotateAngle(sideLeft, 0.0F, -1.5707963267948966F, 0.0F);
        this.crankHandleB = new ModelRenderer(this, 32, 30);
        this.crankHandleB.setRotationPoint(2.0F, 0.0F, 5.0F);
        this.crankHandleB.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.grate2 = new ModelRenderer(this, 16, 0);
        this.grate2.setRotationPoint(0.0F, 1.0F, 3.0F);
        this.grate2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.grate4 = new ModelRenderer(this, 16, 0);
        this.grate4.setRotationPoint(0.0F, 3.0F, 9.0F);
        this.grate4.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.grate3 = new ModelRenderer(this, 16, 0);
        this.grate3.setRotationPoint(0.0F, 2.0F, 6.0F);
        this.grate3.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.grate5 = new ModelRenderer(this, 16, 0);
        this.grate5.setRotationPoint(0.0F, 4.0F, 12.0F);
        this.grate5.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.crankShaft = new ModelRenderer(this, 0, 0);
        this.crankShaft.setRotationPoint(3.0F, 16.0F, 0.0F);
        this.crankShaft.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.transTop = new ModelRenderer(this, 17, 0);
        this.transTop.setRotationPoint(2.0F, 14.0F, -3.5F);
        this.transTop.addBox(1.0F, 0.0F, -1.0F, 5, 3, 1, 0.0F);
        this.setRotateAngle(transTop, 0.0F, -1.5707963267948966F, 0.0F);
        this.grate1 = new ModelRenderer(this, 16, 0);
        this.grate1.setRotationPoint(-7.0F, 17.0F, -7.0F);
        this.grate1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.sideRight = new ModelRenderer(this, 12, 23);
        this.sideRight.setRotationPoint(1.0F, 16.0F, -8.0F);
        this.sideRight.addBox(0.0F, 0.0F, -1.0F, 15, 8, 1, 0.0F);
        this.setRotateAngle(sideRight, 0.0F, -1.5707963267948966F, 0.0F);
        this.crankShaft.addChild(this.crankHandleA);
        this.crankShaft.addChild(this.crankHandleB);
        this.grate1.addChild(this.grate2);
        this.grate1.addChild(this.grate4);
        this.grate1.addChild(this.grate3);
        this.grate1.addChild(this.grate5);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float animationRotation, float scale) {
        animationRotation /= 3F;
        this.crankShaft.rotateAngleX = animationRotation;
        this.transBottom.render(scale);
        this.sideLeft.render(scale);
        this.grate1.rotationPointY = 17F + (float)(Math.sin(animationRotation) * 0.5F);
        this.crankShaft.render(scale);
        this.transTop.render(scale);
        this.grate1.render(scale);
        this.sideRight.render(scale);
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
