package teamasm.moh.tile.machines.teir1;

import codechicken.lib.tile.IGuiTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamasm.moh.api.recipe.IMOHRecipe;
import teamasm.moh.item.ItemOre;
import teamasm.moh.reference.GuiIds;
import teamasm.moh.tile.TileProcessEnergy;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileReducerCrusher extends TileProcessEnergy implements IGuiTile, ITickable {

    public TileReducerCrusher() {
        setInventory(2, 64);
    }

    int runCost = 100;

    @Override
    public void update() {
        if (!worldObj.isRemote) {
            if (getEnergyStored(EnumFacing.DOWN) >= runCost && getStackInSlot(0) != null && getStackInSlot(0).getItem() instanceof ItemOre) {
                int stacksize = getStackInSlot(0).stackSize;
                ItemOre itemOre = (ItemOre) getStackInSlot(0).getItem();
                if (stacksize > 1 && itemOre.getParticleSize(getStackInSlot(0)) > 0.1) {
                    //set new Particle size and copy
                    //                    itemOre.setParticleSize(getStackInSlot(0), itemOre.getParticleSize(getStackInSlot(0)) / stacksize);
                    itemOre.setReduced(getStackInSlot(0), true);
                    ItemStack copyStack = getStackInSlot(0);
                    copyStack.stackSize = 1;
                    //use power
                    energyStorage.extractEnergy(runCost, false);
                    //Remove input stack
                    setInventorySlotContents(0, null);
                    //set output stack
                    setInventorySlotContents(1, copyStack);
                }
            }
        }
    }

    @Override
    public IMOHRecipe checkForValidRecipe() {
        return null;
    }

    @Override
    public void openGui(World world, BlockPos pos, EntityPlayer player) {
        openGui(GuiIds.REDUCER_CRUSHER, world, pos, player);
    }

}
