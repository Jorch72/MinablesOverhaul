package teamasm.moh.network;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.tile.IRotatableTile;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamasm.moh.entity.capabilities.IResearch;
import teamasm.moh.tile.TileProcessEnergy;
import teamasm.moh.tile.TileProcessorBase;

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

    public static void dispatchContainerEnergySync(TileProcessEnergy tile, EntityPlayerMP playerMP) {
        PacketCustom packet = new PacketCustom(NET_CHANNEL, 2);
        packet.writeInt(tile.energyStorage.getEnergyStored());
        packet.sendToPlayer(playerMP);
    }

    public static void dispatchRotationPacket(IRotatableTile tile, World world, BlockPos pos) {
        PacketCustom packet = new PacketCustom(NET_CHANNEL, 3);
        packet.writePos(pos);
        packet.writeByte(tile.getRotation().ordinal());
        packet.sendPacketToAllAround(pos, 64, world.provider.getDimension());
    }

    public static void dispatchTileShort(TileProcessorBase tile, int index, int shortValue) {
        PacketCustom packet = new PacketCustom(NET_CHANNEL, 4);
        packet.writeBlockPos(tile.getPos());
        packet.writeByte(index);
        packet.writeShort(shortValue);
        packet.sendPacketToAllAround(tile.getPos(), 64, tile.getWorld().provider.getDimension());
    }
}
