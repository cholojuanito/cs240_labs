package hangman;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EvilHangmanGame implements IEvilHangmanGame
{
    private String wordPattern;
    private String blankPattern;
    private int wordToGuessLength;
    private SortedSet<String> possibleWords = new TreeSet<>();
    private SortedSet<String> usedLetters = new TreeSet<>();
    private Map<String, SortedSet<String>> patternMap = new TreeMap<>();

    private final char HYPHEN = '-';

    @Override
    public void startGame(File dictionary, int wordLength)
    {
        this.patternMap.clear();
        this.setWordToGuessLength(wordLength);
        this.wordPattern = this.createBlankPattern();
        this.blankPattern = this.createBlankPattern();
        try
        {
            this.addWords(dictionary);
        }
        catch (IOException e)
        {
            System.out.println("Couldn't open '" + dictionary.getName() + "'.\n Please try again.");
        }

    }

    private void addWords(File dictionary) throws IOException
    {
        System.out.println("Opening: " + dictionary.getName() + "...");
        Scanner s = new Scanner(dictionary);
        String word;
        System.out.println("Adding words from "+ dictionary.getName() + "...");
        while (s.hasNext())
        {
            word = s.next().toLowerCase().trim();
            if (word.length() == wordToGuessLength){
                this.possibleWords.add(word);
            }
        }
    }

    @Override
    public SortedSet<String> makeGuess(char guess) throws GuessAlreadyMadeException
    {
        String guessStr = Character.toString(guess);
        if (usedLetters.contains(guessStr))
        {
            throw new GuessAlreadyMadeException();
        }
        partitionWords(guess);
        System.out.println(toStringWordGroups());
        //selectLargestSubset
        return null;
    }

    //TODO: Find an 'easier' way to do the adding of one word that matches the pattern.
    private void partitionWords(char guess)
    {
        //SortedSet<String> copyPossibleWords = new TreeSet<>(this.possibleWords);
        //copyPossibleWords.remove(word);
        for (String word : possibleWords)
        {
            String pattern = createPattern(word, guess);
            SortedSet<String> wordsThatMatchPattern = new TreeSet<>();
            wordsThatMatchPattern.add(word);
            for (String compareWord : possibleWords)
            {
                String comparePattern = createPattern(compareWord, guess);
                if (comparePattern.equals(pattern))
                {
                    wordsThatMatchPattern.add(compareWord);
                }
            }
            patternMap.put(pattern, wordsThatMatchPattern);
        }
    }

    private String createPattern(String word, char guess)
    {
        StringBuilder pattern = new StringBuilder(word);
        int wordLength = word.length();
        for (int i = 0; i < wordLength; i++)
        {
            if ( word.charAt(i) != guess)
            {
                pattern.setCharAt(i, HYPHEN);
            }
        }
        return pattern.toString();
    }

    /*
     * Makes a string of hyphens ('-') as long as the
     * word-to-guess.
     */
    private String createBlankPattern()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordToGuessLength; i++){
            sb.append(HYPHEN);
        }
        return sb.toString();
    }


    public void setWordToGuessLength(int wordToGuessLength)
    {
        this.wordToGuessLength = wordToGuessLength;
    }

    public Set<String> getPossibleWords()
    {
        return possibleWords;
    }

    public SortedSet<String> getUsedLetters()
    {
        return usedLetters;
    }

    public String getPattern()
    {
        return wordPattern;
    }

    private String toStringPossibleWords()
    {
        StringBuilder output = new StringBuilder();
        for (String word : possibleWords)
        {
            output.append(word).append("\n");
        }
        return output.toString();
    }

    private String toStringWordGroups()
    {
        StringBuilder output = new StringBuilder();
        Set<String> keys = patternMap.keySet();
        for (String key : keys)
        {
            output.append(key).append("\n => [");
            SortedSet<String> values = patternMap.get(key);
            for (String value : values)
            {
                output.append("\n\t").append(value);
            }
            output.append("\n]");
        }

        return output.toString();
    }
}
