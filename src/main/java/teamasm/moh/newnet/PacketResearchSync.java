package teamasm.moh.newnet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import teamasm.moh.entity.capabilities.IResearch;
import teamasm.moh.newnet.netsync.MessageHandlerWrapper;

import static teamasm.moh.entity.capabilities.ResearchStorage.RESEARCH_CAP;

/**
 * Created by brandon3055 on 5/08/2016.
 * Use this as an example of how to use the IMessage System @covers1624
 */
public class PacketResearchSync implements IMessage {

    public NBTTagCompound researchData;

    public PacketResearchSync() {}

    public PacketResearchSync(IResearch research) {
        this.researchData = new NBTTagCompound();
        researchData.setTag("Data", RESEARCH_CAP.writeNBT(research, null));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, researchData);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        researchData = ByteBufUtils.readTag(buf);
    }

    public static class Handler extends MessageHandlerWrapper<PacketResearchSync, IMessage> {

        @Override
        public void handleMessage(PacketResearchSync message, MessageContext ctx) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            if (player.hasCapability(RESEARCH_CAP, null)) {
                RESEARCH_CAP.readNBT(player.getCapability(RESEARCH_CAP, null), null, message.researchData.getTag("Data"));
            }
        }
    }
}
