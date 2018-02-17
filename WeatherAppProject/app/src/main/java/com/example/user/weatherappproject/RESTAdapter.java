package com.example.user.weatherappproject;

import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by User on 16/02/2018.
 */

public class RESTAdapter {
    protected final String TAG=getClass().getSimpleName();
    protected RestAdapter m_adapter;
    protected WeatherAPI m_api;
    static final String WEATHER_URL="http://api.openweathermap.org";
    static final String KEY="22b004045adff0ce11c220d27794bbde";

    public RESTAdapter() {
        m_adapter=new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(WEATHER_URL)
                .setErrorHandler(new RESTErrorHandler())
                .build();

        m_api=m_adapter.create(WeatherAPI.class);
        Log.d(TAG,"Created a new REST adapter.");

    }

    public void getWeather(String city, Callback<WeatherData> callback)
    {
        Log.d(TAG,"Getting weather for "+city);
        m_api.GetWeather(city,KEY,callback);
    }
}
