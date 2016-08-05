package teamasm.moh.network;

import codechicken.lib.packet.PacketCustom;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import teamasm.moh.entity.capabilities.IResearch;

import static teamasm.moh.entity.capabilities.ResearchStorage.RESEARCH_CAP;

/**
 * Created by covers1624 on 8/4/2016.
 * Dispatch packets from here.
 */
public class PacketDispatcher {
    public static String NET_CHANNEL = "MinablesOverhaul";

    public static void dispatchResearchSync(IResearch research, EntityPlayerMP playerMP) {
        PacketCustom packet = new PacketCustom(NET_CHANNEL, 1);
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setTag("capData", RESEARCH_CAP.writeNBT(research, null));
        packet.writeNBTTagCompound(tagCompound);
        packet.sendToPlayer(playerMP);
    }

}
