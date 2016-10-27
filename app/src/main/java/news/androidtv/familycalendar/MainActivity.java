package news.androidtv.familycalendar;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int CALENDAR_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (PermissionChecker.checkSelfPermission(this, android.Manifest.permission.READ_CALENDAR) != PermissionChecker.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.READ_CALENDAR}, CALENDAR_REQUEST);
            } else {
                readCalendar();
            }
        } else {
            readCalendar();
        }
    }

    private void readCalendar() {
        Log.d(TAG, "Read cal");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Cursor cursor = getContentResolver().query(CalendarContract.Events.CONTENT_URI, new String[]{"calendar_id", "title", "description", "dtstart", "dtend", "eventLocation"}, null, null, null);
        //Cursor cursor = cr.query(Uri.parse("content://calendar/calendars"), new String[]{ "_id", "name" }, null, null, null);
        String add = null;
        cursor.moveToFirst();
        String[] CalNames = new String[cursor.getCount()];
        Log.d(TAG, cursor.getCount() + " entries");
        int[] CalIds = new int[cursor.getCount()];
        for (int i = 0; i < CalNames.length; i++) {
            CalIds[i] = cursor.getInt(0);
            CalNames[i] = "Event"+cursor.getInt(0)+": \nTitle: "+ cursor.getString(1)+"\nDescription: "+cursor.getString(2)+"\nStart Date: "+new Date(cursor.getLong(3))+"\nEnd Date : "+new Date(cursor.getLong(4))+"\nLocation : "+cursor.getString(5);
            if(add == null)
                add = CalNames[i];
            else{
                add += CalNames[i];
            }
            Log.d(TAG, add);

            cursor.moveToNext();
        }
        cursor.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALENDAR_REQUEST && grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
            readCalendar();
        }
    }
}
