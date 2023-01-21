package com.serj.image;

import com.serj.WeatherData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TopAndBottomWeatherImageCreator implements WeatherImageCreator {

    private static final String WEATHER_IMAGES_DIR_PATH = "src\\main\\resources\\images\\weatherCondition\\512\\";
    private static final Map<String, File> WEATHER_IMAGES_BY_ICON = new HashMap<>();

    static {
        WEATHER_IMAGES_BY_ICON.put("01d", weatherImageFile("day_clear.png"));
        WEATHER_IMAGES_BY_ICON.put("02d", weatherImageFile("day_partial_cloud.png"));
        WEATHER_IMAGES_BY_ICON.put("03d", weatherImageFile("cloudy.png"));
        WEATHER_IMAGES_BY_ICON.put("04d", weatherImageFile("overcast.png"));
        WEATHER_IMAGES_BY_ICON.put("09d", weatherImageFile("rain.png"));
        WEATHER_IMAGES_BY_ICON.put("10d", weatherImageFile("day_rain.png"));
        WEATHER_IMAGES_BY_ICON.put("11d", weatherImageFile("day_rain_thunder.png"));
        WEATHER_IMAGES_BY_ICON.put("13d", weatherImageFile("day_snow.png"));
        WEATHER_IMAGES_BY_ICON.put("50d", weatherImageFile("mist.png"));
        WEATHER_IMAGES_BY_ICON.put("01n", weatherImageFile("night_half_moon_clear.png"));
        WEATHER_IMAGES_BY_ICON.put("02n", weatherImageFile("night_half_moon_partial_cloud.png"));
        WEATHER_IMAGES_BY_ICON.put("03n", weatherImageFile("cloudy.png"));
        WEATHER_IMAGES_BY_ICON.put("04n", weatherImageFile("overcast.png"));
        WEATHER_IMAGES_BY_ICON.put("09n", weatherImageFile("rain.png"));
        WEATHER_IMAGES_BY_ICON.put("10n", weatherImageFile("night_half_moon_rain.png"));
        WEATHER_IMAGES_BY_ICON.put("11n", weatherImageFile("night_half_moon_rain_thunder.png"));
        WEATHER_IMAGES_BY_ICON.put("13n", weatherImageFile("night_half_moon_snow.png"));
        WEATHER_IMAGES_BY_ICON.put("50n", weatherImageFile("mist.png"));
    }

    private final ImageCreator<TopAndBottomTextPosition> imageCreator;

    public TopAndBottomWeatherImageCreator(ImageCreator<TopAndBottomTextPosition> imageCreator) {
        this.imageCreator = imageCreator;
        System.out.println(WEATHER_IMAGES_BY_ICON);
    }

    @Override
    public byte[] getWeatherImage(WeatherData weatherData) throws IOException {

        Map<TopAndBottomTextPosition, String> map = new HashMap<>();
        map.put(TopAndBottomTextPosition.TOP, weatherData.formattedTemperature());
        map.put(TopAndBottomTextPosition.BOTTOM, weatherData.formattedHumidity());

        File imageFile = WEATHER_IMAGES_BY_ICON.get(weatherData.icon());
        return imageCreator.getImageByteArray(map, imageFile);
    }

    private static File weatherImageFile(String imageFileName) {
        String imageFileAbsolutePath = WEATHER_IMAGES_DIR_PATH + imageFileName;
        return new File(imageFileAbsolutePath);
    }
}
