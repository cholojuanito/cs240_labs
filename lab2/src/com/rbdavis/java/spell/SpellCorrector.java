package com.rbdavis.java.spell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector
{
    private Words dictionary;

    public SpellCorrector()
    {
        this.dictionary = new Words();
    }

    /**
     * Tells this {@code SpellCorrector} to use the given file as its dictionary
     * for generating suggestions.
     * @param fileName File containing the dictionary to be used
     * @throws IOException If the file cannot be read
     */
    public void useDictionary(String fileName) throws IOException
    {
        File dictionaryFile = new File(fileName);
        try(Scanner s = new Scanner(dictionaryFile))
        {
            //TODO: Maybe use a delimiter?
            String word;
            while (s.hasNext())
            {
                word = s.next().toLowerCase();
                this.dictionary.add(word);
            }
        }
        catch(Exception e)
        {
            System.out.println("Was not able to read " + fileName + ".\n"
                + "Exception: " + e.toString()
            );
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
