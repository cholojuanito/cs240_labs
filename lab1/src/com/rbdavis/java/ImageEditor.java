package com.rbdavis.java;

import com.rbdavis.java.IO.*;


public class ImageEditor
{
    public Image imgToEdit;

    public static void main(String[] args)
    {

        // The args will be, 1:inputFileName, 2:outputFileName, 3: the action to do to that file

        if (args.length > 0)
        {
            ImageEditor ie = new ImageEditor();
            String inputFileName = args[0];
            String outFileName = args[1];
            String action = args[2];
            int blurLength = -1;

            if (args.length == 4)
            {
                blurLength = Integer.parseInt(args[3]);
            }

            ie.readFromFile(inputFileName);
            ie.performActionToImg(action, blurLength);
            ie.writeToFile(outFileName);
        }
        else
        {
            System.out.println("No arguments were given.");
        }
    }


    public void performActionToImg(String action, int blurLength)
    {
        final String emboss = "emboss";
        final String invert = "invert";
        final String motion = "motionblur";
        final String grayscale = "grayscale";

        switch (action)
        {
            case invert:
                System.out.println("Inverting img...");
                this.imgToEdit.invert();
                System.out.println("Finished inverting!");
                break;
            case motion:
                if(blurLength <= 0)
                {
                    System.out.println("Please provide a blur amount that is greater than ZERO.");
                    return;
                }
                System.out.println("Motion blurring img...");
                this.imgToEdit.motionBlur(blurLength);
                System.out.println("Finished motion blurring!");
                break;
            case grayscale:
                System.out.println("Grayscaling img...");
                this.imgToEdit.grayscale();
                System.out.println("Finished grayscaling!");
                break;
            case emboss:
                System.out.println("Embossing img...");
                this.imgToEdit.emboss();
                System.out.println("Finished embossing!");
                break;
            default:
                System.out.println("The action: '" + "' is not allowed. Please try again.");
                break;
        }
    }

    // File IO functions

    public void readFromFile(String fileName)
    {
        this.imgToEdit = FileReaderHelper.convertFileToImage(fileName);
        //System.out.println(FileReaderHelper.outputFileContents(fileName));
        //System.out.println(this.imgToEdit);

    }


    public void writeToFile(String fileName)
    {
        System.out.println("Writing to: " + fileName + "...");
        String output = FileWriterHelper.imageToString(this.imgToEdit);
        FileWriterHelper.write(fileName, output);
        System.out.println("Finished writing!");
    }
}
