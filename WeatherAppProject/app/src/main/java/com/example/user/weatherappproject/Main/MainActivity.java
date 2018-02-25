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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    protected final String TAG=getClass().getSimpleName();
    private TestWeatherData weatherData;


    protected ProgressBar progBar;
    private EditText in_city_name;
    private TextView city_name;
    private TextView country_name;
    private TextView coords;
    private TextView temp;
    private TextView sunrise;
    private TextView sunset;
    private TextView cod;
    private TextView wind;
    private TextView clouds;
    private TextView wmain;
    private TextView wdesc;
    private ImageView icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        progBar=(ProgressBar)findViewById(R.id.prog_id);
        in_city_name=(EditText)findViewById(R.id.input_city_id);

        if (savedInstanceState != null) {
            if (getLastCustomNonConfigurationInstance() != null) { //retain previous loading program state
                weatherData = (TestWeatherData) getLastCustomNonConfigurationInstance();
                Log.d(TAG,"onCreate(): Reusing retained data set");
            }
        } else {
            weatherData = new TestWeatherData();
            Log.d(TAG, "onCreate(): Creating new  data set");
        }
        weatherData.setAppContext(this);            //Create a new WeakReference to MainActivity
        if(weatherData.m_data!=null)
        {
            updateWeatherData(weatherData.m_data);
        }
        setProgBarVisibility();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        weatherData.setAppContext(null);            //WeatherData references NULL
        Log.d(TAG,"Destroy");
    }



    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return weatherData;
    } //retain previous loading program state

    public void showWeather(View view) {
        hideKeyboard(MainActivity.this,in_city_name.getWindowToken()); //Retrieve a unique token identifying the window this view is attached to
        String city=in_city_name.getText().toString();
        if(city.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"No city or country has been entered!",Toast.LENGTH_LONG).show();
            return;
        }
        weatherData.TestAsyncCall(city);
    }

    //Hide keyboard after the URL has been typed
    public static void hideKeyboard(Activity activity,
                                    IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) activity.getSystemService
                (Context.INPUT_METHOD_SERVICE);   //get a handle for the current service
        mgr.hideSoftInputFromWindow(windowToken, 0);  //hide input window for current token 0-HIDE_IMPLICIT_ONLY
    }

    public void setProgBarVisibility()
    {
        if(weatherData.isDownloadInProgress())
        {
            progBar.setVisibility(View.VISIBLE);
        }
        else
        {
            progBar.setVisibility(View.INVISIBLE);
        }
    }

    void formatReceivedData(final WeatherData data)
    {
        String Text ="City:" + data.getName();             //City Name
        city_name.setText(Text);
        Text ="Country:" + data.getCountry();              //Country Name
        country_name.setText(Text);
        Text = "Coordinates:" +"(" + data.getLat() + "," + data.getLon() + ")";       //Coordinates
        coords.setText(Text);
        Text = "COD:"+ data.getCod();                           //Code
        cod.setText(Text);
        Text = String.format(Locale.UK, "Temperature: %.2f C", data.getTemp()-273.15);        //Temperature in Celsius
        temp.setText(Text);
        Text="Weather status:"+data.getWeatherMain();           //Weather main status
        wmain.setText(Text);
        Text="Weather Description:"+data.getWeatherDesc();      //Weather description
        wdesc.setText(Text);
        Text="Wind Speed:"+data.getWindSpd()+"m/s, Degrees:"+data.getWindDeg();         //Wind speed in m/s
        wind.setText(Text);
        Text="Cloud Percentage:"+data.getCloudPer()+"%";                //Clouds percentage
        clouds.setText(Text);
        DateFormat local = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.UK);
        Date d_sunrise = new Date(data.getSunrise() * 1000);           //Sunrise date up to seconds
        Date d_sunset = new Date(data.getSunset() * 1000);              //Sunset date up to seconds
        Text = "Sunrise:" + local.format(d_sunrise);
        sunrise.setText(Text);
        Text = "Sunset:" + local.format(d_sunset);
        sunset.setText(Text);
        Text=data.getWeatherIcon();
        String iconURL="http://openweathermap.org/img/w/"+Text+".png";       //Weather icon ID information
        Picasso.with(getBaseContext()).load(iconURL).into(icon);
    }
    void updateWeatherData(final WeatherData data) {


                progBar = (ProgressBar) findViewById(R.id.prog_id);
                in_city_name = (EditText) findViewById(R.id.input_city_id);
                city_name = (TextView) findViewById(R.id.city_id);
                country_name = (TextView) findViewById(R.id.country_id);
                cod = (TextView) findViewById(R.id.cod_id);
                coords = (TextView) findViewById(R.id.coords_id);
                temp = (TextView) findViewById(R.id.temp_id);
                sunrise = (TextView) findViewById(R.id.sunrise_id);
                sunset = (TextView) findViewById(R.id.sunset_id);
                clouds=(TextView)findViewById(R.id.clouds_per_id);
                wind=(TextView)findViewById(R.id.wind_id);
                wmain=(TextView)findViewById(R.id.main_id);
                wdesc=(TextView)findViewById(R.id.desc_id);
                icon=(ImageView)findViewById(R.id.id_w_icon);


                setProgBarVisibility();
                formatReceivedData(data);


    }





}
