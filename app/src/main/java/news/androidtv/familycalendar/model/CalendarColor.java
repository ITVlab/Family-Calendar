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
    public final int backgroundSecondary;

    public CalendarColor(int fore, int back, int backSecondary) {
        foreground = fore;
        background = back;
        backgroundSecondary = backSecondary;
    }

    public CalendarColor(String fore, String back) {
        foreground = Color.parseColor(fore);
        background = Color.parseColor(back);
        backgroundSecondary = Color.parseColor(back);
    }

    public CalendarColor(String fore, String back, String backSecondary) {
        foreground = Color.parseColor(fore);
        background = Color.parseColor(back);
        backgroundSecondary = Color.parseColor(backSecondary);
    }
}
