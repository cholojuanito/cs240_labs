package com.rbdavis.java.IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterHelper
{
    public static void write(String outputFileName, String output)
    {
        try (FileWriter fw = new FileWriter(new File(outputFileName)))
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
