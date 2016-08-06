package teamasm.moh.container.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import teamasm.moh.container.BaseContainer;
import teamasm.moh.tile.machines.teir1.TileScreenCoarse;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class ContainerScreenCoarse extends BaseContainer {
    private TileScreenCoarse tileScreenCoarse;

    public ContainerScreenCoarse(InventoryPlayer inventoryPlayer, TileScreenCoarse tileScreenCoarse) {
        super(inventoryPlayer.player, tileScreenCoarse);
        this.tileScreenCoarse = tileScreenCoarse;

        addSlotToContainer(new Slot(tileScreenCoarse, 0, 41, 31));
        addSlotToContainer(new Slot(tileScreenCoarse, 1, 121, 31));
        addPlayersInventory();
        addPlayersHotbar();
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
