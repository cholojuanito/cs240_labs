package com.rbdavis.java.IO;

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
}
