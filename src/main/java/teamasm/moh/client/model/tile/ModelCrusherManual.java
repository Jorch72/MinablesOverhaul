package teamasm.moh.client.model.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * crusherManual - brandon3055
 * Created using Tabula 5.1.0
 */
public class ModelCrusherManual extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer crushPlate;
    public ModelRenderer crankShaft;
    public ModelRenderer backPlate;
    public ModelRenderer crushPlateStatic;
    public ModelRenderer crusherBaseStatic;
    public ModelRenderer crusherBase;
    public ModelRenderer crusherSupportLeft;
    public ModelRenderer crusherSupportRight;
    public ModelRenderer crankSupportRight;
    public ModelRenderer crankSupportLeft;
    public ModelRenderer crusherPin;
    public ModelRenderer crankHandleA;
    public ModelRenderer crankHandleB;
    public ModelRenderer crancNotch;

    public ModelCrusherManual() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.crankHandleA = new ModelRenderer(this, 32, 29);
        this.crankHandleA.setRotationPoint(9.0F, 0.0F, 0.0F);
        this.crankHandleA.addBox(-0.5F, -0.5F, -0.5F, 6, 1, 1, 0.0F);
        this.setRotateAngle(crankHandleA, 0.0F, -1.5707963267948966F, 0.0F);
        this.crancNotch = new ModelRenderer(this, 32, 30);
        this.crancNotch.setRotationPoint(3.5F, -1.0F, 0.0F);
        this.crancNotch.addBox(-0.5F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(-7.0F, 23.0F, -7.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 14, 1, 14, 0.0F);
        this.crushPlateStatic = new ModelRenderer(this, 0, 0);
        this.crushPlateStatic.setRotationPoint(2.5F, -2.5F, 9.7F);
        this.crushPlateStatic.addBox(0.0F, -11.5F, -0.5F, 5, 12, 1, 0.0F);
        this.setRotateAngle(crushPlateStatic, -0.10471975511965977F, 0.0F, 0.0F);
        this.backPlate = new ModelRenderer(this, 0, 15);
        this.backPlate.setRotationPoint(2.0F, 3.0F, 11.0F);
        this.backPlate.addBox(0.0F, -13.0F, 0.0F, 6, 10, 1, 0.0F);
        this.crankSupportLeft = new ModelRenderer(this, 0, 18);
        this.crankSupportLeft.setRotationPoint(1.0F, 0.0F, 3.0F);
        this.crankSupportLeft.addBox(0.0F, -11.0F, 0.0F, 1, 11, 3, 0.0F);
        this.crusherSupportRight = new ModelRenderer(this, 4, 22);
        this.crusherSupportRight.setRotationPoint(8.0F, 0.0F, 6.0F);
        this.crusherSupportRight.addBox(0.0F, -4.0F, 0.0F, 1, 4, 6, 0.0F);
        this.crankHandleB = new ModelRenderer(this, 32, 30);
        this.crankHandleB.setRotationPoint(10.0F, 0.0F, 5.0F);
        this.crankHandleB.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.crushPlate = new ModelRenderer(this, 0, 0);
        this.crushPlate.setRotationPoint(-4.4F, 20.5F, -0.2F);
        this.crushPlate.addBox(0.0F, -11.5F, -0.5F, 5, 11, 1, 0.0F);
        this.setRotateAngle(crushPlate, 0.15009831567151233F, 0.0F, 0.0F);
        this.crusherSupportLeft = new ModelRenderer(this, 4, 22);
        this.crusherSupportLeft.setRotationPoint(1.0F, 0.0F, 6.0F);
        this.crusherSupportLeft.addBox(0.0F, -4.0F, 0.0F, 1, 4, 6, 0.0F);
        this.crusherPin = new ModelRenderer(this, 32, 29);
        this.crusherPin.setRotationPoint(-2.0F, -0.5F, -0.5F);
        this.crusherPin.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.crusherBaseStatic = new ModelRenderer(this, 42, 0);
        this.crusherBaseStatic.setRotationPoint(2.0F, -2.0F, 9.1F);
        this.crusherBaseStatic.addBox(0.0F, 0.0F, 0.0F, 6, 2, 2, 0.0F);
        this.crankShaft = new ModelRenderer(this, 33, 30);
        this.crankShaft.setRotationPoint(-6.0F, 13.5F, -2.5F);
        this.crankShaft.addBox(-0.5F, -0.5F, -0.5F, 9, 1, 1, 0.0F);
        this.crankSupportRight = new ModelRenderer(this, 0, 18);
        this.crankSupportRight.setRotationPoint(8.0F, 0.0F, 3.0F);
        this.crankSupportRight.addBox(0.0F, -11.0F, 0.0F, 1, 11, 3, 0.0F);
        this.crusherBase = new ModelRenderer(this, 42, 0);
        this.crusherBase.setRotationPoint(2.0F, -2.0F, 5.4F);
        this.crusherBase.addBox(0.0F, 0.0F, 0.0F, 6, 2, 2, 0.0F);
        this.crankShaft.addChild(this.crankHandleA);
        this.crankShaft.addChild(this.crancNotch);
        this.base.addChild(this.crushPlateStatic);
        this.base.addChild(this.backPlate);
        this.base.addChild(this.crankSupportLeft);
        this.base.addChild(this.crusherSupportRight);
        this.crankShaft.addChild(this.crankHandleB);
        this.base.addChild(this.crusherSupportLeft);
        this.crushPlate.addChild(this.crusherPin);
        this.base.addChild(this.crusherBaseStatic);
        this.base.addChild(this.crankSupportRight);
        this.base.addChild(this.crusherBase);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float animationRotation, float f5) {
        this.base.render(f5);
        animationRotation /= 3F;
        this.crushPlate.rotateAngleX = 0.15009831F - (float) (Math.max(-1 * Math.sin(animationRotation), 0) * 0.1F);
        this.crushPlate.render(f5);
        this.crankShaft.rotateAngleX = animationRotation;
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
