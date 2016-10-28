package news.androidtv.familycalendar.model;

/**
 * Created by Nick on 10/28/2016.
 */
@Deprecated
public class CalendarList {
    private boolean mSelected;
    private String mId;
    private String mSummary;

    public CalendarList() {
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean mSelected) {
        this.mSelected = mSelected;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String mSummary) {
        this.mSummary = mSummary;
    }
}
