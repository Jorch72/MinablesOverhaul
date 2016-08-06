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

import java.util.Map;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class TileReducerCrusher extends TileProcessEnergy implements IGuiTile, ITickable {

    public TileReducerCrusher() {
        setInventory(2, 64);
    }

    int runCost = 100;
    int SLOT_INPUT = 0;
    int SLOT_OUTPUT = 1;

    @Override
    public void update() {
        if (!worldObj.isRemote) {
            if(canWork()){
                progress++;
                if(progress == 100) {
                    work();
                }

            }
        }
    }

    public boolean canWork(){
        if(getEnergyStored(EnumFacing.DOWN) >= runCost && getStackInSlot(SLOT_INPUT) != null && getStackInSlot(SLOT_INPUT).getItem() instanceof ItemOre){
            return true;
        }
        return false;
    }

    public void work() {
        ItemStack wipStack = getStackInSlot(SLOT_INPUT).copy();
        wipStack.stackSize = 1;
        if(wipStack == null) {
            decrStackSize(SLOT_INPUT, 1);
        }
        ItemOre itemOre = (ItemOre) wipStack.getItem();
        itemOre.setReduced(wipStack, true);

        Map<String, Float> ores = itemOre.getOres(getStackInSlot(SLOT_INPUT));
        for (String name : ores.keySet()){
            float newValue = itemOre.getOres(getStackInSlot(SLOT_INPUT)).get(name).floatValue() + itemOre.getOres(wipStack).get(name).floatValue();
            itemOre.modifyPurity(name, newValue, wipStack);
        }
        decrStackSize(SLOT_INPUT, 1);
        progress = 0;

        //output
        if(getStackInSlot(SLOT_OUTPUT) == null && getStackInSlot(SLOT_INPUT) == null) {
            setInventorySlotContents(SLOT_OUTPUT, wipStack);
            wipStack = null;
            progress = 0;
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
