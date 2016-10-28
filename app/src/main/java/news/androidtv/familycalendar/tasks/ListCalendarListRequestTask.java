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
    public ListCalendarListRequestTask(GoogleAccountCredential credential) {
        super(credential);
    }

    @Override
    protected List<CalendarListEntry> getDataFromApi() throws IOException {
        List<CalendarListEntry> calList =
                getCalendarService().calendarList().list().execute().getItems();
        Log.d(TAG, calList.toString());
        for (CalendarListEntry calendarListEntry : calList) {
            Log.d(TAG, calendarListEntry.getSummary());
            List<Event> events = null;
            try {
                events = new ListCalendarEventsRequestTask(getCredential(),
                        calendarListEntry.getId()).execute().get();
                Log.d(TAG, events.toString());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return calList;
    }
}
