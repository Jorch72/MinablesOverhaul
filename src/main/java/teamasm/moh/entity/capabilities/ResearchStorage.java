package teamasm.moh.entity.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class ResearchStorage implements Capability.IStorage<IResearch> {

    @CapabilityInject(IResearch.class)
    public static Capability<IResearch> RESEARCH_CAP = null;

    @Override
    public NBTBase writeNBT(Capability<IResearch> capability, IResearch instance, EnumFacing side) {
        NBTTagList list = new NBTTagList();
        for (String name : instance.getResearch().keySet()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("Name", name);
            tag.setByte("Progress", (byte) (int) instance.getResearch().get(name));
        }

        return list;
    }

    @Override
    public void readNBT(Capability<IResearch> capability, IResearch instance, EnumFacing side, NBTBase nbt) {
        if (nbt instanceof NBTTagList) {
            for (int i = 0; i < ((NBTTagList) nbt).tagCount(); i++) {
                NBTTagCompound tag = ((NBTTagList) nbt).getCompoundTagAt(i);
                instance.setResearch(tag.getString("Name"), tag.getByte("Progress"));
            }
        }
    }
}
