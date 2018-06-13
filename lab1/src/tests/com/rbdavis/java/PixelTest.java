package com.rbdavis.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixelTest
{

    private int r;
    private int g;
    private int b;

    private Pixel pixelUnderTest;

    @BeforeEach
    public void setUp()
    {
        r = 0;
        g = 0;
        b = 0;
        pixelUnderTest = new Pixel(r, g, b);
    }

    @Test
    public void testSetRGB()
    {
        // Setup
        final int red = 255;
        final int green = 255;
        final int blue = 255;
        Pixel expectedPixel = new Pixel(red, green, blue);

        // Run the test
        pixelUnderTest.setRGB(red, green, blue);

        // Verify the results
        assertEquals(expectedPixel, pixelUnderTest);
    }

    @Test
    public void testInvert()
    {
        // Setup
        final int red = 0;
        final int green = 0;
        final int blue = 0;
        Pixel expectedInvertedPixel = new Pixel(red, green, blue);
        // Run the test
        pixelUnderTest.invert();

        // Verify the results
        assertEquals(expectedInvertedPixel, pixelUnderTest);
    }

    @Test
    public void testGrayscale()
    {
        // Setup
        final int red = 0;
        final int green = 0;
        final int blue = 0;
        Pixel expectedGrayScalePixel = new Pixel(red, green, blue);
        // Run the test
        pixelUnderTest.grayscale();

        // Verify the results
        assertEquals(expectedGrayScalePixel, pixelUnderTest);
    }

//    @Test
//    public void testReset()
//    {
//        // Setup
//        final int newR = 0;
//        final int newG = 0;
//        final int newB = 0;
//
//        // Run the test
//        pixelUnderTest.reset(newR, newG, newB);
//
//        // Verify the results
//    }

    @Test
    public void testEquals()
    {
        // Setup
        final Pixel other = null;
        final boolean expectedResult = false;

        // Run the test
        final boolean result = pixelUnderTest.equals(other);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testToString()
    {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = pixelUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
