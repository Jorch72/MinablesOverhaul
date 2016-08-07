package teamasm.moh.container.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import teamasm.moh.container.ContainerPoweredMachine;
import teamasm.moh.tile.machines.teir1.TileCentrifuge;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class ContainerSeperatorGravity extends ContainerPoweredMachine {
    private TileCentrifuge separatorGravity;

    public ContainerSeperatorGravity(InventoryPlayer inventoryPlayer, TileCentrifuge separatorGravity) {
        super(inventoryPlayer.player, separatorGravity);
        this.separatorGravity = separatorGravity;

        addSlotToContainer(new Slot(separatorGravity, 0, 41, 35));

        for (int i = 0; i < 6; i++) {
            addSlotToContainer(new SlotFurnaceOutput(inventoryPlayer.player, separatorGravity, 1 + i, 103 + i % 3 * 18, 26 + i / 3 * 18));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
