package teamasm.moh.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
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

        addPlayersInventory();
        addPlayersHotbar();
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    private void addPlayersHotbar() {
        int i;
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 139));
        }
    }

    private void addPlayersInventory() {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 81 + i * 18));
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p, int i) {
        Slot slot = getSlot(i);

        if (slot != null && slot.getHasStack())
        {
            ItemStack stack = slot.getStack();
            ItemStack result = stack.copy();

            if (i >= 36){
                if (!mergeItemStack(stack, 0, 36, false)){
                    return null;
                }
            }else if (!tileInventoryBase.isItemValidForSlot(0, stack) || !mergeItemStack(stack, 36, 36 + tileInventoryBase.getSizeInventory(), false)){
                return null;
            }

            if (stack.stackSize == 0) {
                slot.putStack(null);
            }else{
                slot.onSlotChanged();
            }

            slot.onPickupFromSlot(entityPlayer, stack);

            return result;
        }

        return null;
    }
}
