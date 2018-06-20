package com.rbdavis.java.spell;

import java.io.IOException;

class Main

    // ADD src\com\rbdavis\java\spell\ before the text file
{
    public static void main(String[] args)
    {
        String dictionaryFileName = args[0];
        String inputWord = args[1];

        ISpellCorrector corrector = new SpellCorrector();

        try
        {
            corrector.useDictionary(dictionaryFileName);
        }
        catch(IOException e)
        {
            System.out.println("Was not able to read " + dictionaryFileName + ".\n"
                    + "IOException: " + e.toString()
            );
        }

        String suggestion = corrector.suggestSimilarWord(inputWord);
        if (suggestion == null)
        {
            suggestion = "No similar word found";
        }

        System.out.println("Suggestion is: " + suggestion);
    }
}