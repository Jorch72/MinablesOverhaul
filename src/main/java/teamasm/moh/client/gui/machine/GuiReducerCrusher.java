package teamasm.moh.client.gui.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import teamasm.moh.client.gui.GuiUtils;
import teamasm.moh.container.machine.ContainerReducerCrusher;
import teamasm.moh.tile.machines.teir1.TileCrusher;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class GuiReducerCrusher extends GuiContainer {
    private TileCrusher tileReducerCrusher;

    public GuiReducerCrusher(InventoryPlayer inventoryPlayer, TileCrusher tileReducerCrusher) {
        super(new ContainerReducerCrusher(inventoryPlayer, tileReducerCrusher));
        this.tileReducerCrusher = tileReducerCrusher;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
        drawCenteredString(fontRendererObj, "Crusher", guiLeft + xSize / 2, guiTop + 10, 0xFFFFFF);
        GuiUtils.drawSlot(this, guiLeft + 40, guiTop + 30);
        GuiUtils.drawSlot(this, guiLeft + 120, guiTop + 30);

        GuiUtils.drawProgressBar(this, tileReducerCrusher.progress / (double)tileReducerCrusher.cycleTime, guiLeft + 80, guiTop + 30);

        GuiUtils.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 80, true);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        GuiUtils.drawEnergyBar(this, 5, 5, 70, tileReducerCrusher.getEnergyStored(EnumFacing.DOWN), tileReducerCrusher.getMaxEnergyStored(EnumFacing.DOWN), mouseX - guiLeft, mouseY - guiTop);
    }
}
