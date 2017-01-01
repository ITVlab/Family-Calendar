package news.androidtv.familycalendar.utils;

import android.content.Context;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;

import java.util.Arrays;

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
        return event.getStart().getDate().getValue() + " - " + event.getEnd().getDate().getValue();
    }
}
