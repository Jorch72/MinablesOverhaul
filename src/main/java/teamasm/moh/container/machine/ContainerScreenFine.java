package teamasm.moh.container.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import teamasm.moh.container.ContainerPoweredMachine;
import teamasm.moh.tile.machines.teir1.TileScreenFine;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class ContainerScreenFine extends ContainerPoweredMachine {
    private TileScreenFine tileScreenFine;

    public ContainerScreenFine(InventoryPlayer inventoryPlayer, TileScreenFine tileScreenFine) {
        super(inventoryPlayer.player, tileScreenFine);
        this.tileScreenFine = tileScreenFine;

        addSlotToContainer(new Slot(tileScreenFine, 0, 41, 31));
        addSlotToContainer(new SlotFurnaceOutput(inventoryPlayer.player, tileScreenFine, 1, 121, 31));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
