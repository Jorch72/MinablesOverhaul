package teamasm.moh.client.gui.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import teamasm.moh.client.gui.GuiUtils;
import teamasm.moh.container.machine.ContainerScreenCoarse;
import teamasm.moh.tile.machines.teir1.TileScreenCoarse;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class GuiScreenCoarse extends GuiContainer {
    private TileScreenCoarse tileScreenCoarse;

    public GuiScreenCoarse(InventoryPlayer inventoryPlayer, TileScreenCoarse tileScreenCoarse) {
        super(new ContainerScreenCoarse(inventoryPlayer, tileScreenCoarse));
        this.tileScreenCoarse = tileScreenCoarse;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
        drawCenteredString(fontRendererObj, "Coarse", guiLeft + xSize / 2, guiTop + 10, 0xFFFFFF);
        //GuiUtils.drawString(this, "Screen Coarse", guiLeft + 45, guiTop + 10);
        GuiUtils.drawSlot(this, guiLeft + 40, guiTop + 30);
        GuiUtils.drawSlot(this, guiLeft + 120, guiTop + 30);
        //TODO sync this (listener maybe??)
        GuiUtils.drawProgressBar(this, (int) tileScreenCoarse.progress, guiLeft + 80, guiTop + 30);

        GuiUtils.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 80, true);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        GuiUtils.drawEnergyBar(this, 5, 5, 70, tileScreenCoarse.getEnergyStored(EnumFacing.DOWN), tileScreenCoarse.getMaxEnergyStored(EnumFacing.DOWN), mouseX - guiLeft, mouseY - guiTop);
    }
}
