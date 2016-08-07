package teamasm.moh.container.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import teamasm.moh.container.ContainerPoweredMachine;
import teamasm.moh.tile.machines.teir1.TileCrusher;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class ContainerReducerCrusher extends ContainerPoweredMachine {
    private TileCrusher tileReducerCrusher;

    public ContainerReducerCrusher(InventoryPlayer inventoryPlayer, TileCrusher tileReducerCrusher) {
        super(inventoryPlayer.player, tileReducerCrusher);
        this.tileReducerCrusher = tileReducerCrusher;

        addSlotToContainer(new Slot(tileReducerCrusher, 0, 41, 31));
        addSlotToContainer(new SlotFurnaceOutput(inventoryPlayer.player, tileReducerCrusher, 1, 121, 31));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
