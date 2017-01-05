package news.androidtv.familycalendar.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.felkertech.settingsmanager.SettingsManager;
import com.google.api.services.calendar.model.CalendarListEntry;

import java.util.List;

import news.androidtv.familycalendar.R;
import news.androidtv.familycalendar.activities.MainLeanbackActivity;
import news.androidtv.familycalendar.utils.CalendarUtils;
import news.androidtv.familycalendar.utils.SettingsConstants;

/**
 * This adapter displays each calendar and a checkbox to toggle its events as well as options
 * around the adapter.
 */
public class CalendarsAdapter extends RecyclerView.Adapter<CalendarsAdapter.ViewHolder> {
    private static final String TAG = CalendarsAdapter.class.getSimpleName();

    private static final int TYPE_CALENDAR = 0;
    private static final int TYPE_OPTION = 1;
    private static final int TYPE_NAVIGATION = 2;
    private static final int TYPE_CLOSE = 3;

    private Context mContext;
    private LayoutInflater mInflator;
    private List<CalendarListEntry> mDataList;
    private SettingsManager mSettingsManager;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout layout;
        public final int type;

        public ViewHolder(LinearLayout v, int type) {
            super(v);
            layout = v;
            this.type = type;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CalendarsAdapter(final Activity activity, List<CalendarListEntry> dataSource) {
        mContext = activity;
        mDataList = dataSource;
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSettingsManager = new SettingsManager(mContext);
    }

    @Override
    public int getItemCount() {
        if(mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }
    @Override
    public int getItemViewType(int pos) {
        if (pos == 0) {
            return TYPE_OPTION;
        }
        return TYPE_CALENDAR;
    }

    public CalendarListEntry getCalendar(int pos) {
        return mDataList.get(pos);
    }

    public Object getItemAt(int pos) {
        if (pos == 0) {
            return null;
        }
        return getCalendar(pos - 1);
    }

    public SettingsManager getSettingsManager() {
        return mSettingsManager;
    }

    public Context getContext() {
        return mContext;
    }

    public LayoutInflater getLayoutInflator() {
        return mInflator;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layout = null;
        switch (viewType) {
            case TYPE_CALENDAR:
                layout = (LinearLayout) mInflator.inflate(R.layout.calendar_entry, parent, false);
                break;
            case TYPE_OPTION:
                layout = (LinearLayout) mInflator.inflate(R.layout.button, parent, false);
                break;
        }
        if (layout != null) {
            return new ViewHolder(layout, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case TYPE_CALENDAR:
                final CalendarListEntry calendar = (CalendarListEntry) getItemAt(position);

                ((TextView) holder.itemView.findViewById(R.id.calendar_title))
                        .setText(calendar.getSummary());
                if (mDataList.get(position).getSelected() != null) {
                    ((CheckBox) holder.itemView.findViewById(R.id.calendar_selected))
                            .setChecked(CalendarUtils.isCalendarSelected(calendar, mContext));
                    holder.itemView.findViewById(R.id.calendar_selected)
                            .setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    new SettingsManager(mContext).setBoolean(
                                            SettingsConstants.CALENDAR_SELECTED(calendar.getId()),
                                            ((CheckBox) holder.itemView.findViewById(R.id.calendar_selected))
                                                    .isChecked());
                                    // Reload UI
                                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(
                                            new Intent(MainLeanbackActivity.ACTION_RESYNC));
                                }
                            });
                }
                break;
            case TYPE_OPTION:
                if (position == 0) {
                    ((Button) holder.itemView.findViewById(R.id.button)).setText("Settings");
                    holder.itemView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "No settings currently", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
    }
}
