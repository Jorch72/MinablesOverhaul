package teamasm.moh.entity.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import teamasm.moh.network.PacketDispatcher;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class CapabilityHelper {

    //This is here because its faster to have a local static instance of the capability (atleast according to the doc)
    @CapabilityInject(IResearch.class)
    private static Capability<IResearch> RESEARCH_CAP = null;

    /**
     * A helper method for getting the research map from a player.
     */
    public static Map<String, Integer> getResearch(EntityPlayer player) {
        if (player.hasCapability(RESEARCH_CAP, null)) {
            return player.getCapability(RESEARCH_CAP, null).getResearch();
        }

        return new HashMap<String, Integer>();
    }

    /**
     * A helper method that returns a specified research from a player.<br>
     * Progress refers to a value between 0 and 100 (0% to 100%)
     */
    public static int getResearch(EntityPlayer player, String research) {
        Map<String, Integer> res = getResearch(player);
        return res.containsKey(research) ? res.get(research) : 0;
    }

    /**
     * A helper method that sets the progress of a specified research for a specified player.<br>
     * Progress refers to a value between 0 and 100 (0% to 100%)
     */
    public static void setResearch(EntityPlayer player, String research, int progress) {
        if (player.hasCapability(RESEARCH_CAP, null)) {
            player.getCapability(RESEARCH_CAP, null).setResearch(research, progress);
        }

        syncResearch(player);
    }

    /**
     * Syncs a players research with their client. Note this is only sent to the client that owns the research.
     */
    public static void syncResearch(EntityPlayer player) {
        if (player instanceof EntityPlayerMP && player.hasCapability(RESEARCH_CAP, null)) {
            IResearch research = player.getCapability(RESEARCH_CAP, null);
            PacketDispatcher.dispatchResearchSync(research, (EntityPlayerMP) player);
            //PacketResearchSync packet = new PacketResearchSync(research);
            //MinablesOverhaul.network.sendTo(packet, (EntityPlayerMP)player);
        }
    }
}
