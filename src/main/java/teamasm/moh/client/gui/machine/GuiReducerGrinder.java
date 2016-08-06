package teamasm.moh.client.gui.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import teamasm.moh.client.gui.GuiUtils;
import teamasm.moh.container.machine.ContainerReducerGrinder;
import teamasm.moh.tile.machines.tier2.TileReducerGrinder;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public class GuiReducerGrinder extends GuiContainer {
    private TileReducerGrinder tileReducerGrinder;

    public GuiReducerGrinder(InventoryPlayer inventoryPlayer, TileReducerGrinder tileReducerGrinder) {
        super(new ContainerReducerGrinder(inventoryPlayer, tileReducerGrinder));
        this.tileReducerGrinder = tileReducerGrinder;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawDefaultBackground(this, guiLeft, guiTop, xSize, ySize);
        GuiUtils.drawString(this, "Reducer Grinder", guiLeft + 45, guiTop + 10);
        GuiUtils.drawSlot(this, guiLeft + 40, guiTop + 30);
        GuiUtils.drawSlot(this, guiLeft + 120, guiTop + 30);
        //TODO sync this (listener maybe??)
        GuiUtils.drawProgressBar(this, (int) tileReducerGrinder.progress, guiLeft + 80, guiTop + 30);

        GuiUtils.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 80, true);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        GuiUtils.drawEnergyBar(this, 5,  5, 70, tileReducerGrinder.getEnergyStored(EnumFacing.DOWN), tileReducerGrinder.getMaxEnergyStored(EnumFacing.DOWN), mouseX - guiLeft, mouseY - guiTop);
    }
}
