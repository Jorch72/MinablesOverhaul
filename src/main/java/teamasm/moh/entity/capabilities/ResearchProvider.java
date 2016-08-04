package teamasm.moh.entity.capabilities;

import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import static teamasm.moh.entity.capabilities.ResearchStorage.RESEARCH_CAP;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public class ResearchProvider implements ICapabilitySerializable<NBTPrimitive> {

    IResearch defaultInstance = RESEARCH_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == RESEARCH_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == RESEARCH_CAP ? RESEARCH_CAP.<T>cast(defaultInstance) : null;
    }

    @Override
    public NBTPrimitive serializeNBT() {
        return (NBTPrimitive) RESEARCH_CAP.getStorage().writeNBT(RESEARCH_CAP, defaultInstance, null);
    }

    @Override
    public void deserializeNBT(NBTPrimitive nbt) {
        RESEARCH_CAP.getStorage().readNBT(RESEARCH_CAP, defaultInstance, null, nbt);
    }

    public static class DefaultImpl implements IResearch {

        @Override
        public Map<String, Integer> getResearch() {
            return new HashMap<String, Integer>();
        }

        @Override
        public void setResearch(String research, int value) {}
    }
}
