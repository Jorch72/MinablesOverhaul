package teamasm.moh.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import teamasm.moh.reference.VariantReference;
import teamasm.moh.tile.TileOre;

/**
 * Created by covers1624 on 8/5/2016.
 */
public class ItemBlockOre extends ItemBlock {

    public ItemBlockOre(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
        setMaxStackSize(64);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile." + VariantReference.oreNames[stack.getMetadata()];
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
        if (super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState)) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (!(tileEntity instanceof TileOre)) {
                FMLLog.log("MinablesOverhaul", Level.WARN, "Unable to get TileEntity of placed ore block or Tile is not a TileOre!");
                return true;
            }
            TileOre tileOre = (TileOre) tileEntity;
            tileOre.setMinable(VariantReference.oreNames[stack.getMetadata()]);
            return true;
        }
        return false;
    }
}
