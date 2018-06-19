package com.rbdavis.java;

/**
 * Building block of the {@code Image} class.
 */

public class Pixel
{
    private Color r, g, b;
    private final int NUM_COLORS = 3;

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

    public void setRGB(int newVal)
    {
        this.r.setVal(newVal);
        this.g.setVal(newVal);
        this.b.setVal(newVal);
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
        this.setRGB(avg);
    }

    public void emboss(int newVal)
    {
        this.setRGB(newVal);
    }

    public void motionblur(int newR, int newG, int newB)
    {
        this.setRGB(newR, newG, newB);
    }

    public boolean equals(Pixel other)
    {
        return (this.r.getVal() == other.r.getVal()) && (this.g.getVal() == other.g.getVal()) && (this.b.getVal() == other.b.getVal());
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%03d", this.r.getVal()));
        sb.append("\n");
        sb.append(String.format("%03d", this.g.getVal()));
        sb.append("\n");
        sb.append(String.format("%03d", this.b.getVal()));
        sb.append("\n");

        return sb.toString();
    }
}
