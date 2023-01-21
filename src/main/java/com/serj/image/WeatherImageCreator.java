package com.serj.image;

import com.serj.WeatherData;

import java.io.IOException;

public interface WeatherImageCreator {
    byte[] getWeatherImage(WeatherData weatherData) throws IOException;
}
