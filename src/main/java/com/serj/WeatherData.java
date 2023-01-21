package com.serj;

public record WeatherData(double temperature, int humidity, int pressure, String icon) {

    public String formattedTemperature() {
        double temperatureDouble = temperature() - 273.15;
        String temperature = String.format("%10.1f", temperatureDouble).replace(',', '.');
        return temperature + "°C";
    }

    public String formattedHumidity() {
        return humidity() + "%";
    }
}