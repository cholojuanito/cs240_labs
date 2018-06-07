package com.rbdavis.java;

import com.rbdavis.java.IO.*;

import java.io.File;

public class ImageEditor
{

    public Image img;

    public static void main(String[] args)
    {

        // The args will be, 1:inputFileName, 2:outputFileName, 3: the action to do to that file

        if (args.length > 0)
        {
            ImageEditor ie = new ImageEditor();
            String inputFileName = args[0];
            //String outFileName = args[1];
            //String action = args[2];

            ie.readFromFile(inputFileName);
        }
        else
        {
            System.out.println("No arguments were given.");
        }
    }




    // File IO section of this class

    public void readFromFile(String fileName)
    {
        this.img = FileReaderHelper.convertFileToImage(fileName);
        System.out.println(this.img);

    }

    public void readFromFile(File inputFile)
    {
        FileWriterHelper fileWriter = new FileWriterHelper(inputFile);
    }

    private String convertImageToString()
    {
        return FileWriterHelper.imageToString(this.img);
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
