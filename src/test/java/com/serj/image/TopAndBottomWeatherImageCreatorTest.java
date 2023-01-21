package com.serj.image;

import com.serj.WeatherData;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopAndBottomWeatherImageCreatorTest {

    Font arialBlackFont = new Font("Arial Black", Font.PLAIN, 60);
    TopAndBottomImageCreator imageCreator = new TopAndBottomImageCreator(arialBlackFont);
    WeatherImageCreator weatherImageCreator = new TopAndBottomWeatherImageCreator(imageCreator);

    @Test
    void getWeatherImage() throws IOException {
        WeatherData weatherData = new WeatherData(278.11, 67, 1017, "04d");
        byte[] weatherImageBytes = weatherImageCreator.getWeatherImage(weatherData);

        // TODO increase testing
        //File file = new File("..\\1.png");
        //Files.write(file.toPath(), weatherImageBytes);

        BufferedImage result = ImageIO.read(new ByteArrayInputStream(weatherImageBytes));

        assertEquals(512, result.getHeight());
        assertEquals(512, result.getWidth());

        assertEquals(Color.BLACK.getRGB(), result.getRGB(175, 36));
        assertEquals(Color.BLACK.getRGB(), result.getRGB(282, 42));
        assertEquals(Color.WHITE.getRGB(), result.getRGB(258, 52));
        assertEquals(Color.WHITE.getRGB(), result.getRGB(200, 62));

        assertEquals(Color.BLACK.getRGB(), result.getRGB(242, 392));
        assertEquals(Color.BLACK.getRGB(), result.getRGB(276, 392));
        assertEquals(Color.WHITE.getRGB(), result.getRGB(244, 394));
        assertEquals(Color.WHITE.getRGB(), result.getRGB(272, 394));
    }
}