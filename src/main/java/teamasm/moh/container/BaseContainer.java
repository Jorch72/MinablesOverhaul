package teamasm.moh.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import teamasm.moh.tile.TileInventoryBase;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class BaseContainer extends Container {
    EntityPlayer entityPlayer;
    TileInventoryBase tileInventoryBase;

    public BaseContainer(EntityPlayer entityPlayer, TileInventoryBase tileInventoryBase) {
        super();
        this.entityPlayer = entityPlayer;
        this.tileInventoryBase = tileInventoryBase;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    public void addPlayersHotbar() {
        int i;
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 139));
        }
    }

    public void addPlayersInventory() {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 81 + i * 18));
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p, int i) {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i < this.tileInventoryBase.getSizeInventory()) {
                if(!(slot instanceof SlotFurnaceOutput)) {
                    if (!this.mergeItemStack(itemstack1, this.tileInventoryBase.getSizeInventory(), this.inventorySlots.size(), true)) {
                        return null;
                    }
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.tileInventoryBase.getSizeInventory(), false)) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
