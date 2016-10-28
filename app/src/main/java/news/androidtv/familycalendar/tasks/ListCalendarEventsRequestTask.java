package news.androidtv.familycalendar.tasks;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.util.List;

/**
 * A way to obtain all of the events from a specified calendar, not taking other parameters into
 * account like number of events and start/end times.
 */
public class ListCalendarEventsRequestTask extends CalendarRequestTask<List<Event>> {
    private String mCalendarId;

    public ListCalendarEventsRequestTask(GoogleAccountCredential credential, String calenderId) {
        super(credential);
        mCalendarId = calenderId;
    }

    @Override
    protected List<Event> getDataFromApi() throws IOException {
        return getCalendarService().events().list(mCalendarId).execute().getItems();
    }
}
