package hangman;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EvilHangmanGame implements IEvilHangmanGame
{
    private String wordGroupPattern;
    private int wordToGuessLength;
    private Set<String> possibleWords = new TreeSet<>();
    private Set<String> usedLetters = new TreeSet<>();
    private Map<String, Set<String>> wordGroupPatternMap = new TreeMap<>();

    @Override
    public void startGame(File dictionary, int wordLength)
    {
        this.wordGroupPatternMap.clear();
        this.wordToGuessLength = wordLength;
        this.wordGroupPattern = this.createFirstPattern();
        try
        {
            this.addWords(dictionary);
        }
        catch (IOException e)
        {
            System.out.println("Couldn't open '" + dictionary.getName() + "'.\n Please try again.");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong!");
        }

    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException
    {
        String guessStr = Character.toString(guess);
        if (usedLetters.contains(guessStr))
        {
            throw new GuessAlreadyMadeException();
        }
        return null;
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

    /*
     * Makes a string of hyphens ('-') as long as the
     * word-to-guess.
     */
    private String createFirstPattern(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < wordToGuessLength; i++){
            sb.append('-');
        }
        return sb.toString();
    }

}
