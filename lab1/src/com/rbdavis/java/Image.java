package com.rbdavis.java;

import java.util.ArrayList;
import java.util.Arrays;

public class Image
{
    private int height, width;
    private Pixel[][] pixels;

    public Image()
    {
        this.height = 0;
        this.width = 0;
        this.pixels = new Pixel[0][0];
    }

    public Image(int h, int w)
    {
        this.height = h;
        this.width = w;
        this.pixels = new Pixel[h][w];
    }

    public int getHeight()
    {
        return this.height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public Pixel[][] getPixels()
    {
        return this.pixels;
    }

    public void setPixels(Pixel[][] pixels)
    {
        this.pixels = pixels;
    }

    public void addPixel(int i, int j, int r, int g, int b)
    {
        this.pixels[i][j] = new Pixel(r, g, b);
    }

    public void invert()
    {
        for (int x = 0; x < height; x++)
        {
            for (int y = 0; y < width; y++)
            {
                this.pixels[x][y].invert();
            }
        }
    }

    public void grayscale()
    {
        for (int x = 0; x < height; x++)
        {
            for (int y = 0; y < width; y++)
            {
                this.pixels[x][y].grayscale();
            }
        }
    }

    /*
    Assume an image is stored in a structure called image, with “height” rows and
    “width” columns. For every pixel p at row r, column c (p = image[r,c]), set its red,
    green, and blue values to the same value doing the following:

    1) Calculate the differences between red, green, and blue values for the pixel and and the
         pixel to its upper left.
            redDiff = p.redValue - image[r-1,c-1].redValue
            greenDiff = p.greenValue - image[r-1,c-1].greenValue
            blueDiff = p.blueValue - image[r-1, c-1].blueValue

    2) Find the largest difference (positive or negative). We will call this maxDifference. We
        then add 128 to maxDifference. If there are multiple equal differences with differing signs
        (e.g. -3 and 3), favor the red difference first, then green, then blue.
        v = 128 + maxDifference

        If needed, we then scale v to be between 0 and 255 by doing the following:
        If v < 0, then we set v to 0.
        If v > 255, then we set v to 255.
        The pixel’s red, green, and blue values are all set to v.

    Be sure to account for the situation where r-1 or c-1 is less than 0. V should be 128 in
    this case
     */

    public void emboss()
    {
        for (int x = height - 1; x >= 0; x--)
        {
            for (int y = width - 1; y >= 0; y--)
            {
                final int TOP_OR_LEFT_BOUND = 0;
                int maxDiff = 0;
                int newVal;
                if (x == TOP_OR_LEFT_BOUND || y == TOP_OR_LEFT_BOUND)
                {
                    newVal = this.calculateNewVal(maxDiff);
                    this.pixels[x][y].emboss(newVal);
                }
                else
                {
                    Pixel curr = this.pixels[x][y];
                    Pixel upperLeft = this.pixels[x - 1][y - 1];
                    int redDiff = curr.getR().getVal() - upperLeft.getR().getVal();
                    int greenDiff = curr.getG().getVal() - upperLeft.getG().getVal();
                    int blueDiff = curr.getB().getVal() - upperLeft.getB().getVal();

                    maxDiff = this.calculateMaxDiff(redDiff, greenDiff, blueDiff);
                    newVal = this.calculateNewVal(maxDiff);
                    this.pixels[x][y].emboss(newVal);
                }
            }
        }

    }

    public void motionBlur(int blurStrength)
    {

        /*
            A number will be provided in the command line arguments if the command is
            “motionblur.” We will call this number n. n must be greater than 0.

            The value of each color of each pixel is the average of that color value for n pixels (from
            the current pixel to n-1) horizontally.

            Example: if we store the pixels in a 2d array, the motion blur would average each color
            from pixel[ x ][ y ] to pixel[ x ][ y+n-1 ]

            Be sure to account for the situations where one or more of the values used in the
            computing the average do not exist. For example, if an image has width w and we are
            considering the pixel on row r, column c, if c + n >= w, then we only average the pixels
            up to w.
         */

    }

    private int calculateMaxDiff(int redDiff, int greenDiff, int blueDiff)
    {
        int absRed = Math.abs(redDiff);
        int absG = Math.abs(greenDiff);
        int absB = Math.abs(blueDiff);
        int maxAbs = Math.max(Math.max(absRed, absG), absB);

        if (maxAbs == absRed)
        {
            return redDiff;
        }
        else if (maxAbs == absG)
        {
            return greenDiff;
        }
        else
        {
            return blueDiff;
        }

    }

    // Default maxDiff to zero if the pixel to the upper left don't exist
    private int calculateNewVal(int maxDiff)
    {
        final int MIN_VAL = 0;
        final int MAX_VAL = 255;
        final int EMBOSS_CONSTANT = 128;
        int newVal = EMBOSS_CONSTANT + maxDiff;

        if (newVal > MAX_VAL)
        {
            newVal = MAX_VAL;
        }
        else if (newVal < MIN_VAL)
        {
            newVal = MIN_VAL;
        }

        return newVal;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Image:\n");
        sb.append("\tHeight: " + height + "\n");
        sb.append("\tWidth: " + width + "\n");
//        sb.append("\tPixels:\n");
//        for (int i = 0; i < height; i++)
//        {
//            for (int j = 0; j < width; j++)
//            {
//                sb.append("\t" + pixels[i][j].toString());
//            }
//            sb.append("\n");
//        }

        return sb.toString();
    }
}
