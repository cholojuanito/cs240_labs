package hangman;

import java.security.InvalidParameterException;
import java.util.Scanner;
import java.util.SortedSet;

public class EvilHangmanRunTime
{
    private EvilHangmanGame game;
    private int allowedNumGuesses;

    public EvilHangmanRunTime(EvilHangmanGame game, int allowedNumGuesses)
    {
        this.game = game;
        this.allowedNumGuesses = allowedNumGuesses;
    }

    public void runGame()
    {
        SortedSet<String> usedLetters = game.getUsedLetters();
        SortedSet<String> possibleWords;
        int numGuessesLeft = allowedNumGuesses;
        String currPattern;
        char guess = 'a';

        while (numGuessesLeft > 0)
        {
            System.out.println("You have " + numGuessesLeft + " guesses left");
            printPrevGuesses(usedLetters);
            System.out.println(game.getPattern());
            // Get guess
            try
            {
                guess = getUserGuess();
                possibleWords = game.makeGuess(guess);
            }
            catch (IEvilHangmanGame.GuessAlreadyMadeException e)
            {
                System.out.println("You have already guessed '" + guess + "'");
                continue;
            }
            catch (InvalidParameterException e)
            {
                System.out.println(e.getMessage());
                continue;
            }
            // Print wrong or right
            if (possibleWords == null || possibleWords.size() > 0)
            {
                printCorrectGuess(guess, 1);
            }
            else
            {
                printIncorrectGuess(guess);
                numGuessesLeft--;
            }
        }

        // END print win or loss

    }

    private char getUserGuess()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter guess: ");
        char guess = input.next().charAt(0);
        if (Character.isLetter(guess))
        {
            return guess;
        }
        else
        {
            throw new InvalidParameterException("Invalid Input");
        }
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

    private void printCorrectGuess(char guess, int numAppearances)
    {
        if (numAppearances > 1)
        {
            System.out.println("Yes, there are " + numAppearances + " " + guess + "'s");
        }
        else
        {
            System.out.println("Yes, there is " + numAppearances + " " + guess);
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
