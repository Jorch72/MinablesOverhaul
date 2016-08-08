package teamasm.moh.world;

import teamasm.moh.repack.codechicken.lib.world.placement.BlockPlacementBatcher;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import teamasm.moh.handler.ConfigHandler;
import teamasm.moh.init.ModBlocks;
import teamasm.moh.reference.OreRegistry;

import java.util.Random;

/**
 * Created by brandon3055 on 6/08/2016.
 * Note: To avoid confusion block* refers to an actual block positional value and chunk* refers to a chunk positional value.
 */
public class WorldGenHandler {
    private static Random random = new Random();
    public static double noiseFactor = 0.3F;
    private static NoiseGeneratorSimplex noise = new NoiseGeneratorSimplex(new Random());

    //region OreCalculation

    public static ItemStack getOreAt(World world, BlockPos blockPos) {
        GridPoint grid = getNearestGridPoint(blockPos.getX(), blockPos.getZ());
        random.setSeed(grid.getPointSeed(world));

        if (random.nextFloat() > ConfigHandler.chancePerPoint) {
            //return null;
        }

        return OreRegistry.INSTANCE.getRandomOreForSeed(random);
    }

    //endregion

    //region WorldGen

    public static void generateChunk(World world, int chunkX, int chunkZ) {
        int posX = (chunkX * 16);
        int posZ = (chunkZ * 16);
        GridPoint grid = getNearestGridPoint(posX, posZ);

        if (grid.x > posX && grid.x <= posX + 16 && grid.z > posZ && grid.z <= posZ + 16) {
            random.setSeed(grid.getPointSeed(world));

            if (random.nextFloat() > ConfigHandler.chancePerPoint) {
                return;
            }

            int yLevel = 3 + (int) (random.nextDouble() * (Math.min(world.getSeaLevel(), getTopBlock(world, new BlockPos(grid.x, world.getSeaLevel(), grid.z))) - 10));

            if (yLevel < 0) {
                return;
            }

            generatePocketAt(world, new BlockPos(grid.x, yLevel, grid.z));
        }
    }

    private static int getTopBlock(World world, BlockPos blockPos) {
        for (int y = blockPos.getY(); y > 0; y--){
            if (world.getBlockState(new BlockPos(blockPos.getX(), y, blockPos.getZ())).getBlock() == Blocks.STONE) {
                return y;
            }
        }
        return 0;
    }

    private static void generatePocketAt(World world, BlockPos blockPos) {
        if (!(world instanceof WorldServer)) {
            return;
        }

        int xRad = (int) (ConfigHandler.orePocketSizeFactor * (0.8 + (random.nextDouble() * 0.2)));
        int yRad = (int) (ConfigHandler.orePocketHeightFactor * (0.8 + (random.nextDouble() * 0.2)));
        int zRad = (int) (ConfigHandler.orePocketSizeFactor * (0.8 + (random.nextDouble() * 0.2)));

        BlockPlacementBatcher batcher = new BlockPlacementBatcher((WorldServer)world);

        for (int x = blockPos.getX() - xRad; x <= blockPos.getX() + xRad; x++) {
            double xDist = 1D - (Math.abs(x - blockPos.getX()) / (double) xRad);
            for (int y = blockPos.getY() - yRad; y <= blockPos.getY() + yRad; y++) {
                double yDist = Math.abs(y - blockPos.getY());
                for (int z = blockPos.getZ() - zRad; z <= blockPos.getZ() + zRad; z++) {
                    double zDist = 1D - (Math.abs(z - blockPos.getZ()) / (double) zRad);

                    double nValue = Math.max(noise.getValue(x / 64D, z / 64D), 0) * yRad * xDist * zDist;

                    nValue += noise.getValue((x / 16D) + 512D, (z / 16D) + 512D) * (yRad / 3) * xDist * zDist;

                    if (nValue > yDist + 0.5 && y >= 0 && noiseFactor < random.nextFloat()) {
                        setBlock(world, batcher, new BlockPos(x, y, z));
                    }
                }
            }
        }

        batcher.finish();
    }

    private static void setBlock(World world, BlockPlacementBatcher batcher, BlockPos pos) {
        if (world.getBlockState(pos) == Blocks.STONE.getDefaultState()) {
            batcher.setBlockState(pos, ModBlocks.blockOre.getDefaultState());
        }
    }

    //endregion

    //region Grid Calculations

    private static GridPoint getNearestGridPoint(int blockX, int blockZ) {
        return new GridPoint(getNearestMultiple(blockX, ConfigHandler.oreGridSize), getNearestMultiple(blockZ, ConfigHandler.oreGridSize));
    }

    private static int getNearestMultiple(int number, int multiple) {
        //        return multiple * round(number / multiple);
        int result = number;

        if (number < 0) {
            result *= -1;
        }

        if (result % multiple == 0) {
            return number;
        } else if (result % multiple < multiple / 2) {
            result = result - result % multiple;
        } else {
            result = result + (multiple - result % multiple);
        }

        if (number < 0) {
            result *= -1;
        }
        return result;
    }

    private static int round(double number) {
        if (number % 1 >= 0.5) {
            return (int) Math.round(number);
        } else {
            return (int) Math.round(number - 0.51);
        }
    }

    //endregion
}
