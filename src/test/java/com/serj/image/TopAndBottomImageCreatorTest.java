package com.serj.image;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopAndBottomImageCreatorTest {

    Font arialBlackFont = new Font("Arial Black", Font.PLAIN, 60);
    TopAndBottomImageCreator imageCreator = new TopAndBottomImageCreator(arialBlackFont);

    @Test
    void getImageByteArray() throws IOException {
        Map<TopAndBottomTextPosition, String> textToView = Map.of(TopAndBottomTextPosition.TOP, "topText",
                TopAndBottomTextPosition.BOTTOM, "bottomText");

        //System.out.println(Paths.get("").toAbsolutePath().toString());

        File image = new File("src\\main\\resources\\images\\weatherCondition\\512\\night_half_moon_rain.png");
        byte[] imageByteArray = imageCreator.getImageByteArray(textToView, image);

        // TODO delete in future
        //File file = new File("..\\1.png");
        //Files.write(file.toPath(), imageByteArray);

        // TODO increase testing
        BufferedImage result = ImageIO.read(new ByteArrayInputStream(imageByteArray));

        assertEquals(512, result.getHeight());
        assertEquals(512, result.getWidth());

        assertEquals(Color.BLACK.getRGB(), result.getRGB(194, 46));
        assertEquals(Color.BLACK.getRGB(), result.getRGB(194, 89));
        assertEquals(Color.WHITE.getRGB(), result.getRGB(196, 49));
        assertEquals(Color.WHITE.getRGB(), result.getRGB(196, 86));

        assertEquals(Color.BLACK.getRGB(), result.getRGB(69, 392));
        assertEquals(Color.BLACK.getRGB(), result.getRGB(69, 432));
        assertEquals(Color.WHITE.getRGB(), result.getRGB(72, 394));
        assertEquals(Color.WHITE.getRGB(), result.getRGB(72, 431));
    }
}