package com.rbdavis.java;

/**
 * An {@code Image} is represented by a 2d array of {@code Pixel}s
 * with height, <b>h</b> and width, <b>w</b>.
 * An {@code Image} can be altered by an {@code ImageEditor}.
 * Currently, (v 1.0) an {@code Image} is saved to a {@code File} following
 * the Netbpm PPM format (portable pixel map).  See
 * <a href="https://en.wikipedia.org/wiki/Netpbm_format">Netbpm Format</a>
 * for more information.
 *
 * @author Tanner Davis
 * @since v 1.0
 * @see ImageEditor
 * @see Pixel
 * @see Color
 */

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

    public void motionBlur(int blurLength)
    {
        for (int x = 0; x < height; x++)
        {
            for (int y = 0; y < width; y++)
            {
                int distanceToEdge = this.width - y;
                int numOfPixelsToAverage = blurLength;
                if (distanceToEdge < blurLength)
                {
                    numOfPixelsToAverage = distanceToEdge;
                }
                if (distanceToEdge > 0)
                {
                    int[] avgs = this.gatherRGBAvgs(x, y, numOfPixelsToAverage);
                    this.pixels[x][y].motionblur(avgs[0], avgs[1], avgs[2]);
                }
            }
        }
    }

    private int[] gatherRGBAvgs(int currRow, int startIndex, int n)
    {
        int[] avgs = new int[3];
        int[] sums = this.gatherRGBSums(currRow, startIndex, n);

        avgs[0] = this.average(sums[0], n);
        avgs[1] = this.average(sums[1], n);
        avgs[2] = this.average(sums[2], n);
        return avgs;
    }

    private int average(int sum, int divisor)
    {
        return sum / divisor;
    }

    private int[] gatherRGBSums(int currRow, int startIndex, int n)
    {
        int[] sums = new int[3];
        int endIndex = startIndex + n - 1;
        for (int i = startIndex; i <= endIndex; i++)
        {
            sums[0] += this.pixels[currRow][i].getR().getVal();
            sums[1] += this.pixels[currRow][i].getG().getVal();
            sums[2] += this.pixels[currRow][i].getB().getVal();
        }
        return sums;
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
        int imgWidth = this.getWidth();
        int imgHeight = this.getHeight();
        Pixel[][] pixels = this.getPixels();
        StringBuilder sb = new StringBuilder();

        sb.append("P3\n");
        sb.append(imgWidth);
        sb.append(" ");
        sb.append(imgHeight);
        sb.append("\n255\n");
        for(int i = 0; i < imgHeight; i++)
        {
            for(int j = 0; j < imgWidth; j++)
            {
                sb.append(pixels[i][j].toString());
            }
        }

        return sb.toString();
    }
}
