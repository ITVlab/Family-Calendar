package news.androidtv.familycalendar.utils;

import android.util.Log;

import com.google.api.services.calendar.model.Event;

import java.util.Comparator;

/**
 * Created by Nick on 12/31/2016.
 */

public class EventComparator implements Comparator<Event> {
    private static final String TAG = EventComparator.class.getSimpleName();

    @Override
    public int compare(Event event, Event t1) {
//        Log.d(TAG, event.getStart().toString() + "< " + getStartingTimestamp(event));
//        Log.d(TAG, t1.getStart().toString() + "<< " + getStartingTimestamp(t1));
        return (int) (getStartingTimestamp(event) - getStartingTimestamp(t1));
    }

    private long getStartingTimestamp(Event event) {
        if (event.getStart() != null && event.getStart().getDateTime() != null) {
            return event.getStart().getDateTime().getValue();
        } else if (event.getStart() != null && event.getStart().getDate() != null) {
            return event.getStart().getDate().getValue();
        } else if (event.getStart() == null && event.getCreated() != null) {
            return event.getCreated().getValue();
        }
        return 0;
    }

    private long getEndingTimestamp(Event event) {
        if (event.getEnd() != null && event.getEnd().getDateTime() != null) {
            return event.getEnd().getDateTime().getValue();
        } else if (event.getEnd() != null && event.getEnd().getDate() != null) {
            return event.getEnd().getDate().getValue();
        } else if (event.getEnd() == null && event.getCreated() != null) {
            return event.getCreated().getValue();
        }
        return 0;
    }
}
