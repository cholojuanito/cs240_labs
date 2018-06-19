package com.rbdavis.java.IO;

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
}
