package teamasm.moh.manager;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by covers1624 on 8/6/2016.
 */
public class RetroOreStripper {

    public static final String DATA_TAG = "MinablesOverhaul";
    public static final String STRIP_FLAG = "mohOreStripFlag";

    public static final int chunksPerTick = Integer.parseInt(System.getProperty("moh.stripingChunksPerTick", "2"));

    private static final Map<Integer, ConcurrentLinkedQueue<ChunkPos>> chunkQue = new ConcurrentHashMap<Integer, ConcurrentLinkedQueue<ChunkPos>>();
    private static final Map<Integer, LinkedList<ChunkPos>> stripedChunks = new LinkedHashMap<Integer, LinkedList<ChunkPos>>();

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        synchronized (chunkQue) {
            if (chunkQue.containsKey(event.getWorld().provider.getDimension())) {
                chunkQue.remove(event.getWorld().provider.getDimension());
            }
        }
    }

    @SubscribeEvent
    public void tickEvent(TickEvent.ServerTickEvent event) {
        if (event.phase == Phase.END) {
            Map<Integer, List<Chunk>> modifiedChunkMap = new LinkedHashMap<Integer, List<Chunk>>();
            Iterator<Entry<Integer, ConcurrentLinkedQueue<ChunkPos>>> iterator = chunkQue.entrySet().iterator();

            boolean stop = false;
            while (iterator.hasNext()) {
                int chunksProcessed = 0;

                Entry<Integer, ConcurrentLinkedQueue<ChunkPos>> entry = iterator.next();
                WorldServer world = getServer().worldServerForDimension(entry.getKey());
                //FMLLog.info("Dimension %s Que: %s", entry.getKey(), "Remaining: " + entry.getValue().size());
                Iterator<ChunkPos> chunkIterator = entry.getValue().iterator();
                while (chunkIterator.hasNext()) {
                    if (chunksProcessed >= chunksPerTick) {
                        stop = true;
                        break;
                    }
                    ChunkPos chunkPos = chunkIterator.next();
                    Chunk chunk = world.getChunkFromChunkCoords(chunkPos.chunkXPos, chunkPos.chunkZPos);
                    for (int i = 0; i < 16; i++) {
                        for (int j = 0; j < 16; j++) {
                            for (int k = 0; k < 256; k++) {
                                ExtendedBlockStorage storage = chunk.storageArrays[k >> 4];
                                if (storage != null) {
                                    IBlockState state = storage.get(i, k & 15, j);
                                    if (OreStripManager.shouldStrip(state)) {
                                        IBlockState replacement;
                                        if (entry.getKey() == -1) {
                                            replacement = Blocks.NETHERRACK.getDefaultState();
                                        } else if (entry.getKey() == 1) {
                                            replacement = Blocks.END_STONE.getDefaultState();
                                        } else {
                                            replacement = Blocks.STONE.getDefaultState();
                                        }
                                        storage.set(i, k & 15, j, replacement);
                                        List<Chunk> modifiedChunks = modifiedChunkMap.get(entry.getKey());
                                        if (modifiedChunks == null) {
                                            modifiedChunks = new LinkedList<Chunk>();
                                        }
                                        if (!modifiedChunks.contains(chunk)) {
                                            modifiedChunks.add(chunk);
                                        }
                                        modifiedChunkMap.put(entry.getKey(), modifiedChunks);
                                    }
                                }
                            }
                        }
                    }

                    chunksProcessed++;
                    chunkIterator.remove();

                }
                if (stop) {
                    break;
                }
                iterator.remove();
            }

            for (Entry<Integer, List<Chunk>> entry : modifiedChunkMap.entrySet()) {
                WorldServer worldServer = getServer().worldServerForDimension(entry.getKey());
                sendChunkData(worldServer, entry.getValue());
            }
            modifiedChunkMap.clear();
        }
    }

    private static void sendChunkData(WorldServer worldServer, List<Chunk> modifiedChunks) {
        for (Chunk chunk : modifiedChunks) {
            PlayerChunkMap playerChunkMap = worldServer.getPlayerChunkMap();
            if (playerChunkMap == null) {
                return;
            }
            PlayerChunkMapEntry watcher = playerChunkMap.getEntry(chunk.xPosition, chunk.zPosition);
            if (watcher != null) {
                watcher.sendPacket(new SPacketChunkData(chunk, 65535));
            }
        }
    }

    @SubscribeEvent
    public void chunkLoadEvent(ChunkDataEvent.Load event) {
        int dim = event.getWorld().provider.getDimension();
        if (dim == -1 || dim == 0 || dim == 1) {

            NBTTagCompound tagCompound = event.getData().getCompoundTag(DATA_TAG);
            if (tagCompound == null || !tagCompound.hasKey(STRIP_FLAG) || !tagCompound.getBoolean(STRIP_FLAG)) {
                ConcurrentLinkedQueue<ChunkPos> chunks = new ConcurrentLinkedQueue<ChunkPos>();
                if (chunkQue.containsKey(event.getWorld().provider.getDimension())) {
                    chunks = chunkQue.get(event.getWorld().provider.getDimension());
                }
                chunks.add(event.getChunk().getChunkCoordIntPair());
                chunkQue.put(dim, chunks);
            } else if (tagCompound.getBoolean(STRIP_FLAG)) {
                LinkedList<ChunkPos> chunks = new LinkedList<ChunkPos>();
                if (stripedChunks.containsKey(event.getWorld().provider.getDimension())) {
                    chunks = stripedChunks.get(event.getWorld().provider.getDimension());
                }
                chunks.add(event.getChunk().getChunkCoordIntPair());
                stripedChunks.put(event.getWorld().provider.getDimension(), chunks);
            }
        }
    }

    @SubscribeEvent
    public void populateChunkEvent(PopulateChunkEvent.Pre event) {
        LinkedList<ChunkPos> chunks = new LinkedList<ChunkPos>();
        if (stripedChunks.containsKey(event.getWorld().provider.getDimension())) {
            chunks = stripedChunks.get(event.getWorld().provider.getDimension());
        }
        chunks.add(new ChunkPos(event.getChunkX(), event.getChunkZ()));
        stripedChunks.put(event.getWorld().provider.getDimension(), chunks);
    }

    @SubscribeEvent
    public void chunkSaveEvent(ChunkDataEvent.Save event) {
        int dim = event.getWorld().provider.getDimension();
        if (dim == -1 || dim == 0 || dim == 1) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            if (event.getData().hasKey(DATA_TAG)) {
                tagCompound = event.getData().getCompoundTag(DATA_TAG);
            }
            if (!tagCompound.hasKey(STRIP_FLAG) && stripedChunks.containsKey(dim) && stripedChunks.get(dim).contains(event.getChunk().getChunkCoordIntPair())) {
                tagCompound.setBoolean(STRIP_FLAG, true);
            }
            event.getData().setTag(DATA_TAG, tagCompound);
        }
    }

    private static MinecraftServer getServer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance();
    }

}
