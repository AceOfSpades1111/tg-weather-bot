package com.serj.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TopAndBottomImageCreator implements ImageCreator<TopAndBottomTextPosition> {

    private final Font font;

    public TopAndBottomImageCreator(Font font) {
        this.font = font;
    }

    private static final RenderingHints RENDERING_HINTS = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private static final Color FILL_COLOR = Color.WHITE;
    private static final Color BORDER_COLOR = Color.BLACK;
    // TODO maybe some calculations for this numbers
    private static final double HEIGHT_FONT_FACTOR = 1.3;
    private static final double WIDTH_FONT_FACTOR = 0.33;
    private static final float BORDER_THICKNESS_FACTOR = 20.0F;


    public byte[] getImageByteArray(Map<TopAndBottomTextPosition, String> map, File imageFile) throws IOException {

        BufferedImage img = ImageIO.read(imageFile);
        Graphics2D graphics = img.createGraphics();

        graphics.setRenderingHints(RENDERING_HINTS);

        map.forEach((key, value) -> addText(graphics, key, value, img.getWidth(), img.getHeight()));
        graphics.dispose();

        return bytesFromImage(img);
    }

    private static byte[] bytesFromImage(BufferedImage img) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(img, "png", os);
        return os.toByteArray();
    }

    private void addText(Graphics2D graphics, TopAndBottomTextPosition textPosition, String text, int width, int height) {
        int leftPadding = leftPadding(text, width);
        int topPadding = topPadding(textPosition, height);

        graphics.translate(leftPadding, topPadding);
        drawString(graphics, text);
        graphics.translate(-leftPadding, -topPadding);
    }

    private int leftPadding(String text, int width) {
        int halfOfTextWidth = text.length() * (int) (font.getSize() * WIDTH_FONT_FACTOR);
        return width / 2 - halfOfTextWidth;
    }

    private int topPadding(TopAndBottomTextPosition textPosition, int height) {
        return switch (textPosition) {
            case TOP -> (int) (font.getSize() * HEIGHT_FONT_FACTOR);
            case BOTTOM -> height - (int) (font.getSize() * HEIGHT_FONT_FACTOR);
        };
    }

    private void drawString(Graphics2D graphics, String text) {
        setBorderThickness(graphics);
        GlyphVector gv = font.createGlyphVector(graphics.getFontRenderContext(), text);
        for (int i = 0; i < text.length(); i++) {
            Shape jshape = gv.getGlyphOutline(i); // Shape of letter
            drawCharacter(graphics, jshape);
        }
    }

    private void setBorderThickness(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(((float) font.getSize()) / BORDER_THICKNESS_FACTOR));
    }

    private void drawCharacter(Graphics2D graphics, Shape character) {
        graphics.setPaint(FILL_COLOR); // Fill with solid, opaque blue
        graphics.fill(character); // Fill the shape
        graphics.setPaint(BORDER_COLOR); // Switch to solid black
        graphics.draw(character);
    }
}