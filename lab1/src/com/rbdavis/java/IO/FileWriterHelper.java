package com.rbdavis.java.IO;

import com.rbdavis.java.Image;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterHelper
{
    private File outputFile;

    public FileWriterHelper(File outputFile)
    {
        this.outputFile = outputFile;
    }

    public FileWriterHelper(String fileName)
    {
        this.outputFile = new File(fileName);
    }

    public void write(String output)
    {

        try ( FileWriter fw = new FileWriter(this.outputFile) )
        {
            fw.write(output);
        }
        catch (IOException ex)
        {
            System.out.println("Unable to write the string '" +
                    output + "' to file '" + this.outputFile + "'"
                    + ":: Exception: " + ex.toString());
        }
    }

    static public String imageToString(Image img)
    {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
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
                output = img.pixels[i][j].toString();
                sb.append(output);
            }
        }

        return sb.toString();
    }
}
