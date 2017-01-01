package news.androidtv.familycalendar.tasks;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * A way to obtain all of the events from a specified calendar, not taking other parameters into
 * account like number of events and start/end times.
 */
public class ListCalendarEventsMonthRequestTask extends CalendarRequestTask<List<Event>> {
    private String mCalendarId;
    private Date mMonth;

    public ListCalendarEventsMonthRequestTask(GoogleAccountCredential credential, String calenderId,
            Date month) {
        super(credential);
        mCalendarId = calenderId;
        mMonth = month;
    }

    @Override
    protected List<Event> getDataFromApi() throws IOException {
        Date start = mMonth;
        start.setDate(0);
        DateTime dateTime = new DateTime(mMonth);
        Date end = (Date) start.clone();
        end.setMonth(start.getMonth() + 1);
        DateTime nextMonth = new DateTime(end);
        return getCalendarService().events().list(mCalendarId)
                .setTimeMin(dateTime)
                .setTimeMax(nextMonth)
                .execute()
                .getItems();
    }
}
