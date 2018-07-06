package hangman;

import java.io.File;

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
            System.exit(0);
        }

        if (wordLength < 2)
        {
            System.out.println("Word length must be greater than 1! Please try again!");
            System.exit(0);
        }
        if (allowedNumGuesses < 1)
        {
            System.out.println("Allowed number of guesses must be greater than 0! Please try again!");
            System.exit(0);
        }

        EvilHangmanGame game = new EvilHangmanGame();
        game.startGame(dictionary, wordLength);

        EvilHangmanRunTime gameRunTime = new EvilHangmanRunTime(game, allowedNumGuesses, wordLength);
        gameRunTime.runGame();
    }
}
