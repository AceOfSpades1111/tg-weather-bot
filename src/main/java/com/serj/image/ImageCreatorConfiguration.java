package com.serj.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class ImageCreatorConfiguration {

    @Bean
    public Font font(@Value("${image-creator.font-name}") String fontName,
                     @Value("${image-creator.font-size}") int fontSize) {
        return new Font(fontName, Font.PLAIN, fontSize);
    }

    @Bean
    public ImageCreator<TopAndBottomTextPosition> imageCreator(Font font) {
        return new TopAndBottomImageCreator(font);
    }

    @Bean
    public WeatherImageCreator weatherImageCreator(ImageCreator<TopAndBottomTextPosition> imageCreator) {
        return new TopAndBottomWeatherImageCreator(imageCreator);
    }
}