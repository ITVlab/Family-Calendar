package news.androidtv.familycalendar.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.google.api.services.calendar.model.Event;

import java.util.List;

import news.androidtv.familycalendar.R;

/**
 * Created by Nick on 10/28/2016.
 */
public class MonthItemView extends View {
    private List<Event> mEvents;
    private int mDate;

    public MonthItemView(Context context) {
        super(context);
        init();
    }

    public MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MonthItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MonthItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
                } else {
                    v.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View card = layoutInflater.inflate(R.layout.month_card, null);
        if (isFocused()) {
            card.setBackgroundColor(getResources().getColor(R.color.colorAccentDark));
        }
        // TODO Create our adapter here
        card.draw(canvas);
    }


}
