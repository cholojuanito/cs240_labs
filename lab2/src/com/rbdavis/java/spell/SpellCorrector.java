package com.rbdavis.java.spell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector
{
    private Dictionary dictionary;

    public SpellCorrector()
    {
        this.dictionary = new Dictionary();
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
            String word;
            while (s.hasNext())
            {
                word = s.next().toLowerCase();
                this.dictionary.add(word);
            }
        }
    }

    /**
     * Suggest a word from the dictionary that most closely matches
     * {@code inputWord}
     * @param inputWord The word to search for
     * @return The suggestion or null if there is no similar word in the dictionary
     */
    public String suggestSimilarWord(String inputWord)
    {
        Dictionary.WordNode foundWord = this.dictionary.find(inputWord);
        if(foundWord != null)
        {
            return foundWord.getSubStr();
        }
        else
        {
            return null;
        }
    }

    public void editWord()
    {

    }

    private void insertion()
    {

    }

    private void deletion()
    {

    }

    private void transposition()
    {

    }

    private void alteration()
    {

    }
}
