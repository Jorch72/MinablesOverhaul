package teamasm.moh.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class BaseContainer extends Container{
    EntityPlayer entityPlayer;

    public BaseContainer(EntityPlayer entityPlayer){
        super();
        this.entityPlayer = entityPlayer;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    public void addPlayersHotbar() {
        int i;
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(entityPlayer.inventory, i, 8 + i * 18, 142));
        }
    }

    public void addPlayersInventory() {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(entityPlayer.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }
}
