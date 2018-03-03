package com.example.user.weatherappproject.Main;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.weatherappproject.Adapter.RESTAdapter;
import com.example.user.weatherappproject.Model.WeatherData;
import com.example.user.weatherappproject.R;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 21/02/2018.
 */

public class TestWeatherData extends MainActivity {

        private final String TAG = "WeatherTest";
        private WeakReference<MainActivity> m_ac_ref;          //allows all elements in MainActivity to be garbage collected
        protected WeatherData m_data;
        private RESTAdapter rest_adapter;
        private AtomicBoolean download_in_progress = new AtomicBoolean(false);  //flag for multiple threads
        private Callback<WeatherData> m_callback = new Callback<WeatherData>() {     //generate a response from the server
            @Override
            public void success(WeatherData weatherData, Response response) {

                logQueriedDataOnSuccess(weatherData);
                m_data = weatherData;
                m_ac_ref.get().updateWeatherData(m_data);
                hideProgbar();

                download_in_progress.set(false);
            }

            @Override
            public void failure(RetrofitError error) {

                Log.d(TAG, "Failed to execute.");
                Toast.makeText(m_ac_ref.get(), "Failed to establish connection.", Toast.LENGTH_LONG).show();
                download_in_progress.set(false);
                hideProgbar();
            }


        };

        public void TestAsyncCall(final String city) {

            checkIfDownload();
            showProgbar();
            if (rest_adapter == null) {
                rest_adapter = new RESTAdapter();
            }


            ExecQueryTest(city);


        }


        public void ExecQueryTest(final String city) {
            try {                                           //Attempt to execute query to weather server
                download_in_progress.set(true);
                showProgbar();
                rest_adapter.getWeather(city, m_callback);
            } catch (Exception e) {
                Log.d(TAG, "Thread sleep error" + e);
            }

        }
        public void logQueriedDataOnSuccess(WeatherData data)
        {
            DateFormat local = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.UK);
            Date sunrise = new Date(data.getSunrise() * 1000);  //m/s to s
            Date sunset = new Date(data.getSunset() * 1000);
            Log.d(TAG, "\nExecution success.. Name:" + data.getName()
                    + ", Coordinates: (Latitude: " + data.getLat() + ", Longtitude: " + data.getLon() + " ), "
                    + String.format(Locale.UK, "Temperature: %.2f C ,", data.getTemp()) +
                    " Pressure: " + data.getPressure() + ", Humidity: , " + data.getHumidity() +
                    "Sunrise: " + local.format(sunrise) + ", Sunset: , " + local.format(sunset)  +
                    "Country: " + data.getCountry() + ", COD: " + data.getCod());
        }
        public void hideProgbar()
        {
            if (m_ac_ref.get() != null) {
                m_ac_ref.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        m_ac_ref.get().progBar = (ProgressBar) m_ac_ref.get().findViewById(R.id.prog_id);
                        m_ac_ref.get().progBar.setVisibility(View.INVISIBLE);
                    }
                });

            }
        }
        public void showProgbar()
        {
            if (m_ac_ref.get() != null) {
                m_ac_ref.get().progBar.setVisibility(View.VISIBLE);
            }
        }
        public void checkIfDownload() {
            if (m_ac_ref.get() != null && download_in_progress.get()) {
                Toast.makeText(m_ac_ref.get(), "Downloading information...", Toast.LENGTH_LONG).show();
                return;
            }
        }
        void setAppContext(MainActivity ref) {
            m_ac_ref = new WeakReference<>(ref);
        }

        boolean isDownloadInProgress() {
            return download_in_progress.get();
        } //set to true right before weather query

}
