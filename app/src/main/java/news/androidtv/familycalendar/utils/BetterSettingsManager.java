package news.androidtv.familycalendar.utils;

import android.app.Activity;
import android.content.Context;

import com.felkertech.settingsmanager.SettingsManager;

/**
 * Created by Nick on 1/1/2017.
 */

public class BetterSettingsManager extends SettingsManager {
    public BetterSettingsManager(Context context) {
        super(context);
    }

    public boolean hasKey(String key) {
        return sharedPreferences.contains(key);
    }
}
