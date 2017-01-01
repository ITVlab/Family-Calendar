package news.androidtv.familycalendar.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;

import java.io.IOException;

import news.androidtv.familycalendar.shims.Consumer;

/**
 * Created by Nick on 10/28/2016.
 */
public abstract class CalendarRequestTask<T> extends AsyncTask<Void, Void, T> {
    protected static final String TAG = CalendarRequestTask.class.getSimpleName();

    private Exception mLastError = null;
    private com.google.api.services.calendar.Calendar mService = null;
    private GoogleAccountCredential mCredential;
    private Consumer<T> mPostConsumer;

    public CalendarRequestTask(GoogleAccountCredential credential) {
        mCredential = credential;
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        mService = new com.google.api.services.calendar.Calendar.Builder(
                transport, jsonFactory, credential)
                .setApplicationName("Family Calendar")
                .build();
    }

    public CalendarRequestTask<T> setPostConsumer(Consumer<T> doAfter) {
        mPostConsumer = doAfter;
        return this;
    }

    protected Calendar getCalendarService() {
        return mService;
    }

    protected GoogleAccountCredential getCredential() {
        return mCredential;
    }

    /**
     * Background task to call Google Calendar API.
     * @param params no parameters needed for this task.
     */
    @Override
    protected T doInBackground(Void... params) {
        try {
            return getDataFromApi();
        } catch (Exception e) {
            mLastError = e;
            cancel(true);
            return null;
        }
    }

    /**
     * Fetch a list of the next 10 events from the primary calendar.
     * @return List of Strings describing returned events.
     * @throws IOException
     */
    protected abstract T getDataFromApi() throws IOException;


    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(T output) {
        Log.d(TAG, "Task finished");
        if (mPostConsumer != null) {
            mPostConsumer.consume(output);
        }
    }

    @Override
    protected void onCancelled() {
    }
}
