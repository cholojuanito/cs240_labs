package com.rbdavis.java.IO;

import com.rbdavis.java.Image;
import com.rbdavis.java.Pixel;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReaderHelperTest
{
    @Test
    public void testConvertFileToImage()
    {
        // Setup
        final String inputFileName = "audio.ppm";
        final int expectedHeight = 10;
        final int expectedWidth = 10;
        final Pixel[][] expectedPixels = this.createExpectedAudioPixelArray();
        Image expectedResult = new Image(expectedHeight, expectedWidth);
        expectedResult.setPixels(expectedPixels);

        // Run the test
        Image result = FileReaderHelper.convertFileToImage(inputFileName);

        // Verify the results strings since the objects don't want to cooperate
        assertEquals(expectedResult.toString(), result.toString());
    }

    private Pixel[][] createExpectedAudioPixelArray()
    {
        Pixel grey = new Pixel(192, 192, 192);
        Pixel blue = new Pixel(0, 0, 153);
        Pixel[][] pixels = {
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
        return pixels;
    }

    @Test
    public void testOutputFileContents()
    {
        // Setup
        final String inputFileName = "audio.ppm";
        final String expectedResult = this.createExpectedAudioString();

        // Run the test
        final String result = FileReaderHelper.outputFileContents(inputFileName);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    private String createExpectedAudioString()
    {
        return "File Contents are:\n" +
                "P3\n" +
                "# CREATOR: GIMP PNM Filter Version 1.1\n" +
                "10 10\n" +
                "255\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "0\n" +
                "0\n" +
                "153\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n" +
                "192\n";
    }
}
