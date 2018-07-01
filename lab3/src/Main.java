import hangman.EvilHangmanGame;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File dictionary = new File("");
        int wordLength = 0;
        int numGuesses = 0;
        if (args.length > 2){
            dictionary = new File(args[0]);
            wordLength = Integer.parseInt(args[1]);
            numGuesses = Integer.parseInt(args[2]);
        }
        else
        {
            System.out.println("An invalid number of arguments was detected! \nPlease verify your arguments and try again.");
        }
        EvilHangmanGame game = new EvilHangmanGame();
        game.startGame(dictionary, wordLength);
    }
}
