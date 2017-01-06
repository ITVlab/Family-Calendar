package news.androidtv.familycalendar.activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import news.androidtv.familycalendar.R;

/**
 * Created by Nick on 1/5/2017.
 */

public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_fragment);
    }
}