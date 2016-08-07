package teamasm.moh.network;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IClientPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.network.play.INetHandlerPlayClient;
import teamasm.moh.container.ContainerPoweredMachine;

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
        }
    }
}
