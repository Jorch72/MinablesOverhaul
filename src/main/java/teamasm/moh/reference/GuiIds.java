package teamasm.moh.reference;

/**
 * Created by Gigabit101 on 05/08/2016.
 */
public enum GuiIds {

    CRUSHER,
    GRINDER,
    SCREEN_COARSE,
    SCREEN_FINE,
    SEPARATOR_MAGNETIC,
    CENTRIFUGE,
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
