package hangman;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EvilHangmanGame implements IEvilHangmanGame
{
    private String wordPattern;
    private String blankPattern;
    private int wordToGuessLength;
    private char currGuess;
    private Set<String> possibleWords = new HashSet<>();
    private SortedSet<String> usedLetters = new TreeSet<>();
    private Map<String, Set<String>> patternMap = new TreeMap<>();

    public final static char HYPHEN = '-';

    @Override
    public void startGame(File dictionary, int wordLength)
    {
        this.setWordToGuessLength(wordLength);
        this.clearOldGame();
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
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException
    {
        String guessStr = Character.toString(guess);
        this.setCurrGuess(guess);
        if (usedLetters.contains(guessStr))
        {
            throw new GuessAlreadyMadeException();
        }
        partitionWords();
        selectLargestSubset();
        usedLetters.add(guessStr);
        return possibleWords;
    }

    private void partitionWords()
    {
        this.patternMap.clear();
        for (String word : possibleWords)
        {
            String pattern = createPattern(word);
            Set<String> wordsThatMatchPattern = patternMap.get(pattern);
            if (wordsThatMatchPattern != null)
            {
                wordsThatMatchPattern.add(word);
            }
            else
            {
                Set<String> newSubset = new HashSet<>();
                newSubset.add(word);
                patternMap.put(pattern, newSubset);
            }
        }
    }

    private String createPattern(String word)
    {
        StringBuilder pattern = new StringBuilder(word);
        for (int i = 0; i < wordToGuessLength; i++)
        {
            if ( word.charAt(i) != this.currGuess)
            {
                pattern.setCharAt(i, HYPHEN);
            }
        }
        return pattern.toString();
    }

    private void selectLargestSubset()
    {
        String patternWithLargestSubset = this.blankPattern;
        int runningMax = 0;
        Set<Map.Entry<String, Set<String>>> entries = patternMap.entrySet();
       for (Map.Entry<String, Set<String>> entry : entries)
       {
           int subSetSize = entry.getValue().size();
           if (subSetSize > runningMax)
           {
               runningMax = subSetSize;
               patternWithLargestSubset = entry.getKey();
           }
           else if (subSetSize == runningMax)
           {
               String contendingPattern = entry.getKey();
               patternWithLargestSubset = settleDisputeOverNewPattern(patternWithLargestSubset, contendingPattern);
           }
       }
       updateWordPattern(patternWithLargestSubset);
       possibleWords = patternMap.get(patternWithLargestSubset);
    }

    private String settleDisputeOverNewPattern(String reigningChamp, String contender)
    {
        if (reigningChamp.equals(blankPattern) && contender.equals(blankPattern))
        {
            return reigningChamp;
        }
        String newChamp = reigningChamp;
        int occurrencesInContender = numOccurrencesInPattern(contender, this.currGuess);
        int occurrencesInCurrent = numOccurrencesInPattern(reigningChamp, this.currGuess);
        if (occurrencesInContender < occurrencesInCurrent)
        {
            newChamp = contender;
        }
        else if (occurrencesInContender == occurrencesInCurrent)
        {
            int lastIndex = wordToGuessLength - 1;
            newChamp = compareRightMostLetters(reigningChamp, contender, lastIndex);
        }
        return newChamp;
    }

    private String compareRightMostLetters(String reigningChamp, String contender, int lastIndexToCheck)
    {
        String newChamp = reigningChamp;
        if (reigningChamp.charAt(lastIndexToCheck) != currGuess && contender.charAt(lastIndexToCheck) == currGuess)
        {
            newChamp = contender;
        }
        else if (reigningChamp.charAt(lastIndexToCheck) == currGuess && contender.charAt(lastIndexToCheck) != currGuess)
        {
            newChamp = reigningChamp;
        }
        else
        {
            if (lastIndexToCheck > 0)
            {
                compareRightMostLetters(reigningChamp, contender, lastIndexToCheck - 1);
            }
            else
            {
                if (reigningChamp.charAt(lastIndexToCheck) > contender.charAt(lastIndexToCheck))
                {
                    return reigningChamp;
                }
                else
                {
                    return contender;
                }
            }

        }
        return newChamp;
    }

    private void updateWordPattern(String patternToAdd)
    {
        StringBuilder newPattern = new StringBuilder(this.wordPattern);
        for (int i = 0; i < wordToGuessLength; i++)
        {
            if (patternToAdd.charAt(i) != HYPHEN)
            {
                newPattern.setCharAt(i, patternToAdd.charAt(i));
            }
        }
        this.wordPattern = newPattern.toString();
    }

    public int numOccurrencesInPattern(String pattern, char guess)
    {
        int occurrences = 0;
        for (int i = 0; i < wordToGuessLength; i++)
        {
            if (pattern.charAt(i) == guess)
            {
                occurrences++;
            }
        }
        return occurrences;
    }

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

    public SortedSet<String> getUsedLetters()
    {
        return usedLetters;
    }

    public Set<String> getPossibleWords()
    {
        return possibleWords;
    }

    public String getPattern()
    {
        return wordPattern;
    }

    public void setCurrGuess(char currGuess)
    {
        this.currGuess = currGuess;
    }

    private void clearOldGame()
    {
        this.patternMap.clear();
        this.usedLetters.clear();
        this.possibleWords.clear();
        this.wordPattern = this.createBlankPattern();
        this.blankPattern = this.createBlankPattern();
    }
}
