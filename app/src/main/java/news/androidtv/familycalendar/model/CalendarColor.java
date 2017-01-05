package news.androidtv.familycalendar.model;

import android.graphics.Color;
import android.icu.util.Calendar;

/**
 * Created by Nick on 1/4/2017.
 *
 * Represents a calendar color.
 */

public class CalendarColor {
    public final int foreground;
    public final int background;

    public CalendarColor(int fore, int back) {
        foreground = fore;
        background = back;
    }

    public CalendarColor(String fore, String back) {
        foreground = Color.parseColor(fore);
        background = Color.parseColor(back);
    }
}
