package teamasm.moh.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamasm.moh.world.WorldGenHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class BlockOre extends Block {
    public BlockOre() {
        super(Material.ROCK);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> stacks = new ArrayList<ItemStack>();

        if (world instanceof World) {
            stacks.add(WorldGenHandler.getOreAt((World) world, pos));
        }

        return stacks;
    }

    //todo delete
    //    private final List<String> variants;
    //
    //    private final PropertyString TYPE;
    //
    //    public BlockOre(List<String> variants) {
    //        super(Material.ROCK);
    //        this.variants = variants;
    //        this.TYPE = new PropertyString("type", variants);
    //
    //        try {
    //            ObfMapping mapping = new ObfMapping("net/minecraft/block/Block", "field_176227_L", "");
    //            mapping.toRuntime();
    //            Class<?> clazz = Class.forName(mapping.javaClass());
    //            Field field = clazz.getDeclaredField(mapping.s_name);
    //            field.setAccessible(true);
    //            field.set(this, createBlockState());
    //        } catch (Exception e) {
    //            throw new RuntimeException(e);
    //        }
    //    }
    //
    //    public static IBlockState getOreFromMeta(int meta) {
    //        if (meta <= 15) {
    //            ModBlocks.blockOreNormal.getStateFromMeta(meta);
    //        } else if (meta > 16 && meta <= 31) {
    //            ModBlocks.blockOreMisc.getStateFromMeta(meta - 17);
    //        }
    //        return ModBlocks.blockOreNormal.getDefaultState();
    //    }
    //
    //    @Override
    //    public IBlockState getStateFromMeta(int meta) {
    //        return getBlockState().getBaseState().withProperty(TYPE, variants.get(meta));
    //    }
    //
    //    @Nullable
    //    @Override
    //    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    //        return Item.getItemFromBlock(ModBlocks.blockOreNormal);
    //    }
    //
    //    @Override
    //    public int damageDropped(IBlockState state) {
    //        if (state.getBlock().equals(ModBlocks.blockOreNormal)) {
    //            return getMetaFromState(state);
    //        } else if (state.getBlock().equals(ModBlocks.blockOreMisc)) {
    //            return getMetaFromState(state) + 16;
    //        }
    //        return 0;
    //    }
    //
    //    @Override
    //    public int getMetaFromState(IBlockState state) {
    //        return variants.indexOf(state.getValue(TYPE));
    //    }
    //
    //    @Override
    //    protected BlockStateContainer createBlockState() {
    //        if (TYPE == null) {
    //            return new BlockStateContainer(this);
    //        }
    //        return new BlockStateContainer(this, TYPE);
    //    }
    //
    //    @Override
    //    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    //        if (meta <= 15) {//0 is an index
    //            return ModBlocks.blockOreNormal.getStateFromMeta(meta);
    //        } else if (meta > 16 && meta <= 31) {//0 is an index
    //            return ModBlocks.blockOreMisc.getStateFromMeta(meta - 17);
    //        }
    //        return getDefaultState();
    //    }
}
