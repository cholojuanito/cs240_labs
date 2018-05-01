package com.rbdavis.java;

import java.util.ArrayList;

public class Image {
    private int height, width;
    private Pixel[][] pixels;

    public Image(int h, int w) {
        this.height = h;
        this.width = w;
        this.pixels = new Pixel[h][w];
    }

    public void addPixel(int x, int y, int r, int g, int b) {
        this.pixels[x][y] = new Pixel(r, g, b);
    }

    public void invert() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.pixels[x][y].invert();
            }
        }
    }

    public void grayscale() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.pixels[x][y].grayscale();
            }
        }
    }
}
