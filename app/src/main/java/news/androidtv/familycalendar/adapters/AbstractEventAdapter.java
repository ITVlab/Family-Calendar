package news.androidtv.familycalendar.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.felkertech.settingsmanager.SettingsManager;
import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract adapter which shows events..
 */
public abstract class AbstractEventAdapter extends
        RecyclerView.Adapter<AbstractEventAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflator;
    private List<Event> mDataList;
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
    public AbstractEventAdapter(final Activity activity, List<Event> dataSource) {
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

    public Event getItemAt(int pos) {
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
}
