package com.rbdavis.java.IO;

import com.rbdavis.java.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileWriterHelperTest {

    @Test
    public void testWrite() {
        // Setup
        final String outputFileName = "outputFileName";
        final String output = "output";

        // Run the test
        FileWriterHelper.write(outputFileName, output);

        // Verify the results
    }

    @Test
    public void testImageToString() {
        // Setup
        final Image img = null;
        final String expectedResult = "result";

        // Run the test
        final String result = FileWriterHelper.imageToString(img);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
