package com.serj.openweather.current;

import java.util.List;

// TODO check what fields required
public record CurrentWeather(Coordinate coord,
                             List<Weather> weather,
                             String base,
                             Main main,
                             int visibility,
                             Wind wind) {}

record Coordinate(double lon, double lat){}

record Weather(int id, String main, String description, String icon){}

record Main(double temp,
            double feelsLike,
            double tempMin,
            double tempMax,
            int pressure,
            int humidity,
            int seaLevel,
            int grndLevel){}

record Wind(double speed, int deg, double gust){}