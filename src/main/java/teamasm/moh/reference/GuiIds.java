package teamasm.moh.reference;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public enum GuiIds {

    REDUCER_CRUSHER,
    UNKNOWN;

    public static GuiIds parse(int ID) {
        try {
            return values()[ID];
        } catch (Exception ignored) {
            return UNKNOWN;
        }
    }

}
