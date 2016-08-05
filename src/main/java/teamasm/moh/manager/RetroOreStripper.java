package teamasm.moh.manager;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by covers1624 on 8/6/2016.
 */
public class RetroOreStripper {

    public static final String DATA_TAG = "MinablesOverhaul";
    public static final String STRIP_FLAG = "mohOreStripFlag";

    public static final int chunksPerTick = Integer.parseInt(System.getProperty("moh.stripingChunksPerTick", "10"));

    private static Map<Integer, LinkedList<ChunkPos>> chunkQue = new LinkedHashMap<Integer, LinkedList<ChunkPos>>();

    private static Map<Integer, LinkedList<ChunkPos>> stripedChunks = new LinkedHashMap<Integer, LinkedList<ChunkPos>>();

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        if (chunkQue.containsKey(event.getWorld().provider.getDimension())) {
            chunkQue.remove(event.getWorld().provider.getDimension());
        }
    }

    @SubscribeEvent
    public void tickEvent(TickEvent.ServerTickEvent event) {
        if (event.phase == Phase.START) {
            Iterator<Entry<Integer, LinkedList<ChunkPos>>> iterator = chunkQue.entrySet().iterator();
            int chunksProcessed = 0;
            while (iterator.hasNext()) {
                if (chunksProcessed >= chunksPerTick) {
                    break;
                }
                Entry<Integer, LinkedList<ChunkPos>> entry = iterator.next();
                WorldServer world = getServer().worldServerForDimension(entry.getKey());
                //Chunk chunk = world.getChunkFromChunkCoords(entry.)
                chunksProcessed++;
                iterator.remove();
            }
        }
    }

    @SubscribeEvent
    public void chunkLoadEvent(ChunkDataEvent.Load event) {
        NBTTagCompound tagCompound = event.getData().getCompoundTag(DATA_TAG);
        if (tagCompound == null || !tagCompound.hasKey(STRIP_FLAG) || !tagCompound.getBoolean(STRIP_FLAG)) {
            LinkedList<ChunkPos> chunks = new LinkedList<ChunkPos>();
            if (chunkQue.containsKey(event.getWorld().provider.getDimension())) {
                chunks = chunkQue.get(event.getWorld().provider.getDimension());
            }
            chunks.add(event.getChunk().getChunkCoordIntPair());
        } else if (tagCompound.getBoolean(STRIP_FLAG)) {
            LinkedList<ChunkPos> chunks = new LinkedList<ChunkPos>();
            if (stripedChunks.containsKey(event.getWorld().provider.getDimension())) {
                chunks = stripedChunks.get(event.getWorld().provider.getDimension());
            }
            chunks.add(event.getChunk().getChunkCoordIntPair());
            stripedChunks.put(event.getWorld().provider.getDimension(), chunks);
        }
    }

    @SubscribeEvent
    public void chunkSaveEvent(ChunkDataEvent.Save event) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        if (event.getData().hasKey(DATA_TAG)) {
            tagCompound = event.getData().getCompoundTag(DATA_TAG);
        }
        if (!tagCompound.hasKey(STRIP_FLAG) && stripedChunks.get(event.getWorld().provider.getDimension()).contains(event.getChunk().getChunkCoordIntPair())) {
            tagCompound.setBoolean(STRIP_FLAG, true);
        }
        event.getData().setTag(DATA_TAG, tagCompound);
    }

    private static MinecraftServer getServer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance();
    }

}
