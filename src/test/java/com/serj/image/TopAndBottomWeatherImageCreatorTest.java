package com.serj.image;

import org.junit.jupiter.api.Test;

import java.awt.*;

class TopAndBottomWeatherImageCreatorTest {

    Font arialBlackFont = new Font("Arial Black", Font.PLAIN, 60);
    TopAndBottomImageCreator imageCreator = new TopAndBottomImageCreator(arialBlackFont);
    WeatherImageCreator weatherImageCreator = new TopAndBottomWeatherImageCreator(imageCreator);

    @Test
    void getWeatherImage() {

    }
}