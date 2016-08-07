package teamasm.moh.tile;

import codechicken.lib.tile.IRotatableTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import teamasm.moh.api.recipe.IMOHRecipe;

import javax.annotation.Nullable;

/**
 * Created by brandon3055 on 5/08/2016.
 * This is the base tile for all "Processors" (Machines that turn one material into another via some process)
 * This dose not implement energy because there will be manual machines that do not use energy.
 */
public abstract class TileProcessorBase extends TileInventoryBase implements IRotatableTile {

    private EnumFacing facing = EnumFacing.NORTH;

    /**
     * The currently crafting recipe.
     */
    protected IMOHRecipe activeRecipe = null;
    /**
     * The progress in ticks for the current recipe.
     * This is a double because progress will slow when energy is low.
     */
    public double progress = 0;
    /**
     * The number of ticks required by the current recipe.
     */
    public int cycleTimeTime = 100;

    public abstract IMOHRecipe checkForValidRecipe();

    //region Rotation

    @Override
    public void doRotation(boolean shift) {
        facing = shift ? facing.rotateYCCW() : facing.rotateY();
        updateBlock();
    }

    @Override
    public EnumFacing getRotation() {
        return facing;
    }

    @Override
    public void setRotation(EnumFacing facing) {
        this.facing = facing;
    }

    @Override
    public boolean getPlacing() {
        return false;
    }

    @Override
    public void setPlacing(boolean placing) {}

    //endregion

    //region Save

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound compound = new NBTTagCompound();
        writeSyncedNBT(compound);
        return new SPacketUpdateTileEntity(this.pos, 0, compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readSyncedNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound compound = super.getUpdateTag();
        writeSyncedNBT(compound);
        return compound;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        readSyncedNBT(tag);
        super.handleUpdateTag(tag);
    }

    public void writeSyncedNBT(NBTTagCompound compound) {
        compound.setByte("Facing", (byte)facing.getIndex());
    }

    public void readSyncedNBT(NBTTagCompound compound) {
        facing = EnumFacing.getFront(compound.getByte("Facing"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setDouble("Progress", progress);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        progress = compound.getDouble("Progress");
        super.readFromNBT(compound);
    }

    //endregion

    //region Helper

    public void updateBlock(){
        IBlockState blockState = worldObj.getBlockState(pos);
        worldObj.notifyBlockUpdate(getPos(), blockState, blockState, 3);
    }

    //endregion

    //region Something to look at later (Not related to the comp)

    //Note: This looks interesting... Maby worth checking out in the future as an easy way to store custom data.
    @Override
    public NBTTagCompound getTileData() {
        return super.getTileData();
    }
    //endregion
}
