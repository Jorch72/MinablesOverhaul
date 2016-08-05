package teamasm.moh.world;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamasm.moh.reference.OreRegistry;

import java.util.Random;

/**
 * Created by brandon3055 on 6/08/2016.
 * Note: To avoid confusion block* refers to an actual block positional value and chunk* refers to a chunk positional value.
 */
public class WorldGenHandler {
    private static Random random = new Random();

    private static int gridSize = 48;
    /**
     * Ore pocket size should be no more then half grid size to avoid overlap.
     */
    private static int orePocketSize = 24;
    private static float chancePerPoint = 0.3F;


    //region OreCalculation

    public static ItemStack getOreAt(World world, BlockPos blockPos) {
        GridPoint grid = getNearestGridPoint(blockPos.getX(), blockPos.getZ());
        random.setSeed(grid.getPointSeed(world));

        if (random.nextFloat() > chancePerPoint) {
            return null;
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

            if (random.nextFloat() > chancePerPoint) {
                return;
            }

            int yLevel = 3 + (int) (random.nextDouble() * (world.getSeaLevel() - 3));

            generatePocketAt(world, new BlockPos(grid.x, yLevel, grid.z));
        }
    }

    private static void generatePocketAt(World world, BlockPos blockPos) {
        //todo Create some proper generation code that dose not just generate a giant block of ore

        int diameter = orePocketSize / 2;
        Iterable<BlockPos> blocks = BlockPos.getAllInBox(blockPos.add(-diameter, -diameter, -diameter), blockPos.add(diameter, diameter, diameter));

        for (BlockPos pos : blocks) {
            world.setBlockState(pos, Blocks.IRON_ORE.getDefaultState());//todo replace with the actual ore block
        }
    }

    //endregion

    //region Grid Calculations

    private static GridPoint getNearestGridPoint(int blockX, int blockZ) {
        return new GridPoint(getNearestMultiple(blockX, gridSize), getNearestMultiple(blockZ, gridSize));//TODO Switch back to grid size
    }

    private static int getNearestMultiple(int number, int multiple) {
//        return multiple * round(number / multiple);
        int result = number;

        if (number < 0) {
            result *= -1;
        }

        if (result % multiple == 0) {
            return number;
        }
        else if (result % multiple < multiple/2) {
            result = result - result % multiple;
        }
        else {
            result = result + (multiple - result % multiple);
        }

        if (number < 0) {
            result *= -1;
        }
        return result;
    }

    private static int round(double number) {
        if (number % 1 >= 0.5) {
            return (int)Math.round(number);
        }
        else {
            return (int)Math.round(number - 0.51);
        }
    }

    //endregion
}
