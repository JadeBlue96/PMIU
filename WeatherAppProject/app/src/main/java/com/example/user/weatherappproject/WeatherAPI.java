package com.example.user.weatherappproject;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by User on 16/02/2018.
 */

public interface WeatherAPI {
    @GET("/data/2.5/weather")
    void GetWeather(@Query("q") String City,
                    @Query("APPID") String app_ID,
                    Callback<WeatherData> callback);
}
