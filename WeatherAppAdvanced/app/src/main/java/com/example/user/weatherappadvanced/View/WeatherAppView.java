package com.example.user.weatherappadvanced.View;

import com.example.user.weatherappadvanced.Model.WeatherData;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by User on 03/03/2018.
 */

public interface WeatherAppView extends MvpView {

    void showLoad();
    void hideLoad();

    void showErr(String message);
    void onWeatherObtained(WeatherData weatherData);

    void enableProgressBar();
    void disableProgressBar();
}
