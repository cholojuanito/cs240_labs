package hangman;

import java.security.InvalidParameterException;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;

public class EvilHangmanRunTime
{
    private EvilHangmanGame game;
    private int allowedNumGuesses;
    private int wordSize;

    public EvilHangmanRunTime(EvilHangmanGame game, int allowedNumGuesses, int wordSize)
    {
        this.game = game;
        this.allowedNumGuesses = allowedNumGuesses;
        this.wordSize = wordSize;
    }

    public void runGame()
    {
        Set<String> possibleWords = game.getPossibleWords();
        if (possibleWords.size() == 0)
        {
            System.out.println("There are no words of length " + wordSize + " available to guess. Please try a different word length.");
            return;
        }
        int numGuessesLeft = allowedNumGuesses;
        char guess = 'a';

        System.out.println("Let the guessing begin!");
        while (numGuessesLeft > 0)
        {
            System.out.println("You have " + numGuessesLeft + " guesses left");
            printPrevGuesses(game.getUsedLetters());
            System.out.println("Word: " + game.getPattern());

            try
            {
                guess = getUserGuess();
                possibleWords = game.makeGuess(guess);
                if (wasCorrectGuess(possibleWords, guess))
                {
                    int numNonHyphens = countNumNonHyphens();
                    if (numNonHyphens == game.getPattern().length())
                    {
                        printWin(game.getPattern());
                        break;
                    }
                    printCorrectGuess(guess, game.numOccurrencesInPattern(game.getPattern(), guess));
                }
                else
                {
                    numGuessesLeft--;
                    if (numGuessesLeft == 0)
                    {
                        printLoss(possibleWords.iterator().next());
                        break;
                    }
                    printIncorrectGuess(guess);
                }
            }
            catch (IEvilHangmanGame.GuessAlreadyMadeException e)
            {
                System.out.println("You have already guessed '" + guess + "'. Please try something else.");
            }
            catch (InvalidParameterException e)
            {
                System.out.println(e.getMessage());
            }
            System.out.println("\n");
        }
    }

    private boolean wasCorrectGuess(Set<String> possibleWords, char guess)
    {
        for (String word : possibleWords)
        {
            int wordLength = word.length();
            for (int i = 0; i < wordLength; i++)
            {
                if (word.charAt(i) == guess)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private int countNumNonHyphens()
    {
        String pattern = game.getPattern();
        int count = 0;
        int patternLength = pattern.length();
        for (int i = 0; i < patternLength; i++)
        {
            if (pattern.charAt(i) != EvilHangmanGame.HYPHEN)
            {
                count++;
            }
        }
        return count;
    }

    private char getUserGuess()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter guess: ");
        String guess = input.next().toLowerCase();
        if (!Character.isLetter(guess.charAt(0)) || guess.length() != 1)
        {
            throw new InvalidParameterException("Invalid input! Please try again");
        }
        return guess.charAt(0);
    }

    private void printWin(String theWord)
    {
        System.out.println("You win!");
        System.out.println("Word was: " + theWord);
    }

    private void printLoss(String theWord)
    {
        System.out.println("You lose!");
        System.out.println("Word was: " + theWord);
    }

    private void printCorrectGuess(char guess, int numOccurrences)
    {
        if (numOccurrences > 1)
        {
            System.out.println("Yes, there are " + numOccurrences + " " + guess + "'s");
        }
        else
        {
            System.out.println("Yes, there is " + numOccurrences + " " + guess);
        }
    }

    private void printIncorrectGuess(char guess)
    {
        System.out.println("Sorry, there are no " + guess + "'s");
    }

    private void printPrevGuesses(SortedSet<String> usedLetters)
    {
        StringBuilder out = new StringBuilder("Used letters: ");
        for (String letter : usedLetters)
        {
            out.append(letter);
            if (!letter.equals(usedLetters.last()))
            {
                out.append(", ");
            }
        }
        System.out.println(out.toString());
    }
}
