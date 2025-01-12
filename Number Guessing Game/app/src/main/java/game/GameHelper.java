package game;

public class GameHelper {

    public String provideHint(int guess) {
        if (guess < 0 || guess > 100) {
            return "Please guess a number between 1 and 100.";
        } else if (guess < GuessNumber.getGeneratedNumber()) {  
            return "Higher!";
        } else {
            return "Lower!";
        }
    }
}
