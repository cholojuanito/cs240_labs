package com.rbdavis.java.IO;

import com.rbdavis.java.Image;
import com.rbdavis.java.Pixel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterHelper
{
    private static String relativePathToPictures = "src/com/rbdavis/java/resources/pictures/";

    public static void write(String outputFileName, String output)
    {
        String fullFilePath = relativePathToPictures + outputFileName;
        try (FileWriter fw = new FileWriter(new File(fullFilePath)))
        {
            fw.write(output);
        }
        catch (IOException ex)
        {
            System.out.println("Unable to write the string '" +
                    output + "' to file '" + outputFileName + "'"
                    + ":: Exception: " + ex.toString());
        }
    }

    public static String imageToString(Image img)
    {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        Pixel[][] pixels = img.getPixels();
        StringBuilder sb = new StringBuilder();

        sb.append("P3\n");
        sb.append(imgWidth);
        sb.append(" ");
        sb.append(imgHeight);
        sb.append("\n255\n");

        String output;
        for(int i = 0; i < imgHeight; i++)
        {
            for(int j = 0; j < imgWidth; j++)
            {
                output = pixels[i][j].toString();
                sb.append(output);
            }
        }

        return sb.toString();
    }
}
