package com.rbdavis.java.spell;

import java.io.IOException;

public class SpellCorrector implements ISpellCorrector
{

    /**
     * Tells this {@code SpellCorrector} to use the given file as its dictionary
     * for generating suggestions.
     * @param fileName File containing the words to be used
     * @throws IOException If the file cannot be read
     */
    public void useDictionary(String fileName) throws IOException
    {
        try
        {

        }
        catch(Exception e) //TODO: Change to IOException
        {

        }
    }

    /**
     * Suggest a word from the dictionary that most closely matches
     * {@code inputWord}
     * @param inputWord
     * @return The suggestion or null if there is no similar word in the dictionary
     */
    public String suggestSimilarWord(String inputWord)
    {
        return inputWord;
    }

    public void insertion()
    {

    }

    public void deletion()
    {

    }

    public void transposition()
    {

    }

    public void alteration()
    {

    }
}
