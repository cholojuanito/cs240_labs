package com.rbdavis.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorTest
{

    private int val;
    private int expectedInvertedVal;

    private Color colorUnderTest;

    @BeforeEach
    public void setUp()
    {
        this.val = 75;
        this.expectedInvertedVal = 180; // Should be set to 255 - this.val. i.e. if this.val == 55 then this.expectedInvertedVal == 200.
        colorUnderTest = new Color(this.val);
    }

    @Test
    public void testInvert()
    {
        // Setup

        // Run the test
        colorUnderTest.invert();

        int actualVal = colorUnderTest.getVal();
        // Verify the results
        assertEquals(this.expectedInvertedVal, actualVal);
    }
}
