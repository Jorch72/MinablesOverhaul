package teamasm.moh.container.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import teamasm.moh.container.ContainerPoweredMachine;
import teamasm.moh.tile.machines.teir1.TileScreenCoarse;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class ContainerScreenCoarse extends ContainerPoweredMachine {
    private TileScreenCoarse tileScreenCoarse;

    public ContainerScreenCoarse(InventoryPlayer inventoryPlayer, TileScreenCoarse tileScreenCoarse) {
        super(inventoryPlayer.player, tileScreenCoarse);
        this.tileScreenCoarse = tileScreenCoarse;

        addSlotToContainer(new Slot(tileScreenCoarse, 0, 41, 31));
        addSlotToContainer(new SlotFurnaceOutput(inventoryPlayer.player, tileScreenCoarse, 1, 121, 31));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
