package teamasm.moh.entity.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import java.util.concurrent.Callable;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class PlayerResearchStorage implements Capability.IStorage<IResearchStorage> {


    @Override
    public NBTBase writeNBT(Capability<IResearchStorage> capability, IResearchStorage instance, EnumFacing side) {
        NBTTagCompound compound = new NBTTagCompound();

        return compound;
    }

    @Override
    public void readNBT(Capability<IResearchStorage> capability, IResearchStorage instance, EnumFacing side, NBTBase nbt) {

    }

    private static class Factory implements Callable<IResearchStorage> {

        @Override
        public IResearchStorage call() throws Exception {
            return new ResearchStorage();
        }
    }
}
