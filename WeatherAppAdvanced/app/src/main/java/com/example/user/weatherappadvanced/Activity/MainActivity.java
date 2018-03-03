package com.example.user.weatherappadvanced.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.weatherappadvanced.Model.WeatherData;
import com.example.user.weatherappadvanced.Presenter.WeatherAppPresenter;
import com.example.user.weatherappadvanced.R;
import com.example.user.weatherappadvanced.View.WeatherAppView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends MvpActivity<WeatherAppView,WeatherAppPresenter> implements WeatherAppView {

    @BindView(R.id.prog_id) ProgressBar progBar;
    @BindView(R.id.input_city_id) EditText in_city_name;
    @BindView(R.id.city_id) TextView city_name;
    @BindView(R.id.country_id)  TextView country_name;
    @BindView(R.id.coords_id)  TextView coords;
    @BindView(R.id.temp_id)  TextView temp;
    @BindView(R.id.sunrise_id)  TextView sunrise;
    @BindView(R.id.sunset_id)  TextView sunset;
    @BindView(R.id.cod_id) TextView cod;
    @BindView(R.id.wind_id)  TextView wind;
    @BindView(R.id.clouds_per_id)  TextView clouds;
    @BindView(R.id.main_id)  TextView wmain;
    @BindView(R.id.desc_id) TextView wdesc;
    @BindView(R.id.id_w_icon)  ImageView icon;

    private Unbinder unbinder;
    private ProgressDialog progressDialog;
    private ViewGroup rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder=ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        rootLayout=findViewById(R.id.root_layout);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.downloading_info));
        disableProgressBar();
        setSupportActionBar(toolbar);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unbinder.unbind();
    }

    @NonNull
    @Override
    public WeatherAppPresenter createPresenter() {
        return new WeatherAppPresenter();
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
        return super.onOptionsItemSelected(item);
    }





    @Override
    public void showLoad() {
        progressDialog.show();
        enableProgressBar();
    }

    @Override
    public void hideLoad() {
        progressDialog.hide();
        disableProgressBar();
    }

    @Override
    public void showErr(String message) {
        hideLoad();
        Snackbar.make(rootLayout,message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onWeatherObtained(WeatherData weatherData) {
        hideLoad();
        displayWeather(weatherData);
    }

    @Override
    public void enableProgressBar() {
        progBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableProgressBar() {
        progBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @OnClick(R.id.show_weather_id)
    public void showWeather(View view) {
        hideKeyboard(MainActivity.this,in_city_name.getWindowToken()); //Retrieve a unique token identifying the window this view is attached to
        String city=in_city_name.getText().toString();
        if(city.isEmpty())
        {
            progressDialog.setMessage(getString(R.string.no_city_entered));
            showLoad();
            return;
        }
        presenter.ObtainWeather(city);
    }


    public void hideKeyboard(Activity activity,
                             IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) activity.getSystemService
                (Context.INPUT_METHOD_SERVICE);   //get a handle for the current service
        if (mgr != null) {
            mgr.hideSoftInputFromWindow(windowToken, 0);  //hide input window for current token 0-HIDE_IMPLICIT_ONLY
        }
    }

    public void displayWeather(WeatherData weatherData) {
        String Text ="City:" + weatherData.getName();             //City Name
        city_name.setText(Text);
        Text ="Country:" + weatherData.getCountry();              //Country Name
        country_name.setText(Text);
        Text = "Coordinates:" +"(" + weatherData.getLat() + "," + weatherData.getLon() + ")";       //Coordinates
        coords.setText(Text);
        Text = "COD:"+ weatherData.getCod();                           //Code
        cod.setText(Text);
        Text = String.format(Locale.UK, "Temperature: %.2f C", weatherData.getTemp()-273.15);        //Temperature in Celsius
        temp.setText(Text);
        Text="Weather status:"+weatherData.getWeatherMain();           //Weather main status
        wmain.setText(Text);
        Text="Weather Description:"+weatherData.getWeatherDesc();      //Weather description
        wdesc.setText(Text);
        Text="Wind Speed:"+weatherData.getWindSpd()+"m/s, Degrees:"+weatherData.getWindDeg();         //Wind speed in m/s
        wind.setText(Text);
        Text="Cloud Percentage:"+weatherData.getCloudPer()+"%";                //Clouds percentage
        clouds.setText(Text);
        DateFormat local = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.UK);
        Date d_sunrise = new Date(weatherData.getSunrise() * 1000);           //Sunrise date up to seconds
        Date d_sunset = new Date(weatherData.getSunset() * 1000);              //Sunset date up to seconds
        Text = "Sunrise:" + local.format(d_sunrise);
        sunrise.setText(Text);
        Text = "Sunset:" + local.format(d_sunset);
        sunset.setText(Text);
        Text=weatherData.getWeatherIcon();
        String iconURL="http://openweathermap.org/img/w/"+Text+".png";       //Weather icon ID information
        Picasso.with(getBaseContext()).load(iconURL).into(icon);
    }
}
