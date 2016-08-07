package teamasm.moh.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.FakePlayer;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.api.tile.IManualMachine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public abstract class TileProcessorManual extends TileProcessorBase implements IManualMachine, ITickable {

    public TileProcessorManual() {
        cycleTime = 40;
    }

    public int workTicks = 0;
    public Map<String, Float> workCache = new HashMap<String, Float>();

    @Override
    public final void update() {
        if (worldObj.isRemote) {
            rotation += rotationSpeed;
            return;
        }

        int work = workTicks;

        if (workTicks > 0) {
            updateWork();
            workTicks--;
        }

        if (workTicks == 0 && work > 0) {
            sendShortToClient(0, 0);
        }
    }

    public void updateWork() {
        if (workCache.isEmpty() && workTicks > 0) {
            workTicks = 0;
            sendShortToClient(0, 0);
            return;
        }
        else if (!workCache.isEmpty() && progress < cycleTime) {
            double speed = getWorkSpeed();
            progress += speed;
        }
        else if (!workCache.isEmpty() && progress >= cycleTime) {
            tryProcessOutput();
        }
    }

    protected abstract void tryProcessOutput();

    @Override
    public void onTileActivated(EntityPlayer player) {
        if (player instanceof FakePlayer) {
            return;
        }

        if (workTicks <= 10) {
            workTicks = 20;
        }

        if (!handleClick(player)) {
            workTicks = 0;
        }
        else {
            sendShortToClient(0, (int)(rotationSpeed * 1000F));
        }
    }

    protected double getWorkSpeed() {
        double speed = Math.min(1, workTicks / (double) 10);
        if (Math.abs(rotationSpeed - speed) > 0.01) {
            rotationSpeed = (float)speed;
            sendShortToClient(0, (int)(rotationSpeed * 1000F));
        }
        return speed;
    }

    public abstract boolean handleClick(EntityPlayer player);

    @Override
    public float getAnimRotStat(float partialTicks) {
        return workTicks == 0 ? 0 : rotation + (rotationSpeed * partialTicks);
    }

    @Override
    public IMOHRecipe checkForValidRecipe() {
        return null;
    }
}
