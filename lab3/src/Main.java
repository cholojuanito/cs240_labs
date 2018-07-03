import hangman.EvilHangmanGame;
import hangman.EvilHangmanRunTime;
import hangman.IEvilHangmanGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;
import java.util.Scanner;
import java.util.SortedSet;

public class Main
{

    public static void main(String[] args) {
        File dictionary = new File("");
        int wordLength = 0;
        int allowedNumGuesses = 0;
        if (args.length > 2)
        {
            dictionary = new File(args[0]);
            wordLength = Integer.parseInt(args[1]);
            allowedNumGuesses = Integer.parseInt(args[2]);
        }
        else
        {
            System.out.println("An invalid number of arguments was detected! \nPlease verify your arguments and try again.");
        }
        EvilHangmanGame game = new EvilHangmanGame();
        game.startGame(dictionary, wordLength);

        EvilHangmanRunTime gameRunTime = new EvilHangmanRunTime(game, allowedNumGuesses);
        gameRunTime.runGame();
    }
}
