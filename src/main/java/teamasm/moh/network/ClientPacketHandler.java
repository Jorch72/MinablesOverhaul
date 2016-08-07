package teamasm.moh.network;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IClientPacketHandler;
import codechicken.lib.tile.IRotatableTile;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import teamasm.moh.container.ContainerPoweredMachine;
import teamasm.moh.tile.TileProcessorBase;

import static teamasm.moh.entity.capabilities.ResearchStorage.RESEARCH_CAP;

/**
 * Created by covers1624 on 8/4/2016.
 */
public class ClientPacketHandler implements IClientPacketHandler {

    @Override
    public void handlePacket(PacketCustom packetCustom, Minecraft mc, INetHandlerPlayClient handler) {
        switch (packetCustom.getType()) {
        case 1:
            RESEARCH_CAP.readNBT(mc.thePlayer.getCapability(RESEARCH_CAP, null), null, packetCustom.readNBTTagCompound().getTag("capData"));
            break;
        case 2:
            Container container = mc.thePlayer.openContainer;
            if (container instanceof ContainerPoweredMachine) {
                ((ContainerPoweredMachine) container).tileProcessEnergy.energyStorage.setEnergyStored(packetCustom.readInt());
            }
            break;
        case 3:
            TileEntity rotatableTile = mc.theWorld.getTileEntity(packetCustom.readBlockPos());
            if (rotatableTile instanceof IRotatableTile) {
                ((IRotatableTile) rotatableTile).setRotation(EnumFacing.VALUES[packetCustom.readByte()]);
            }
            break;
        case 4:

            TileEntity tile = mc.theWorld.getTileEntity(packetCustom.readBlockPos());
            if (tile instanceof TileProcessorBase) {
                ((TileProcessorBase) tile).onShortPacket(packetCustom.readByte(), packetCustom.readShort());
            }
            break;
        }
    }
}
