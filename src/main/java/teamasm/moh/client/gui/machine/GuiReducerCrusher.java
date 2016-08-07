package teamasm.moh.client.gui.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.FMLLog;
import teamasm.moh.client.gui.GuiUtils;
import teamasm.moh.container.machine.ContainerReducerCrusher;
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
        GuiUtils.drawString(this, "Reducer Crusher", guiLeft + 45, guiTop + 10);
        GuiUtils.drawSlot(this, guiLeft + 40, guiTop + 30);
        GuiUtils.drawSlot(this, guiLeft + 120, guiTop + 30);

        FMLLog.info(""+tileReducerCrusher.progress);
        GuiUtils.drawProgressBar(this, tileReducerCrusher.progress / (double)tileReducerCrusher.cycleTimeTime, guiLeft + 80, guiTop + 30);

        GuiUtils.drawPlayerSlots(this, guiLeft + xSize / 2, guiTop + 80, true);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        GuiUtils.drawEnergyBar(this, 5, 5, 70, tileReducerCrusher.getEnergyStored(EnumFacing.DOWN), tileReducerCrusher.getMaxEnergyStored(EnumFacing.DOWN), mouseX - guiLeft, mouseY - guiTop);
    }
}
