package com.rbdavis.java.IO;

import com.rbdavis.java.Image;
import com.rbdavis.java.Pixel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderHelper
{
    private static String relativePathToPictures = "src/com/rbdavis/java/resources/pictures/";

    public static Image convertFileToImage(String inputFileName)
    {
        String filePath = relativePathToPictures + inputFileName;
        File inputFile = new File(filePath);
        return FileReaderHelper.convertFileToImage(inputFile);
    }

    public static Image convertFileToImage(File inputFile)
    {
        Image img = new Image();

        System.out.println("Reading: " + inputFile.getName() + "...");
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(inputFile))))
        {
            s.useDelimiter("(\\s+)(#[^\\n]*\\n)?(\\s*)|(#[^\\n]*\\n)(\\s*)");
            s.next();
            img.setWidth(Integer.parseInt(s.next()));
            img.setHeight(Integer.parseInt(s.next()));
            int imgHeight = img.getHeight();
            int imgWidth = img.getWidth();
            Pixel[][] pixels = new Pixel[imgHeight][imgWidth];
            img.setPixels(pixels);
            s.next();

            int rowCount = 0; // Keeps track of the height of the Image
            int columnCount = 0; // Keeps track of the width of the Image
            while (s.hasNext())
            {
                int r = Integer.parseInt(s.next());
                int g = Integer.parseInt(s.next());
                int b = Integer.parseInt(s.next());
                img.addPixel(rowCount, columnCount, r, g, b);

                columnCount++;
                if (columnCount == imgWidth)
                {
                    columnCount = 0;
                    rowCount++;
                }
            }
        }
        catch(IOException ex)
        {
            System.out.println("Unable to convert the file '" + inputFile.toString() + "' to an img.\n"
                    + "Exception: " + ex.toString());
        }

        System.out.println("Done reading!");
        return img;
    }

    public static String outputFileContents(String inputFileName)
    {
        String filePath = relativePathToPictures + inputFileName;
        File inputFile = new File(filePath);
        StringBuilder sb = new StringBuilder();
        sb.append("File Contents are:\n");

        try (Scanner s = new Scanner(new BufferedReader(new FileReader(inputFile))))
        {
            s.useDelimiter("((#[^\\n]*\\n)|(\\s+))+");
            while (s.hasNextLine())
            {
                sb.append(s.nextLine() + "\n");
            }
        }
        catch(IOException ex)
        {
            System.out.println("Unable to convert the file '" + inputFile.toString() + "' to an img.\n"
                    + "Exception: " + ex.toString());
        }

        return sb.toString();
    }

}
