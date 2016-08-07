package teamasm.moh.tile;

import codechicken.lib.tile.IGuiTile;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamasm.moh.MinablesOverhaul;
import teamasm.moh.reference.GuiIds;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public abstract class TileProcessEnergy extends TileProcessorBase implements IEnergyReceiver, IGuiTile, ITickable {

    public EnergyStorage energyStorage = new EnergyStorage(512000, 32000, 0);
    public boolean isIdle = false;
    public Map<String, Float> workCache = new HashMap<String, Float>();
    public int runCost = 100;
    public int particleSize = 0;

    @Override
    public void update() {
        if (worldObj.isRemote || isIdle) {
            rotation += rotationSpeed;
            return;
        }

        if (workCache.isEmpty() && !inputValid()) {
            isIdle = true;
            sendShortToClient(0, 0);
            return;
        }
        else if (workCache.isEmpty() && inputValid()) {
            addItemsToCache();
            return;
        }
        else if (!workCache.isEmpty() && progress < cycleTime) {
            double speed = getWorkSpeed();
            energyStorage.modifyEnergyStored(-(int) (speed * runCost));
            progress += speed;
        }
        else if (!workCache.isEmpty() && progress >= cycleTime) {
            tryProcessOutput();
        }
    }

    protected abstract void addItemsToCache();

    protected abstract void tryProcessOutput();

    protected abstract boolean inputValid();

    protected double getWorkSpeed() {
        double speed = Math.min(1, energyStorage.getEnergyStored() / (double)(energyStorage.getMaxEnergyStored() / 2));
        if (Math.abs(rotationSpeed - speed) > 0.01) {
            rotationSpeed = (float)speed;
            sendShortToClient(0, (int)(rotationSpeed * 1000F));
        }
        return speed;
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        return energyStorage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        energyStorage.writeToNBT(compound);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        energyStorage.readFromNBT(compound);
        super.readFromNBT(compound);
    }

    protected void openGui(GuiIds id, World world, BlockPos pos, EntityPlayer player) {
        player.openGui(MinablesOverhaul.instance, id.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void openGui(World world, BlockPos pos, EntityPlayer player) {
        openGui(getGuiID(), world, pos, player);
    }

    public abstract GuiIds getGuiID();

    @Override
    public float getAnimRotStat(float partialTicks) {
        return isIdle ? 0 : rotation + (rotationSpeed * partialTicks);
    }

    @Override
    public void writeSyncedNBT(NBTTagCompound compound) {
        compound.setBoolean("IsIdle", isIdle);
        super.writeSyncedNBT(compound);
    }

    @Override
    public void readSyncedNBT(NBTTagCompound compound) {
        isIdle = compound.getBoolean("IsIdle");
        super.readSyncedNBT(compound);
    }
}
