package teamasm.moh.tile;

import codechicken.lib.inventory.InventorySimple;
import codechicken.lib.inventory.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * Created by brandon3055 on 5/08/2016.
 * Base inventory tile.
 * - Call setInventory(n) in the constructor.
 */
public class TileInventoryBase extends TileEntity implements IInventory {

    private InventorySimple inventorySimple;
    public int progress;
    public boolean insertOverride = false;

    public TileInventoryBase() {
    }

    /**
     * @param slots      Number of inventory slots.
     * @param stackLimit Inventory stack size limit.
     */
    public void setInventory(int slots, int stackLimit) {
        inventorySimple = new InventorySimple(slots, stackLimit);
    }

    //region IInventory

    @Override
    public int getSizeInventory() {
        return inventorySimple.getSizeInventory();
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        return inventorySimple.getStackInSlot(index);
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = inventorySimple.decrStackSize(index, count);
        inventoryChanged();
        return stack;
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = inventorySimple.removeStackFromSlot(index);
        inventoryChanged();
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        inventorySimple.setInventorySlotContents(index, stack);
        markDirty();
        inventoryChanged();
    }

    @Override
    public int getInventoryStackLimit() {
        return inventorySimple.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (worldObj == null) {
            return false;
        } else if (worldObj.getTileEntity(pos) != this) {
            return false;
        }
        return player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) < 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index == 0 || insertOverride;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        inventorySimple.clear();
        inventoryChanged();
    }

    @Override
    public String getName() {
        return inventorySimple.getName();
    }

    @Override
    public boolean hasCustomName() {
        return inventorySimple.hasCustomName();
    }

    @Override
    public ITextComponent getDisplayName() {
        return inventorySimple.getDisplayName();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return getSizeInventory() > 0 && inventorySimple.hasCapability(capability, facing) || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (inventorySimple.hasCapability(capability, facing) && getSizeInventory() > 0) {
            return inventorySimple.getCapability(capability, facing);
        }
        return super.getCapability(capability, facing);
    }

    protected void inventoryChanged(){}

        //endregion

    //region Save

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("Items", InventoryUtils.writeItemStacksToTag(inventorySimple.items));
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ItemStack[] stacks = new ItemStack[inventorySimple.getSizeInventory()];
        InventoryUtils.readItemStacksFromTag(stacks, compound.getTagList("Items", 10));
        inventorySimple = new InventorySimple(stacks);
    }

    //endregion
}
