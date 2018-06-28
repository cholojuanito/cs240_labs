package com.rbdavis.java.spell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class SpellCorrector implements ISpellCorrector
{
    private Dictionary dictionary;
    private SortedSet<String> wordEditsDistance1, wordEditsDistance2;

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
        System.out.println("Opening: " + fileName + "...");
        Scanner s = new Scanner(dictionaryFile);
        String word;
        System.out.println("Opened!");
        System.out.println("Adding words to dictionary...");
        while (s.hasNext())
        {
            word = s.next().toLowerCase();
            this.dictionary.add(word);
        }
        System.out.println("Finished adding words!");
        System.out.println(this.dictionary.toString());
    }

    /**
     * Suggest a word from the dictionary that most closely matches
     * {@code inputWord}
     * @param inputWord The word to search for
     * @return The suggestion or null if there is no similar word in the dictionary
     */
    public String suggestSimilarWord(String inputWord)
    {
        String lowerCaseWord = inputWord.toLowerCase();
        System.out.println("Attempting to find: '" + lowerCaseWord + "'...");
        Dictionary.WordNode foundWord = this.dictionary.find(lowerCaseWord);
        if(foundWord != null)
        {
            System.out.println("Found!");
            return foundWord.getSubStr();
        }
        // Try by editing up to one index.
        foundWord = this.tryEditDistance1(lowerCaseWord);
        if( foundWord != null)
        {
            return foundWord.getSubStr();
        }
        // Try by editing up to two indices
        foundWord = this.tryEditDistance2(lowerCaseWord);
        if( foundWord != null)
        {
            return foundWord.getSubStr();
        }
            return null;
    }

    private Dictionary.WordNode tryEditDistance1(String lowerCaseInputWord)
    {
        Dictionary.WordNode foundWord;
        this.gatherWordEditsDistance1(lowerCaseInputWord);
        for(String word : this.wordEditsDistance1)
        {
            foundWord = this.dictionary.find(word);
            if(foundWord != null)
            {
                System.out.println("Found!");
                return foundWord;
            }
        }
        return null;
    }

    private Dictionary.WordNode tryEditDistance2(String lowerCaseInputWord)
    {
        Dictionary.WordNode foundWord;
        this.gatherWordEditsDistance2(lowerCaseInputWord);
        for(String word : this.wordEditsDistance2)
        {
            foundWord = this.dictionary.find(word);
            if(foundWord != null)
            {
                System.out.println("Found!");
                return foundWord;
            }
        }
        return null;
    }

    private void gatherWordEditsDistance1(String word)
    {
        System.out.println("Editing word up to 1 index...");
        this.wordEditsDistance1 = new TreeSet<>();
        this.insertion(word, 1);
        this.deletion(word, 1);
        this.alteration(word, 1);
        this.transposition(word, 1);
        System.out.println("Done editing!");
    }

    private void gatherWordEditsDistance2(String word)
    {
        System.out.println("Editing word up to 2 indices...");
        this.wordEditsDistance2 = new TreeSet<>();
        this.insertion(word, 2);
        this.deletion(word, 2);
        this.alteration(word, 2);
        this.transposition(word, 2);
        System.out.println("Done editing!");

    }

    private void insertion(String word, int distance)
    {
        if (distance == 1)
        {

        }
        else
        {

        }

    }

    private void deletion(String word, int distance)
    {
        if (distance == 1)
        {

        }
        else
        {

        }
    }

    private void transposition(String word, int distance)
    {
        if (distance == 1)
        {

        }
        else
        {

        }
    }

    private void alteration(String word, int distance)
    {
        if (distance == 1)
        {

        }
        else
        {

        }
    }
}
