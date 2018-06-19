package com.rbdavis.java;

import com.rbdavis.java.IO.*;

/**
 * An {@code ImageEditor} can modify an {@code Image}. Currently, (v 1.0)
 * there are 4 ways you can modify an {@code Image}.
 * <ol>
 *     They are:
 *     <li>Invert, see <a href="https://en.wikipedia.org/wiki/Negative_(photography)">Image Negative</a></li>
 *     <li>Grayscale, see <a href="https://en.wikipedia.org/wiki/Grayscale">Image Grayscale</a></li>
 *     <li>Emboss, see <a href="https://en.wikipedia.org/wiki/Image_embossing">Image Emboss</a></li>
 *     <li>Motion Blur, see <a href="https://en.wikipedia.org/wiki/Motion_blur">Image Motion Blur</a></li>
 * </ol>
 *
 * An {@code ImageEditor} works in conjunction with a {@code FileWriterHelper} and
 * a {@code FileReaderHelper} to read an {@code Image} in the PPM format, modify the {@code Image}
 * using the desired modification function and then write the new {@code Image} in the PPM format.
 *
 * Command Line:
 * Command line arguments follow this syntax:
 * {@code java ImageEditor inputFileName outputFileName [options]}
 * Options:
 * <ul>
 *  <li>grayscale</li>
 *  <li>invert</li>
 *  <li>emboss</li>
 *  <li>motionblur {@code (int)}blurLength</li>
 * </ul>
 *
 * @author Tanner Davis
 * @since v 1.0
 * @see Image
 * @see FileWriterHelper
 * @see FileReaderHelper
 */

public class ImageEditor
{
    public Image imgToEdit;

    public static void main(String[] args)
    {
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
        System.out.println("Opening: " + fileName + "...");
        this.imgToEdit = FileReaderHelper.convertFileToImage(fileName);
        System.out.println("Opened!");
    }


    public void writeToFile(String fileName)
    {
        System.out.println("Writing to: " + fileName + "...");
        String output = this.imgToEdit.toString();
        FileWriterHelper.write(fileName, output);
        System.out.println("Finished writing!");
    }
}
