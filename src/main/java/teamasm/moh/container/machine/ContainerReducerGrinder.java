package teamasm.moh.container.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import teamasm.moh.container.ContainerPoweredMachine;
import teamasm.moh.tile.machines.tier2.TileGrinder;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class ContainerReducerGrinder extends ContainerPoweredMachine {
    private TileGrinder tileReducerGrinder;

    public ContainerReducerGrinder(InventoryPlayer inventoryPlayer, TileGrinder tileReducerGrinder) {
        super(inventoryPlayer.player, tileReducerGrinder);
        this.tileReducerGrinder = tileReducerGrinder;

        addSlotToContainer(new Slot(tileReducerGrinder, 0, 41, 31));
        addSlotToContainer(new SlotFurnaceOutput(inventoryPlayer.player, tileReducerGrinder, 1, 121, 31));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
