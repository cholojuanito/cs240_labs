package tests;

import com.rbdavis.java.Color;

public class testColor {
    public Color color;

    public void create()
    {
        int colorVal = 80;
        this.color = new Color();
        this.color.setVal(colorVal);
    }

    public void createWithAltConstructor()
    {
        int colorVal = 80;
        this.color = new Color(colorVal);
    }

    public void update()
    {
        int newColorVal = 105;
        this.color.setVal(105);
    }

    public void testInvert()
    {
        this.color.invert();
    }
}
