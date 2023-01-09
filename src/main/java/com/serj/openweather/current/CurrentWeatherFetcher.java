package com.serj.openweather.current;

import com.serj.openweather.WeatherFetcher;
import org.springframework.web.client.RestTemplate;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CurrentWeatherFetcher implements WeatherFetcher<CurrentWeather> {

    private final String link;
    private final String apiKey;
    private final RestTemplate restTemplate;

    public CurrentWeatherFetcher(RestTemplate restTemplate, String link, String apiKey) {
        this.restTemplate = restTemplate;
        this.link = link;
        this.apiKey = apiKey;
    }

    @Override
    public CurrentWeather fetch(double longitude, double latitude) {
        return restTemplate.getForObject(createRequest(longitude, latitude), CurrentWeather.class);
    }

    private String createRequest(double longitude, double latitude) {
        return String.format(link, format(latitude), format(longitude), apiKey);
    }

    private static String format(double number) {
        DecimalFormat df = new DecimalFormat("0.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        return df.format(number);
    }
}