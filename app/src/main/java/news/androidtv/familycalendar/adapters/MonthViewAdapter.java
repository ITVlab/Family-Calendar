package news.androidtv.familycalendar.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import news.androidtv.familycalendar.R;
import news.androidtv.familycalendar.model.CalendarColorMap;
import news.androidtv.familycalendar.utils.CalendarUtils;
import news.androidtv.familycalendar.utils.MonthThemer;

/**
 * Created by Nick on 1/5/2017.
 *
 * Displays a month of events in a classic month view.
 */
public class MonthViewAdapter extends AbstractEventAdapter {
    private static final String TAG = MonthViewAdapter.class.getSimpleName();

    private static final int TYPE_DATE = 0;
    private static final int TYPE_DOW = 1;

    private int mIndex = 6;

    public MonthViewAdapter(Context context, List<Event> dataSource, EventHandler eventHandler) {
        super(context, dataSource, eventHandler);
    }

    @Override
    public int getItemCount() {
        // Get current month.
        // Assume it's in the event data.
        Event middle = getDataList().get(getDataList().size() / 2);
        if (middle.getStart().getDate() != null) {
            Date date = new Date(middle.getStart().getDate().getValue());
            GregorianCalendar calendar =
                    new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay());
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 7;
        }
        return 31 + 7; // As a precaution.
    }

    @Override
    public int getItemViewType(int pos) {
        if (pos <= 6) {
            return TYPE_DOW;
        }
        return TYPE_DATE;
    }

    @Override
    public void handleKeyEvent(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                mIndex--;
                if (mIndex < 6) {
                    getEventHandler().onJumpToMonth(-1);
                }
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                mIndex++;
                if (mIndex >= getDataList().size() + 7) {
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
        ((GridLayoutManager) recyclerView.getLayoutManager())
                .scrollToPositionWithOffset(mIndex, 60);
    }

    @Override
    public void unfocusPreviousSelectedElement(RecyclerView recyclerView, Date currentMonth) {
        if (recyclerView.findViewHolderForAdapterPosition(mIndex) == null) {
            return;
        }
        // Unfocus current item
        int originalColor = Color.parseColor("#333333");
        recyclerView.findViewHolderForAdapterPosition(mIndex).itemView
                .setBackgroundColor(originalColor);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh = null;
        switch (viewType) {
            case TYPE_DATE:
                LinearLayout layout = (LinearLayout) getLayoutInflator().inflate(R.layout.month_card,
                    parent, false);
                vh = new ViewHolder(layout, viewType);
                break;
            case TYPE_DOW:
                layout = (LinearLayout) getLayoutInflator().inflate(R.layout.month_view_heading,
                    parent, false);
                vh = new ViewHolder(layout, viewType);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        switch (holder.type) {
            case TYPE_DATE:
                ((TextView) holder.itemView.findViewById(R.id.date)).setText((position - 6) + "");
                LinearLayout layout = (LinearLayout) holder.layout.findViewById(R.id.list);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.removeAllViews();
                for (Event event : getDataList()) {
                    if (event.getStart().getDateTime() != null &&
                            new Date(event.getStart().getDateTime().getValue()).getDate() == position - 6) {
                        Log.d(TAG, event.getSummary() + " " + position);
                        layout.addView(getEventView(event));
                    }
                }
                break;
            case TYPE_DOW:
                ((TextView) holder.itemView.findViewById(R.id.heading)).setText(position + " day");
        }
    }

    private View getEventView(Event event) {
        LinearLayout layout =
                (LinearLayout) getLayoutInflator().inflate(R.layout.event_card, null, false);
        ((TextView) layout.findViewById(R.id.event_title)).setText(event.getSummary());
        ((TextView)  layout.findViewById(R.id.event_time)).setText(
                CalendarUtils.getEventStartEndTimesAsString(event));

        if (event.getColorId() != null) {
            Log.d(TAG, "Color: " + event.getColorId());
            layout.setBackgroundColor(getContext().getResources()
                    .getColor(CalendarColorMap.getColors(event.getColorId()).background));
        } else if (event.getStart().getDateTime() != null) {
            layout.setBackgroundColor(getContext().getResources()
                    .getColor(MonthThemer.getPrimaryDarkColor(new Date(event.getStart()
                            .getDateTime().getValue()).getMonth())));
        } else if (event.getStart().getDate() != null) {
            layout.setBackgroundColor(getContext().getResources()
                    .getColor(MonthThemer.getPrimaryDarkColor(new Date(event.getStart()
                            .getDate().getValue()).getMonth())));
        }
        return layout;
    }

    @Override
    public void displayPopup(int position) {
        // Display all events in that day
        List<Event> dayEvents = new ArrayList<>();
        for (Event event : getDataList()) {
            if (event.getStart().getDateTime() != null &&
                    new Date(event.getStart().getDateTime().getValue()).getDate() == position - 6) {
                dayEvents.add(event);
            }
        }
        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new AgendaViewAdapter(getContext(), dayEvents, getEventHandler()));

        new AlertDialog.Builder(getContext())
                .setTitle("Month " + (position - 6))
                .setView(rv)
                .show();
    }
}
