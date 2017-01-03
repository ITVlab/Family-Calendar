package news.androidtv.familycalendar.utils;

import com.felkertech.settingsmanager.SettingsManager;

/**
 * Created by Nick on 12/31/2016.
 *
 * Constant keys used for {@link SettingsManager}.
 */
public class SettingsConstants {
    /**
     * A boolean which is on when the user has fully authenticated their calendar account.
     */
    public static final String CALENDAR_AUTH_ENABLED = "CALENDAR_AUTH_ENABLED";

    /**
     * A string which stores the user account name.
     */
    public static final String PREF_ACCOUNT_NAME = "pref_account_name";

    /**
     * A boolean which states whether this calendar has been manually enabled or disabled by the
     * user.
     *
     * @param id The calendar id
     * @return A key for the calendar.
     */
    public static String CALENDAR_SELECTED(String id) {
        return "CALENDAR_SELECTED_" + id;
    }
}
