package com.rbdavis.java.spell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class SpellCorrector implements ISpellCorrector
{
    public Dictionary dictionary;

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
        System.out.println("Adding words to dictionary...");
        while (s.hasNext())
        {
            word = s.next().toLowerCase().trim();
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
        if(inputWord == null || inputWord.trim().equals(""))
        {
            return null;
        }
        String foundWord;
        String lowerCaseWord = inputWord.toLowerCase();
        System.out.println("Attempting to find: '" + lowerCaseWord + "'...");
        ITrie.INode foundWordNode = this.dictionary.find(lowerCaseWord);
        if(foundWordNode != null)
        {
            return inputWord;
        }

        foundWord = this.tryEdits(lowerCaseWord);
        if( foundWord != null)
        {
            return foundWord;
        }
        return null;
    }

    private String tryEdits(String lowerCaseInputWord)
    {
        String suggestedWord;
        SortedSet<String> wordEditsDistance1 = new TreeSet<>();
        System.out.println("Editing word up to 1 index...");
        this.gatherWordEditsDistance1(lowerCaseInputWord, wordEditsDistance1);
        suggestedWord = this.suggestAnEditedWord(wordEditsDistance1);
        if(suggestedWord == null)
        {
            // Try editing up to two indices
            suggestedWord = this.tryEditDistance2(wordEditsDistance1);
        }
        return suggestedWord;
    }

    private String tryEditDistance2(SortedSet<String> editedWordsDistance1)
    {
        String suggestedWord;
        SortedSet<String> wordEditsDistance2 = new TreeSet<>();
        System.out.println("Editing word up to 2 indices...");
        this.gatherWordEditsDistance2(editedWordsDistance1, wordEditsDistance2);
        suggestedWord = this.suggestAnEditedWord(wordEditsDistance2);
        return suggestedWord;
    }

    private String suggestAnEditedWord(SortedSet<String> editedWords)
    {
        String suggestion = null;
        int runningMaxFrequency = 0;
        System.out.println("Comparing edited words...");
        for (String word : editedWords)
        {
            ITrie.INode foundWordNode = this.dictionary.find(word);
            if(foundWordNode != null)
            {
                int nodeFrequency = foundWordNode.getValue();
                if(nodeFrequency > runningMaxFrequency)
                {
                    runningMaxFrequency = nodeFrequency;
                    suggestion = word;
                }
            }
        }
        return suggestion;
    }

    private void gatherWordEditsDistance1(String word, SortedSet<String> editedWords)
    {
        this.deletion(word, editedWords);
        this.transposition(word, editedWords);
        this.alteration(word, editedWords);
        this.insertion(word, editedWords);
    }

    private void gatherWordEditsDistance2(SortedSet<String> editedWordsDistance1, SortedSet<String> editedWordsDistance2)
    {
        for (String editedWord : editedWordsDistance1)
        {
            this.gatherWordEditsDistance1(editedWord, editedWordsDistance2);
        }
    }

    private void deletion(String word, SortedSet<String> editedWords)
    {
        int wordLength = word.length();
        for (int i = 0; i < wordLength; i++)
        {
            StringBuilder newWord = new StringBuilder(word);
            newWord.deleteCharAt(i);
            editedWords.add(newWord.toString());
        }
    }

    private void transposition(String word, SortedSet<String> editedWords)
    {
        int wordLengthMinusOne = word.length() - 1;
        for (int i = 0; i < wordLengthMinusOne; i++)
        {
            StringBuilder newWord = new StringBuilder(word);
            char first = word.charAt(i);
            char second = word.charAt(i + 1);
            newWord.deleteCharAt(i + 1);
            newWord.deleteCharAt(i);
            newWord.insert(i, second);
            newWord.insert(i + 1, first);
            editedWords.add(newWord.toString());
        }
    }

    private void alteration(String word, SortedSet<String> editedWords)
    {
        int wordLength = word.length();
        char[] AtoZ = this.dictionary.getAtoZ();
        for (int i = 0; i < wordLength; i++)
        {
            for(char c : AtoZ)
            {
                StringBuilder newWord = new StringBuilder(word);
                newWord.deleteCharAt(i);
                newWord.insert(i, c);
                editedWords.add(newWord.toString());
            }
        }
    }

    private void insertion(String word, SortedSet<String> editedWords)
    {
        int wordLengthPlusOne = word.length() + 1;
        char[] AtoZ = this.dictionary.getAtoZ();
        for (int i = 0; i < wordLengthPlusOne; i++)
        {
            for(char c : AtoZ)
            {
                StringBuilder newWord = new StringBuilder(word);
                newWord.insert(i, c);
                editedWords.add(newWord.toString());
            }
        }
    }
}
