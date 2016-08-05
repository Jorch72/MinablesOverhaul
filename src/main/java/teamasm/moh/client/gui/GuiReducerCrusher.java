package teamasm.moh.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import teamasm.moh.container.ContainerReducerCrusher;
import teamasm.moh.tile.machines.teir1.TileReducerCrusher;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class GuiReducerCrusher extends GuiContainer {
    private TileReducerCrusher tileReducerCrusher;

    public GuiReducerCrusher(InventoryPlayer inventoryPlayer, TileReducerCrusher tileReducerCrusher) {
        super(new ContainerReducerCrusher(inventoryPlayer, tileReducerCrusher));
        this.tileReducerCrusher = tileReducerCrusher;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
        GuiUtils.drawEnergyBar(this, guiLeft + 5, guiTop + 5, 70, tileReducerCrusher.getWorld().rand.nextInt(512000), 512000, mouseX, mouseY);
        GuiUtils.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 80, true);
    }
}
