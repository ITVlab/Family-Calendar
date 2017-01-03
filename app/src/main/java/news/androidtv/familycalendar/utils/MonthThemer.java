package news.androidtv.familycalendar.utils;

import java.util.HashMap;

import news.androidtv.familycalendar.R;

/**
 * Created by Nick on 1/1/2017.
 */

public class MonthThemer {
    private static final HashMap<Integer, Integer> mPrimary;
    private static final HashMap<Integer, Integer> mPrimaryDark;
    private static final HashMap<Integer, Integer> mSecondary;

    static {
        mPrimary = new HashMap<>();
        mPrimaryDark = new HashMap<>();
        mSecondary = new HashMap<>();

        mPrimary.put(0, R.color.md_cyan_600);
        mPrimaryDark.put(0, R.color.md_cyan_800);
        mSecondary.put(0, R.color.md_blue_grey_600);

        mPrimary.put(1, R.color.md_pink_600);
        mPrimaryDark.put(1, R.color.md_pink_900);
        mSecondary.put(1, R.color.md_light_blue_600);

        mPrimary.put(2, R.color.md_green_600);
        mPrimaryDark.put(2, R.color.md_green_800);
        mSecondary.put(2, R.color.md_amber_700);

        mPrimary.put(3, R.color.md_teal_600);
        mPrimaryDark.put(3, R.color.md_teal_800);
        mSecondary.put(3, R.color.md_deep_purple_600);

        mPrimary.put(4, R.color.md_red_600);
        mPrimaryDark.put(4, R.color.md_red_800);
        mSecondary.put(4, R.color.md_green_700);

        mPrimary.put(5, R.color.md_blue_600);
        mPrimaryDark.put(5, R.color.md_blue_800);
        mSecondary.put(5, R.color.md_lime_700);

        mPrimary.put(6, R.color.md_teal_600);
        mPrimaryDark.put(6, R.color.md_teal_800);
        mSecondary.put(6, R.color.md_light_green_700);

        mPrimary.put(7, R.color.md_deep_orange_600);
        mPrimaryDark.put(7, R.color.md_deep_orange_800);
        mSecondary.put(7, R.color.md_blue_700);

        mPrimary.put(8, R.color.md_lime_600);
        mPrimaryDark.put(8, R.color.md_lime_800);
        mSecondary.put(9, R.color.md_brown_600);

        mPrimary.put(9, R.color.md_orange_600);
        mPrimaryDark.put(9, R.color.md_orange_800);
        mSecondary.put(9, R.color.md_red_600);

        mPrimary.put(10, R.color.md_amber_700);
        mPrimaryDark.put(10, R.color.md_deep_orange_800);
        mSecondary.put(10, R.color.md_brown_600);

        mPrimary.put(11, R.color.md_blue_grey_600);
        mPrimaryDark.put(11, R.color.md_blue_grey_800);
        mSecondary.put(11, R.color.md_green_600);
    }

    public static int getPrimaryColor(int month) {
        return mPrimary.get(month);
    }

    public static int getPrimaryDarkColor(int month) {
        return mPrimaryDark.get(month);
    }

    public static int getSecondaryColor(int month) {
        return mSecondary.get(month);
    }
}
