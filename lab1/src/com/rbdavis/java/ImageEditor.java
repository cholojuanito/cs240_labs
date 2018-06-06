package com.rbdavis.java;

import com.rbdavis.java.IO.*;

import java.io.File;

public class ImageEditor
{

    public Image image;

    public static void main(String[] args)
    {

        System.out.println("Hello world");
    }




    // File IO section of this class

    public void readFromFile(String fileName)
    {
        Image img = FileReaderHelper.convertFileToImage(fileName);
    }

    public void readFromFile(File inputFile)
    {
        FileWriterHelper fileWriter = new FileWriterHelper(inputFile);
    }

    private String convertImageToString()
    {
        return FileWriterHelper.imageToString(this.image);
    }

    public void writeToFile(String fileName)
    {
        FileWriterHelper fileWriter = new FileWriterHelper(fileName);
    }

    public void writeToFile(File outputFile)
    {
        FileWriterHelper fileWriter = new FileWriterHelper(outputFile);
    }
}
