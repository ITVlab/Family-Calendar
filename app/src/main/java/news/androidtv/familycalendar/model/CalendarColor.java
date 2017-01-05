package news.androidtv.familycalendar.model;

import android.graphics.Color;

/**
 * Created by Nick on 1/4/2017.
 *
 * Represents a calendar color.
 */

public class CalendarColor {
    public final Color foreground;
    public final Color background;

    public CalendarColor(Color fore, Color back) {
        foreground = fore;
        background = back;
    }
}
