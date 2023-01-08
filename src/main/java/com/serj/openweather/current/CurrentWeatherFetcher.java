package com.serj.openweather.current;

import com.serj.openweather.WeatherFetcher;
import org.springframework.web.client.RestTemplate;

public class CurrentWeatherFetcher implements WeatherFetcher<CurrentWeather> {

    private final String link;
    private final String apiKey;

    public CurrentWeatherFetcher(String link, String apiKey) {
        this.link = link;
        this.apiKey = apiKey;

        fetch(44.34, 10.99);
    }

    private String createRequest(double latitude, double longitude) {
        return String.format(link, latitude, longitude, apiKey);
    }

    @Override
    public CurrentWeather fetch(double longitude, double latitude) {
        RestTemplate restTemplate = new RestTemplate();
        CurrentWeather forObject = restTemplate.getForObject(createRequest(latitude, longitude), CurrentWeather.class);
        System.out.println(forObject);
        return null;
    }
}