package teamasm.moh.network;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IClientPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
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
                int x = packetCustom.readInt();
                int y = packetCustom.readInt();
                int z = packetCustom.readInt();
                int index = packetCustom.readByte();
                int value = packetCustom.readShort();
                TileEntity tile = mc.theWorld.getTileEntity(new BlockPos(x, y, z));
                if (tile instanceof TileProcessorBase) {
                    ((TileProcessorBase) tile).onShortPacket(index, value);
                }
                break;
        }
    }
}
