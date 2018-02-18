package com.example.user.weatherappproject.Main;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.weatherappproject.R;
import com.example.user.weatherappproject.Model.WeatherData;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    protected final String TAG=getClass().getSimpleName();
    private TestWeatherData mWeatherData;


    private ProgressBar mProgBar;
    private EditText m_in_city_name;
    private TextView m_city_name;
    private TextView m_country_name;
    private TextView m_coords;
    private TextView m_temp;
    private TextView m_sunrise;
    private TextView m_sunset;
    private TextView m_cod;
    private TextView m_wind;
    private TextView m_clouds;
    private TextView m_wmain;
    private TextView m_wdesc;
    private ImageView m_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mProgBar=(ProgressBar)findViewById(R.id.prog_id);
        m_in_city_name=(EditText)findViewById(R.id.input_city_id);

        if (savedInstanceState != null) {
            if (getLastCustomNonConfigurationInstance() != null) {
                mWeatherData = (TestWeatherData) getLastCustomNonConfigurationInstance();
                Log.d(TAG,"onCreate(): Reusing retained data set");
            }
        } else {
            mWeatherData = new TestWeatherData();
            Log.d(TAG, "onCreate(): Creating new  data set");
        }
        mWeatherData.setAppContext(this);
        if(mWeatherData.m_data!=null)
        {
            updateWeatherData(mWeatherData.m_data);
        }

        if(mWeatherData.isDownloadInProgress())
        {
            mProgBar.setVisibility(View.VISIBLE);
        }
        else
        {
            mProgBar.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherData.setAppContext(null);
        Log.d(TAG,"Destroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mWeatherData;
    }

    public void showWeather(View view) {
        hideKeyboard(MainActivity.this,m_in_city_name.getWindowToken());
        String city=m_in_city_name.getText().toString();
        if(city.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"No city or country has been entered!",Toast.LENGTH_LONG).show();
            return;
        }
        mWeatherData.TestAsyncCall(city);
    }

    //Hide keyboard after the URL has been typed
    public static void hideKeyboard(Activity activity,
                                    IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) activity.getSystemService
                (Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

    void updateWeatherData(final WeatherData data) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgBar = (ProgressBar) findViewById(R.id.prog_id);
                m_in_city_name = (EditText) findViewById(R.id.input_city_id);
                m_city_name = (TextView) findViewById(R.id.city_id);
                m_country_name = (TextView) findViewById(R.id.country_id);
                m_cod = (TextView) findViewById(R.id.cod_id);
                m_coords = (TextView) findViewById(R.id.coords_id);
                m_temp = (TextView) findViewById(R.id.temp_id);
                m_sunrise = (TextView) findViewById(R.id.sunrise_id);
                m_sunset = (TextView) findViewById(R.id.sunset_id);
                m_clouds=(TextView)findViewById(R.id.clouds_per_id);
                m_wind=(TextView)findViewById(R.id.wind_id);
                m_wmain=(TextView)findViewById(R.id.main_id);
                m_wdesc=(TextView)findViewById(R.id.desc_id);
                m_icon=(ImageView)findViewById(R.id.id_w_icon);


                if (mWeatherData.isDownloadInProgress()) {
                    mProgBar.setVisibility(View.VISIBLE);
                } else {
                    mProgBar.setVisibility(View.INVISIBLE);
                }

                Resources res = getResources();
                String Text ="City:" + data.getName();
                m_city_name.setText(Text);
                Text ="Country:" + data.getCountry();
                m_country_name.setText(Text);
                Text = "Coordinates:" +"(" + data.getLat() + "," + data.getLon() + ")";
                m_coords.setText(Text);
                Text = "COD:"+ data.getCod();
                m_cod.setText(Text);
                Text = String.format(Locale.UK, "Temperature: %.2f C", data.getTemp()-273.15);
                m_temp.setText(Text);
                Text="Weather status:"+data.getWeatherMain();
                m_wmain.setText(Text);
                Text="Weather Description:"+data.getWeatherDesc();
                m_wdesc.setText(Text);
                Text="Wind Speed:"+data.getWindSpd()+"m/s, Degrees:"+data.getWindDeg();
                m_wind.setText(Text);
                Text="Cloud Percentage:"+data.getCloudPer()+"%";
                m_clouds.setText(Text);
                DateFormat local = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.UK);
                Date sunrise = new Date(data.getSunrise() * 1000);
                Date sunset = new Date(data.getSunset() * 1000);
                Text = "Sunrise:" + local.format(sunrise);
                m_sunrise.setText(Text);
                Text = "Sunset:" + local.format(sunset);
                m_sunset.setText(Text);

                Text=data.getWeatherIcon();
                String iconURL="http://openweathermap.org/img/w/"+Text+".png";
                Picasso.with(getBaseContext()).load(iconURL).into(m_icon);

            }


        });
    }



    private static class TestWeatherData {
        private final String TAG = "WeatherTest";
        private WeakReference<MainActivity> m_ac_ref;
        private WeatherData m_data;
        private RESTAdapter rest_adapter;
        private AtomicBoolean download_in_progress = new AtomicBoolean(false);
        private Callback<WeatherData> m_callback = new Callback<WeatherData>() {
            @Override
            public void success(WeatherData weatherData, Response response) {
                DateFormat local = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss", Locale.UK);
                Date sunrise = new Date(weatherData.getSunrise() * 1000);
                Date sunset = new Date(weatherData.getSunset() * 1000);
                Log.d(TAG, "\nExecution success.. Name:" + weatherData.getName()
                        + ", Coordinates: (Latitude: " + weatherData.getLat() + ", Longtitude: " + weatherData.getLon() + ")"
                       + String.format(Locale.UK, "Temperature: %.2f C ", weatherData.getTemp()) +
                      "Pressure: " + weatherData.getPressure() + ", Humidity: " + weatherData.getHumidity() +
                        "Sunrise: " + local.format(sunrise) + ", Sunset: " + local.format(sunset)  +
                        "Country: " + weatherData.getCountry() + ", COD: " + weatherData.getCod());

                m_data = weatherData;
                if (m_ac_ref.get() != null) {
                    m_ac_ref.get().updateWeatherData(m_data);
                    m_ac_ref.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            m_ac_ref.get().mProgBar = (ProgressBar) m_ac_ref.get().findViewById(R.id.prog_id);
                            m_ac_ref.get().mProgBar.setVisibility(View.INVISIBLE);
                        }
                    });

                }
                download_in_progress.set(false);
            }

            @Override
            public void failure(RetrofitError error) {

                Log.d(TAG, "Failed to execute.");
                Toast.makeText(m_ac_ref.get(), "Failed to establish connection.", Toast.LENGTH_LONG).show();
                download_in_progress.set(false);
                if (m_ac_ref.get() != null) {

                    m_ac_ref.get().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            m_ac_ref.get().mProgBar = (ProgressBar) m_ac_ref.get().findViewById(R.id.prog_id);
                            m_ac_ref.get().mProgBar.setVisibility(View.INVISIBLE);
                        }
                    });

                }
            }


        };

        public void TestAsyncCall(final String city) {

            if (m_ac_ref.get() != null && download_in_progress.get()) {
                Toast.makeText(m_ac_ref.get(), "Downloading information...", Toast.LENGTH_LONG).show();
                return;
            }

            if (rest_adapter == null) {
                rest_adapter = new RESTAdapter();
            }

            if (m_ac_ref.get() != null) {
                m_ac_ref.get().mProgBar.setVisibility(View.VISIBLE);
            }


            try {
                download_in_progress.set(true);
                rest_adapter.getWeather(city, m_callback);
            } catch (Exception e) {
                Log.d(TAG, "Thread sleep error" + e);
            }

        }




        void setAppContext(MainActivity ref) {
            m_ac_ref = new WeakReference<>(ref);
        }

        boolean isDownloadInProgress() {
            return download_in_progress.get();
        }
    }
}
