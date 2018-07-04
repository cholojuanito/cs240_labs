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
        SortedSet<String> possibleWords = null;
        int numGuessesLeft = allowedNumGuesses;
        char guess = 'a';

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
                    }
                    printCorrectGuess(guess, game.numOccurrencesInPattern(game.getPattern(), guess));
                }
                else
                {
                    numGuessesLeft--;
                    if (numGuessesLeft == 0)
                    {
                        printLoss(possibleWords.first());
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

//    private String pickAWord(SortedSet<String> possibleWords)
//    {
//        String endPattern = game.getPattern();
//        for (String word : possibleWords)
//        {
//            int endPatternLength = endPattern.length();
//            for (int i = 0; i < endPatternLength; i++)
//            {
//                if (word.charAt(i) != endPattern.charAt(i))
//                {
//                    return word;
//                }
//            }
//        }
//        return endPattern;
//    }

    private boolean wasCorrectGuess(SortedSet<String> possibleWords, char guess)
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
        String guess = input.next();
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
        System.exit(0);
    }

    private void printLoss(String theWord)
    {
        System.out.println("You lose!");
        System.out.println("Word was: " + theWord);
        System.exit(0);
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
