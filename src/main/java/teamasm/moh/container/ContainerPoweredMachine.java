package teamasm.moh.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamasm.moh.tile.TileProcessEnergy;

/**
 * Created by Gigabit101 on 07/08/2016.
 */
public class ContainerPoweredMachine extends BaseContainer {
    public TileProcessEnergy tileProcessEnergy;
    public int power;
    public int progress;

    public ContainerPoweredMachine(EntityPlayer entityPlayer, TileProcessEnergy tileProcessEnergy) {
        super(entityPlayer, tileProcessEnergy);
        this.tileProcessEnergy = tileProcessEnergy;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); i++) {
            IContainerListener IContainerListener = this.listeners.get(i);
            if (this.power != tileProcessEnergy.getEnergyStored(EnumFacing.DOWN)) {
                IContainerListener.sendProgressBarUpdate(this, 0, (int) tileProcessEnergy.getEnergyStored(EnumFacing.DOWN));
            }
            if (this.progress != tileProcessEnergy.progress) {
                IContainerListener.sendProgressBarUpdate(this, 1, (int) tileProcessEnergy.progress);
            }
        }
    }

    @Override
    public void addListener(IContainerListener crafting) {
        super.addListener(crafting);
        crafting.sendProgressBarUpdate(this, 0, (int) tileProcessEnergy.getEnergyStored(EnumFacing.DOWN));
        crafting.sendProgressBarUpdate(this, 1, (int) tileProcessEnergy.progress);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.power = value;
        }
        else if (id == 1) {
            this.progress = value;
        }
    }
}
