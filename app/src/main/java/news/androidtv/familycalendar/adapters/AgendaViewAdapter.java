package news.androidtv.familycalendar.adapters;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.api.services.calendar.model.Event;

import java.util.Date;
import java.util.List;

import news.androidtv.familycalendar.R;
import news.androidtv.familycalendar.model.CalendarColorMap;
import news.androidtv.familycalendar.utils.CalendarUtils;
import news.androidtv.familycalendar.utils.MonthThemer;

/**
 * Created by Nick on 12/31/2016.
 */
public class AgendaViewAdapter extends AbstractEventAdapter {
    private static final String TAG = AgendaViewAdapter.class.getSimpleName();
    private static final boolean DEBUG = false;

    private int mIndex;

    public AgendaViewAdapter(Activity activity, List<Event> dataSource, EventHandler eventHandler) {
        super(activity, dataSource, eventHandler);
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
        if (event.getLocation() != null) {
            ((TextView) holder.layout.findViewById(R.id.event_location)).setText(
                    event.getLocation());
        }
        if (event.getColorId() != null) {
            Log.d(TAG, "Color: " + event.getColorId());
            holder.layout.setBackgroundColor(getContext().getResources()
                    .getColor(CalendarColorMap.getColors(event.getColorId()).background));
        } else if (event.getStart().getDateTime() != null) {
            holder.layout.setBackgroundColor(getContext().getResources()
                    .getColor(MonthThemer.getPrimaryDarkColor(new Date(event.getStart()
                            .getDateTime().getValue()).getMonth())));
        } else if (event.getStart().getDate() != null) {
            holder.layout.setBackgroundColor(getContext().getResources()
                    .getColor(MonthThemer.getPrimaryDarkColor(new Date(event.getStart()
                            .getDate().getValue()).getMonth())));
        }
    }

    @Override
    public void handleKeyEvent(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                mIndex--;
                if (mIndex < 0) {
                    getEventHandler().onJumpToMonth(-1);
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                mIndex++;
                if (mIndex >= getDataList().size()) {
                    getEventHandler().onJumpToMonth(1);
                }
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_BUTTON_A:
            case KeyEvent.KEYCODE_A:
                displayPopup(mIndex);
                break;
        }
    }

    @Override
    public void focusNewSelectedElement(RecyclerView recyclerView, Date currentMonth) {
        if (recyclerView.findViewHolderForAdapterPosition(mIndex) == null) {
            return;
        }
        // Focus new item
        recyclerView.findViewHolderForAdapterPosition(mIndex).itemView.setBackgroundColor(
                getContext().getResources().getColor(MonthThemer.getSecondaryColor(currentMonth.getMonth())));
        recyclerView.scrollToPosition(mIndex);
        ((LinearLayoutManager) recyclerView.getLayoutManager())
                .scrollToPositionWithOffset(mIndex, 60);
    }

    @Override
    public void unfocusPreviousSelectedElement(RecyclerView recyclerView, Date currentMonth) {
        if (recyclerView.findViewHolderForAdapterPosition(mIndex) == null) {
            return;
        }
        // Unfocus current item
        int originalColor = getItemAt(mIndex).getColorId() == null ?
                MonthThemer.getPrimaryDarkColor(currentMonth.getMonth()) :
                CalendarColorMap.getColors(getItemAt(mIndex).getColorId()).background;
        recyclerView.findViewHolderForAdapterPosition(mIndex).itemView.setBackgroundColor(
            getContext().getResources().getColor(originalColor));
    }
}