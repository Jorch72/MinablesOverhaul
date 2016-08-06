package teamasm.moh.reference;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public enum GuiIds {

    REDUCER_CRUSHER,
    REDUCER_GRINDER,
    SCREEN_COARSE,
    SCREEN_MEDIUM,
    SCREEN_FINE,
    SEPARATOR_MAGNETIC,
    SEPARATOR_GRAVITY,
    SEPARATOR_FLOTATION,
    SEPARATOR_ELECTROSTATIC,
    DRYER_ROTARY,
    UNKNOWN;

    public static GuiIds parse(int ID) {
        try {
            return values()[ID];
        } catch (Exception ignored) {
            return UNKNOWN;
        }
    }

}
