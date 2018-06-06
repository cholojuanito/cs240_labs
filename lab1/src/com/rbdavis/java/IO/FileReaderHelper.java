package com.rbdavis.java.IO;

import com.rbdavis.java.Image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderHelper
{

    static public Image convertFileToImage(String inputFileName)
    {
        Image img = new Image();

        try (Scanner s = new Scanner(new BufferedReader(new FileReader(inputFileName))))
        {


        }
        catch(IOException ex)
        {
            System.out.println("Unable to convert the file '" + inputFileName + "' to an image."
                + "Exception: " + ex.toString());
        }

        return img;
    }

    static public Image convertFileToImage(File inputFile)
    {
        Image img = new Image();

        try (Scanner s = new Scanner(new BufferedReader(new FileReader(inputFile))))
        {


        }
        catch(IOException ex)
        {
            System.out.println("Unable to convert the file '" + inputFile.toString() + "' to an image."
                    + "Exception: " + ex.toString());
        }


        return img;
    }

}
