package com.rbdavis.java;

/**
 * Building block of the {@code Image} class.
 */

public class Pixel {
    private Color r, g, b;

    final int NUM_COLORS = 3;

    public Pixel()
    {
        this.r = new Color();
        this.g = new Color();
        this.b = new Color();
    }

    public Pixel(int r, int g, int b)
    {
        this.r = new Color(r);
        this.g = new Color(g);
        this.b = new Color(b);
    }

    public Color getR()
    {
        return r;
    }

    public Color getG()
    {
        return g;
    }

    public Color getB()
    {
        return b;
    }

    public void setRGB(int red, int green, int blue)
    {
        this.r.setVal(red);
        this.g.setVal(green);
        this.b.setVal(blue);
    }

    public void invert()
    {
        this.r.invert();
        this.g.invert();
        this.b.invert();
    }

    public void grayscale()
    {
        int avg = ( this.r.getVal() + this.g.getVal() + this.b.getVal() ) / NUM_COLORS;
        this.r.setVal(avg);
        this.g.setVal(avg);
        this.b.setVal(avg);
    }

    public void reset(int newR, int newG, int newB)
    {

    }

    public boolean equals(Pixel other)
    {
        return (this.r == other.r) && (this.g == other.g) && (this.b == other.b);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%03d", this.r.getVal()));
        sb.append(" ");
        sb.append(String.format("%03d", this.g.getVal()));
        sb.append(" ");
        sb.append(String.format("%03d", this.b.getVal()));
        sb.append(" ");

        return sb.toString();
    }
}
