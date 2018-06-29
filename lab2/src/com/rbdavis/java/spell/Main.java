package com.rbdavis.java.spell;

import java.io.IOException;

class Main
{
    public static void main(String[] args)
    {
        if(args.length < 2)
        {
            System.out.println("Insufficient amount of arguments. Please check your arguments.");
            return;
        }
        String dictionaryFileName = args[0];
        String inputWord = args[1];
        String suggestion = null;
        ISpellCorrector corrector = new SpellCorrector();

        try
        {
            corrector.useDictionary(dictionaryFileName);
            suggestion = corrector.suggestSimilarWord(inputWord);
        }
        catch(IOException e)
        {
            System.out.println("Was not able to read " + dictionaryFileName + ".\n"
                    + "IOException: " + e.getMessage()
            );
        }
        catch(Exception e)
        {
            System.out.println("Something caused the program to end early:\n Exception:");
            System.out.println(e.getMessage());
        }
        finally {
            if (suggestion == null)
            {
                suggestion = "No similar word found";
            }

            System.out.println("Suggestion is: " + suggestion);
        }
    }
}