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
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                this.pixels[x][y].invert();
            }
        }
    }

    public void grayscale()
    {
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                this.pixels[x][y].grayscale();
            }
        }
    }

    public void emboss()
    {

    }

    public void motionBlur()
    {

    }

    @Override
    public String toString() {
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
