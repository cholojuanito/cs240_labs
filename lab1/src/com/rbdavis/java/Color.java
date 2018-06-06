package com.rbdavis.java;


/**
 * Building block of the {@code Pixel} class. Starts with a specified value
 * which can then be inverted.
 *
 * @author Tanner Davis
 * @since 2018-05-01
 * @see Pixel
 */

public class Color
{
    public final int INVERT_VAL = 255;
    private int val;

    public Color(int val)
    {
        this.val = val;
    }

    public int getVal()
    {
        return this.val;
    }

    public void setVal(int val)
    {
        this.val = val;
    }

    /**
     * Inverts the current value to a value on the opposite side of the color wheel.
     */
    public void invert()
    {
        this.val = Math.abs(INVERT_VAL - this.val);
    }
}
