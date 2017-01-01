package news.androidtv.familycalendar.adapters;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.api.services.calendar.model.Event;

import java.util.List;

import news.androidtv.familycalendar.R;
import news.androidtv.familycalendar.utils.CalendarUtils;

/**
 * Created by Nick on 12/31/2016.
 */

public class AgendaViewAdapter extends AbstractEventAdapter {
    private static final String TAG = AgendaViewAdapter.class.getSimpleName();
    private static final boolean DEBUG = false;

    public AgendaViewAdapter(Activity activity, List<Event> dataSource) {
        super(activity, dataSource);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layout = (LinearLayout) getLayoutInflator().inflate(R.layout.event_card_agenda,
                parent, false);
        ViewHolder vh = new ViewHolder(layout, viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Event event = getItemAt(position);
        ((TextView) holder.layout.findViewById(R.id.event_title)).setText(event.getSummary());
        ((TextView) holder.layout.findViewById(R.id.event_time)).setText(
                CalendarUtils.getEventStartEndAsString(event));
    }
}