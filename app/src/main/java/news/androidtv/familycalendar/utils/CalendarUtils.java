package news.androidtv.familycalendar.utils;

import android.content.Context;

import com.felkertech.settingsmanager.SettingsManager;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Nick on 10/28/2016.
 */

public class CalendarUtils {
    private static final String[] SCOPES = { CalendarScopes.CALENDAR_READONLY };

    public static GoogleAccountCredential getCredential(Context context) {
        return GoogleAccountCredential.usingOAuth2(context, Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());
    }

    public static String getEventStartEndAsString(Event event) {
        if (event.getStart().getDateTime() != null && event.getEnd().getDateTime() != null) {
            return new SimpleDateFormat("MMM d     h:mma").format(
                    new Date(event.getStart().getDateTime().getValue()))
                    + " - " +
                    new SimpleDateFormat("h:mma").format(
                            new Date(event.getEnd().getDateTime().getValue()));
        }
        return "";
    }

    public static boolean isCalendarSelected(CalendarListEntry calendar, Context context) {
        BetterSettingsManager settingsManager = new BetterSettingsManager(context);
        String key =  SettingsConstants.CALENDAR_SELECTED(calendar.getId());
        return (calendar.isSelected() && !settingsManager.hasKey(key))
                || new SettingsManager(context).getBoolean(key);
    }
}

