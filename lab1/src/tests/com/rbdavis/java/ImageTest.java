package com.rbdavis.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageTest
{

    private Pixel grey = new Pixel(192, 192, 192);
    private Pixel blue = new Pixel(0, 0, 153);
    private Pixel[][] originalPixels = {
            {grey, grey, grey, grey, grey, blue, grey, grey, grey, grey},
            {grey, grey, grey, grey, blue, blue, grey, grey, grey, grey},
            {grey, grey, grey, blue, blue, blue, grey, grey, blue, grey},
            {blue, blue, blue, blue, blue, blue, grey, grey, grey, blue},
            {blue, blue, blue, blue, blue, blue, grey, grey, grey, blue},
            {blue, blue, blue, blue, blue, blue, grey, grey, grey, blue},
            {blue, blue, blue, blue, blue, blue, grey, grey, grey, blue},
            {grey, grey, grey, blue, blue, blue, grey, grey, blue, grey},
            {grey, grey, grey, grey, blue, blue, grey, grey, grey, grey},
            {grey, grey, grey, grey, grey, blue, grey, grey, grey, grey}
    };
    private int h;
    private int w;

    private Image imageUnderTest;

    @BeforeEach
    public void setUp()
    {
        h = 10;
        w = 10;
        imageUnderTest = new Image(h, w);
        imageUnderTest.setPixels(originalPixels);
    }

    @Test
    public void testGetPixels()
    {
        // Setup
        final Pixel[][] expectedResult = originalPixels;

        // Run the test
        final Pixel[][] result = imageUnderTest.getPixels();

        // Verify the results
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testAddPixel()
    {
        // Setup
        final int i = 5;
        final int j = 4;
        final int r = 255;
        final int g = 128;
        final int b = 0;
        Pixel expectedPixel = new Pixel(r, g, b);

        // Run the test
        imageUnderTest.addPixel(i, j, r, g, b);
        Pixel[][] testedImgPixels = this.imageUnderTest.getPixels();

        // Verify the results
        assertEquals(expectedPixel.toString(), testedImgPixels[i][j].toString());
    }

    @Test
    public void testInvert()
    {
        // Setup
        Pixel invertedGrey = new Pixel(63, 63, 63);
        Pixel invertedBlue = new Pixel(255, 255, 102);
        Pixel[][] expectedInvertedPixels = {
            {invertedGrey, invertedGrey, invertedGrey, invertedGrey, invertedGrey, invertedBlue, invertedGrey, invertedGrey, invertedGrey, invertedGrey},
            {invertedGrey, invertedGrey, invertedGrey, invertedGrey, invertedBlue, invertedBlue, invertedGrey, invertedGrey, invertedGrey, invertedGrey},
            {invertedGrey, invertedGrey, invertedGrey, invertedBlue, invertedBlue, invertedBlue, invertedGrey, invertedGrey, invertedBlue, invertedGrey},
            {invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedGrey, invertedGrey, invertedGrey, invertedBlue},
            {invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedGrey, invertedGrey, invertedGrey, invertedBlue},
            {invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedGrey, invertedGrey, invertedGrey, invertedBlue},
            {invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedBlue, invertedGrey, invertedGrey, invertedGrey, invertedBlue},
            {invertedGrey, invertedGrey, invertedGrey, invertedBlue, invertedBlue, invertedBlue, invertedGrey, invertedGrey, invertedBlue, invertedGrey},
            {invertedGrey, invertedGrey, invertedGrey, invertedGrey, invertedBlue, invertedBlue, invertedGrey, invertedGrey, invertedGrey, invertedGrey},
            {invertedGrey, invertedGrey, invertedGrey, invertedGrey, invertedGrey, invertedBlue, invertedGrey, invertedGrey, invertedGrey, invertedGrey}
        };
        Image expectedInvertedImage = new Image(h, w);
        expectedInvertedImage.setPixels(expectedInvertedPixels);
        imageUnderTest.setPixels(originalPixels);

        // Run the test
        imageUnderTest.invert();

        // Verify the results
        assertEquals(expectedInvertedImage.toString(), imageUnderTest.toString());
    }

    @Test
    public void testGrayscale() {
        // Setup

        // Run the test
        imageUnderTest.grayscale();

        // Verify the results
    }

    @Test
    public void testEmboss() {
        // Setup

        // Run the test
        imageUnderTest.emboss();

        // Verify the results
    }

    @Test
    public void testMotionBlur() {
        // Setup
        final int blurLength = 2;

        // Run the test
        imageUnderTest.motionBlur(blurLength);

        String expected;

        // Verify the results
    }
}
