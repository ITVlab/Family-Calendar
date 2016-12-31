package news.androidtv.familycalendar.tasks;

import android.util.Log;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Nick on 10/28/2016.
 */
public class ListCalendarListRequestTask extends CalendarRequestTask<List<CalendarListEntry>> {
    private static final boolean DEBUG = false;

    public ListCalendarListRequestTask(GoogleAccountCredential credential) {
        super(credential);
    }

    @Override
    protected List<CalendarListEntry> getDataFromApi() throws IOException {
        List<CalendarListEntry> calList =
                getCalendarService().calendarList().list().execute().getItems();
        if (DEBUG) {
            Log.d(TAG, calList.toString());
        }
        for (CalendarListEntry calendarListEntry : calList) {
            List<Event> events = new ListCalendarEventsRequestTask(getCredential(),
                    calendarListEntry.getId()).getDataFromApi();
            if (DEBUG) {
                Log.d(TAG, calendarListEntry.getSummary());
                Log.d(TAG, calendarListEntry.getId());
                Log.d(TAG, "Events: " + events.toString());
            }
        }
        return calList;
    }
}
