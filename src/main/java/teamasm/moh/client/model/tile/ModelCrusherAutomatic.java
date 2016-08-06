package teamasm.moh.client.model.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by covers1624 on 8/6/2016.
 */
public class ModelCrusherAutomatic extends ModelBase{

    public ModelRenderer base;
    public ModelRenderer backPlate;
    public ModelRenderer crushPlate;
    public ModelRenderer crushPlateStatic;
    public ModelRenderer crusherBaseStatic;
    public ModelRenderer crusherBase;
    public ModelRenderer crusherSupportLeft;
    public ModelRenderer crusherSupportRight;
    public ModelRenderer crankSupportRight;
    public ModelRenderer crankShaft;
    public ModelRenderer crankSupportLeft;
    public ModelRenderer motorFront;
    public ModelRenderer motorBack;
    public ModelRenderer motorTop;
    public ModelRenderer motorBottom;
    public ModelRenderer motorCoil1;
    public ModelRenderer motorCoil2;
    public ModelRenderer motorCoil3;
    public ModelRenderer motorCoil4;
    public ModelRenderer crusherPin;
    public ModelRenderer crankNotch;
    public ModelRenderer stator;
    public ModelRenderer statorMagnet1;
    public ModelRenderer statorMagnet2;
    public ModelRenderer statorMagnet3;
    public ModelRenderer statorMagnet4;

    public ModelCrusherAutomatic() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.motorCoil2 = new ModelRenderer(this, 43, 11);
        this.motorCoil2.setRotationPoint(9.5F, -10.5F, 6.5F);
        this.motorCoil2.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.crankSupportRight = new ModelRenderer(this, 0, 18);
        this.crankSupportRight.setRotationPoint(8.0F, 0.0F, 3.0F);
        this.crankSupportRight.addBox(0.0F, -11.0F, 0.0F, 1, 11, 3, 0.0F);
        this.crushPlate = new ModelRenderer(this, 0, 0);
        this.crushPlate.setRotationPoint(2.6F, -2.5F, 6.8F);
        this.crushPlate.addBox(0.0F, -11.5F, -0.5F, 5, 11, 1, 0.0F);
        this.setRotateAngle(crushPlate, 0.15009831567151233F, 0.0F, 0.0F);
        this.stator = new ModelRenderer(this, 1, 0);
        this.stator.setRotationPoint(9.0F, 0.0F, 0.0F);
        this.stator.addBox(-0.5F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
        this.crankSupportLeft = new ModelRenderer(this, 0, 18);
        this.crankSupportLeft.setRotationPoint(1.0F, 0.0F, 3.0F);
        this.crankSupportLeft.addBox(0.0F, -11.0F, 0.0F, 1, 11, 3, 0.0F);
        this.motorCoil4 = new ModelRenderer(this, 43, 11);
        this.motorCoil4.setRotationPoint(9.5F, -6.5F, 3.5F);
        this.motorCoil4.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(motorCoil4, 1.5707963267948966F, 0.0F, 0.0F);
        this.motorBack = new ModelRenderer(this, 0, 0);
        this.motorBack.setRotationPoint(9.0F, -13.5F, 7.5F);
        this.motorBack.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1, 0.0F);
        this.crushPlateStatic = new ModelRenderer(this, 0, 0);
        this.crushPlateStatic.setRotationPoint(2.5F, -2.5F, 9.7F);
        this.crushPlateStatic.addBox(0.0F, -11.5F, -0.5F, 5, 12, 1, 0.0F);
        this.setRotateAngle(crushPlateStatic, -0.10471975511965977F, 0.0F, 0.0F);
        this.crusherBaseStatic = new ModelRenderer(this, 42, 0);
        this.crusherBaseStatic.setRotationPoint(2.0F, -2.0F, 9.1F);
        this.crusherBaseStatic.addBox(0.0F, 0.0F, 0.0F, 6, 2, 2, 0.0F);
        this.motorCoil3 = new ModelRenderer(this, 43, 11);
        this.motorCoil3.setRotationPoint(9.5F, -11.5F, 3.5F);
        this.motorCoil3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(motorCoil3, 1.5707963267948966F, 0.0F, 0.0F);
        this.backPlate = new ModelRenderer(this, 0, 15);
        this.backPlate.setRotationPoint(2.0F, 3.0F, 11.0F);
        this.backPlate.addBox(0.0F, -13.0F, 0.0F, 6, 10, 1, 0.0F);
        this.crankShaft = new ModelRenderer(this, 33, 30);
        this.crankShaft.setRotationPoint(1.0F, -9.5F, 4.5F);
        this.crankShaft.addBox(-0.5F, -0.5F, -0.5F, 9, 1, 1, 0.0F);
        this.motorTop = new ModelRenderer(this, 0, 0);
        this.motorTop.setRotationPoint(9.0F, -12.5F, 1.5F);
        this.motorTop.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1, 0.0F);
        this.setRotateAngle(motorTop, 1.5707963267948966F, 0.0F, 0.0F);
        this.motorCoil1 = new ModelRenderer(this, 43, 11);
        this.motorCoil1.setRotationPoint(9.5F, -10.5F, 1.5F);
        this.motorCoil1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.motorFront = new ModelRenderer(this, 0, 0);
        this.motorFront.setRotationPoint(9.0F, -13.5F, 0.5F);
        this.motorFront.addBox(0.0F, 0.0F, 0.0F, 4, 8, 1, 0.0F);
        this.crusherSupportRight = new ModelRenderer(this, 4, 22);
        this.crusherSupportRight.setRotationPoint(8.0F, 0.0F, 6.0F);
        this.crusherSupportRight.addBox(0.0F, -4.0F, 0.0F, 1, 4, 6, 0.0F);
        this.crusherSupportLeft = new ModelRenderer(this, 4, 22);
        this.crusherSupportLeft.setRotationPoint(1.0F, 0.0F, 6.0F);
        this.crusherSupportLeft.addBox(0.0F, -4.0F, 0.0F, 1, 4, 6, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(-7.0F, 23.0F, -7.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 14, 1, 14, 0.0F);
        this.statorMagnet2 = new ModelRenderer(this, 45, 0);
        this.statorMagnet2.setRotationPoint(9.0F, 0.0F, 0.0F);
        this.statorMagnet2.addBox(-0.5F, 1.0F, -0.5F, 3, 1, 1, 0.0F);
        this.statorMagnet3 = new ModelRenderer(this, 45, 0);
        this.statorMagnet3.setRotationPoint(9.0F, 0.0F, 0.0F);
        this.statorMagnet3.addBox(-0.5F, -0.5F, -2.0F, 3, 1, 1, 0.0F);
        this.crusherBase = new ModelRenderer(this, 42, 0);
        this.crusherBase.setRotationPoint(2.0F, -2.0F, 5.4F);
        this.crusherBase.addBox(0.0F, 0.0F, 0.0F, 6, 2, 2, 0.0F);
        this.statorMagnet4 = new ModelRenderer(this, 45, 0);
        this.statorMagnet4.setRotationPoint(9.0F, 0.0F, 0.0F);
        this.statorMagnet4.addBox(-0.5F, -0.5F, 1.0F, 3, 1, 1, 0.0F);
        this.crusherPin = new ModelRenderer(this, 32, 29);
        this.crusherPin.setRotationPoint(-2.0F, -0.5F, -0.5F);
        this.crusherPin.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.statorMagnet1 = new ModelRenderer(this, 45, 0);
        this.statorMagnet1.setRotationPoint(9.0F, 0.0F, 0.0F);
        this.statorMagnet1.addBox(-0.5F, -2.0F, -0.5F, 3, 1, 1, 0.0F);
        this.motorBottom = new ModelRenderer(this, 0, 0);
        this.motorBottom.setRotationPoint(9.0F, -5.5F, 1.5F);
        this.motorBottom.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1, 0.0F);
        this.setRotateAngle(motorBottom, 1.5707963267948966F, 0.0F, 0.0F);
        this.crankNotch = new ModelRenderer(this, 32, 30);
        this.crankNotch.setRotationPoint(3.5F, -1.0F, 0.0F);
        this.crankNotch.addBox(-0.5F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
        this.base.addChild(this.motorCoil2);
        this.base.addChild(this.crankSupportRight);
        this.base.addChild(this.crushPlate);
        this.crankShaft.addChild(this.stator);
        this.base.addChild(this.crankSupportLeft);
        this.base.addChild(this.motorCoil4);
        this.base.addChild(this.motorBack);
        this.base.addChild(this.crushPlateStatic);
        this.base.addChild(this.crusherBaseStatic);
        this.base.addChild(this.motorCoil3);
        this.base.addChild(this.backPlate);
        this.base.addChild(this.crankShaft);
        this.base.addChild(this.motorTop);
        this.base.addChild(this.motorCoil1);
        this.base.addChild(this.motorFront);
        this.base.addChild(this.crusherSupportRight);
        this.base.addChild(this.crusherSupportLeft);
        this.crankShaft.addChild(this.statorMagnet2);
        this.crankShaft.addChild(this.statorMagnet3);
        this.base.addChild(this.crusherBase);
        this.crankShaft.addChild(this.statorMagnet4);
        this.crushPlate.addChild(this.crusherPin);
        this.crankShaft.addChild(this.statorMagnet1);
        this.base.addChild(this.motorBottom);
        this.crankShaft.addChild(this.crankNotch);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float scale) {
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
