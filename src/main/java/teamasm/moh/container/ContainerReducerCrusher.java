package teamasm.moh.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import teamasm.moh.tile.machines.teir1.TileReducerCrusher;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class ContainerReducerCrusher extends Container{
    private TileReducerCrusher tileReducerCrusher;

    public ContainerReducerCrusher(InventoryPlayer inventoryPlayer, TileReducerCrusher tileReducerCrusher){
        this.tileReducerCrusher = tileReducerCrusher;

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
