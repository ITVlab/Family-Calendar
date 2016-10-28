package news.androidtv.familycalendar.tasks;

import android.os.AsyncTask;
import android.provider.CalendarContract;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;

import java.io.IOException;
import java.util.List;

/**
 * Created by Nick on 10/28/2016.
 */
public abstract class CalendarRequestTask<T> extends AsyncTask<Void, Void, T> {
    protected static final String TAG = CalendarRequestTask.class.getSimpleName();

    private Exception mLastError = null;
    private com.google.api.services.calendar.Calendar mService = null;
    private GoogleAccountCredential mCredential;

    public CalendarRequestTask(GoogleAccountCredential credential) {
        mCredential = credential;
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        mService = new com.google.api.services.calendar.Calendar.Builder(
                transport, jsonFactory, credential)
                .setApplicationName("Family Calendar")
                .build();
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
        /*mOutputText.setText("");
        mProgress.show();*/
    }

    @Override
    protected void onPostExecute(T output) {
        /*mProgress.hide();
        if (output == null || output.size() == 0) {
            mOutputText.setText("No results returned.");
        } else {
            output.add(0, "Data retrieved using the Google Calendar API:");
            mOutputText.setText(TextUtils.join("\n", output));
        }*/
    }

    @Override
    protected void onCancelled() {
        /*mProgress.hide();
        if (mLastError != null) {
            if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                showGooglePlayServicesAvailabilityErrorDialog(
                        ((GooglePlayServicesAvailabilityIOException) mLastError)
                                .getConnectionStatusCode());
            } else if (mLastError instanceof UserRecoverableAuthIOException) {
                startActivityForResult(
                        ((UserRecoverableAuthIOException) mLastError).getIntent(),
                        QuickStartActivity.REQUEST_AUTHORIZATION);
            } else {
                mOutputText.setText("The following error occurred:\n"
                        + mLastError.getMessage());
            }
        } else {
            mOutputText.setText("Request cancelled.");
        }*/
    }
}
