package news.androidtv.familycalendar.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.felkertech.settingsmanager.SettingsManager;
import com.google.api.services.calendar.model.CalendarListEntry;

import java.util.List;

import news.androidtv.familycalendar.R;

/**
 * This adapter displays each calendar and a checkbox to toggle its events as well as options
 * around the adapter.
 */
public class CalendarsAdapter extends RecyclerView.Adapter<CalendarsAdapter.ViewHolder> {
    private static final String TAG = CalendarsAdapter.class.getSimpleName();

    private static final int TYPE_CALENDAR = 0;
    private static final int TYPE_OPTION = 1;
    private static final int TYPE_NAVIGATION = 2;

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

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }
    @Override
    public int getItemViewType(int pos) {
        return 0;
    }

    public CalendarListEntry getItemAt(int pos) {
        return mDataList.get(pos);
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
        return new ViewHolder((LinearLayout)
                mInflator.inflate(R.layout.calendar_entry, parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ((TextView) holder.itemView.findViewById(R.id.calendar_title))
                .setText(mDataList.get(position).getSummary());
        if (mDataList.get(position).getSelected() != null) {
            ((CheckBox) holder.itemView.findViewById(R.id.calendar_selected))
                    .setChecked(mDataList.get(position).getSelected());
        }
    }
}
