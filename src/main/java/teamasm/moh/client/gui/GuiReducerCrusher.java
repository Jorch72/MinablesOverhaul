package teamasm.moh.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import teamasm.moh.container.ContainerReducerCrusher;
import teamasm.moh.tile.machines.teir1.TileReducerCrusher;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class GuiReducerCrusher extends GuiContainer{
    private TileReducerCrusher tileReducerCrusher;

    public GuiReducerCrusher(InventoryPlayer inventoryPlayer, TileReducerCrusher tileReducerCrusher) {
        super(new ContainerReducerCrusher(inventoryPlayer, tileReducerCrusher));
        this.tileReducerCrusher = tileReducerCrusher;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
