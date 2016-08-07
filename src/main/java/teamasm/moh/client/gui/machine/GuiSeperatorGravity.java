package teamasm.moh.client.gui.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import teamasm.moh.client.gui.GuiUtils;
import teamasm.moh.container.machine.ContainerSeperatorGravity;
import teamasm.moh.tile.machines.teir1.TileCentrifuge;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class GuiSeperatorGravity extends GuiContainer {
    private TileCentrifuge separatorGravity;

    public GuiSeperatorGravity(InventoryPlayer inventoryPlayer, TileCentrifuge separatorGravity) {
        super(new ContainerSeperatorGravity(inventoryPlayer, separatorGravity));
        this.separatorGravity = separatorGravity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
        drawCenteredString(fontRendererObj, "Separator", guiLeft + xSize / 2, guiTop + 10, 0xFFFFFF);
        //GuiUtils.drawString(this, "Screen Coarse", guiLeft + 45, guiTop + 10);
        GuiUtils.drawSlot(this, guiLeft + 40, guiTop + 34);

        for (int i = 0; i < 6; i++) {
            GuiUtils.drawSlot(this, guiLeft + 102 + i % 3 * 18, guiTop + 25 + i / 3 * 18);
        }

        //TODO sync this (listener maybe??)
        GuiUtils.drawProgressBar(this, (int) separatorGravity.progress, guiLeft + 69, guiTop + 35);

        GuiUtils.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 80, true);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        GuiUtils.drawEnergyBar(this, 5, 5, 70, separatorGravity.getEnergyStored(EnumFacing.DOWN), separatorGravity.getMaxEnergyStored(EnumFacing.DOWN), mouseX - guiLeft, mouseY - guiTop);
    }
}
