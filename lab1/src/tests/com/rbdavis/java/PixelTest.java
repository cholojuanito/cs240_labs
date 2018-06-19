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
        assertEquals(expectedPixel.toString(), pixelUnderTest.toString());
    }

    @Test
    public void testInvert()
    {
        // Setup
        final int red = 0;
        final int green = 0;
        final int blue = 0;
        final int redInvert = 255;
        final int greenInvert = 255;
        final int blueInvert = 255;
        Pixel expectedInvertedPixel = new Pixel(redInvert, greenInvert, blueInvert);

        // Initialize the Pixel
        pixelUnderTest.setRGB(red, green, blue);

        // Run the test
        pixelUnderTest.invert();

        // Verify the results
        assertEquals(expectedInvertedPixel.toString(), pixelUnderTest.toString());
    }

    @Test
    public void testEquals()
    {
        // Setup
        final Pixel other = new Pixel(255, 255, 255);
        final boolean expectedResult = true;
        pixelUnderTest.setRGB(255, 255, 255);

        // Run the test
        final boolean result = pixelUnderTest.equals(other);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
