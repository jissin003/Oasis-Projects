package game;

import java.util.Random;

public class GuessNumber {
    private static int generatedNumber;  

    public GuessNumber() {
        Random random = new Random();
        generatedNumber = random.nextInt(100) + 1;  
    }

    public static int getGeneratedNumber() {
        return generatedNumber;
    }

    public boolean checkGuess(int guess) {
        return guess == generatedNumber;
    }
}
