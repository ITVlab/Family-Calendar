package news.androidtv.familycalendar.model;

import java.util.HashMap;

/**
 * Created by Nick on 1/4/2017. A xollection of {@link CalendarColor} objects.
 *
 *
 */
public class CalendarColorMap {
    private static HashMap<String, CalendarColor> colorHashMap;

    static {
        colorHashMap = new HashMap<>();
        colorHashMap.put("1", new CalendarColor("#1d1d1d", "#ac725e", "#a4bdfc"));
        colorHashMap.put("2", new CalendarColor("#1d1d1d", "#d06b64", "#7ae7bf"));
        colorHashMap.put("3", new CalendarColor("#1d1d1d", "#f83a22", "#dbadff"));
        colorHashMap.put("4", new CalendarColor("#1d1d1d", "#fa573c", "#ff887c"));
        colorHashMap.put("5", new CalendarColor("#1d1d1d", "#ff7537", "#fbd75b"));
        colorHashMap.put("6", new CalendarColor("#1d1d1d", "#ffad46", "#ffb878"));
        colorHashMap.put("7", new CalendarColor("#1d1d1d", "#42d692", "#46d6db"));
        colorHashMap.put("8", new CalendarColor("#1d1d1d", "#16a765", "#e1e1e1"));
        colorHashMap.put("9", new CalendarColor("#1d1d1d", "#7bd148", "#5484ed"));
        colorHashMap.put("10", new CalendarColor("#1d1d1d", "#b3dc6c", "#51b749"));
        colorHashMap.put("11", new CalendarColor("#1d1d1d", "#fbe983", "#dc2127"));
        colorHashMap.put("12", new CalendarColor("#1d1d1d", "#fad165"));
        colorHashMap.put("13", new CalendarColor("#1d1d1d", "#92e1c0"));
        colorHashMap.put("14", new CalendarColor("#1d1d1d", "#9fe1e7"));
        colorHashMap.put("15", new CalendarColor("#1d1d1d", "#9fc6e7"));
        colorHashMap.put("16", new CalendarColor("#1d1d1d", "#4986e7"));
        colorHashMap.put("17", new CalendarColor("#1d1d1d", "#9a9cff"));
        colorHashMap.put("18", new CalendarColor("#1d1d1d", "#b99aff"));
        colorHashMap.put("19", new CalendarColor("#1d1d1d", "#c2c2c2"));
        colorHashMap.put("20", new CalendarColor("#1d1d1d", "#cabdbf"));
        colorHashMap.put("21", new CalendarColor("#1d1d1d", "#cca6ac"));
        colorHashMap.put("22", new CalendarColor("#1d1d1d", "#f691b2"));
        colorHashMap.put("23", new CalendarColor("#1d1d1d", "#cd74e6"));
        colorHashMap.put("24", new CalendarColor("#1d1d1d", "#a47ae2"));
    }

    public static CalendarColor getColors(String colorId) {
        if (!colorHashMap.containsKey(colorId)) {
            return null;
        }
        return colorHashMap.get(colorId);
    }
}
