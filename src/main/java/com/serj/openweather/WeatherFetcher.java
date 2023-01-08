package com.serj.openweather;

import com.serj.openweather.current.CurrentWeather;

public interface WeatherFetcher<T> {

    T fetch(double longitude, double latitude);
}