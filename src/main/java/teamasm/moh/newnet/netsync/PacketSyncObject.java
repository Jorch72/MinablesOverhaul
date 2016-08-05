package teamasm.moh.newnet.netsync;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Level;

/**
 * Created by brandon3055 on 5/8/2016.
 * This is used for packet thread synchronization.
 */
public abstract class PacketSyncObject<REQ extends IMessage> implements Runnable {

    public final REQ message;

    public final MessageContext ctx;

    public PacketSyncObject(REQ message, MessageContext ctx) {
        this.message = message;
        this.ctx = ctx;
    }

    @Override
    public abstract void run();

    public void addPacketServer() {
        if (ctx.side == Side.CLIENT) {
            FMLLog.log(Level.ERROR, "[SyncPacket#addPacketServer] WRONG SIDE!!!! This should not be possible! - " + message.getClass());
            return;
        }
        ctx.getServerHandler().playerEntity.getServer().addScheduledTask(this);
    }

    public void addPacketClient() {
        if (ctx.side == Side.SERVER) {
            FMLLog.log(Level.ERROR, "[SyncPacket#SyncPacket] WRONG SIDE!!!! This should not be possible! - " + message.getClass());
            return;
        }
        Minecraft.getMinecraft().addScheduledTask(this);
    }
}
