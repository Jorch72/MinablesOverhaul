package teamasm.moh.newnet.netsync;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public abstract class MessageHandlerWrapper<REQ extends IMessage, REPLY extends IMessage> implements IMessageHandler<REQ, REPLY> {

    /**
     * Override this method to return a reply. Note that this is called from the network thread so be sure to keep<br>
     * thread safety in mind.
     */
    public REPLY getReply(REQ message, MessageContext ctx) {
        return null;
    }

    @Override
    public REPLY onMessage(REQ message, MessageContext ctx) {

        PacketSyncObject<REQ> syncObject = new PacketSyncObject<REQ>(message, ctx) {
            @Override
            public void run() {
                handleMessage(message, ctx);
            }
        };

        if (ctx.side == Side.CLIENT) {
            syncObject.addPacketClient();
        } else {
            syncObject.addPacketServer();
        }

        return getReply(message, ctx);
    }

    public abstract void handleMessage(REQ message, MessageContext ctx);

}
