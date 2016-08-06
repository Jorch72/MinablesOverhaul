package teamasm.moh.entity.capabilities;

import java.util.Map;

/**
 * Created by brandon3055 on 5/08/2016.
 */
public interface IResearch {

    /**
     * @return a map of research to research progress (percentage 0 - 100)
     */
    Map<String, Integer> getResearch();

    /**
     * @param research the name of the research. For ores this is the ore dictionary name of the ore.
     * @param value    the research level. This ranges from 0 to 100 (0% - 100%)
     */
    void setResearch(String research, int value);

    /**
     * This method of setting research directly overwrites the research map and is only used when saving or restoring research data.
     */
    void setResearch(Map<String, Integer> research);
}
