package game;

import javax.swing.*;


public class App {
    public static void main(String[] args) {
        int maxAttempts = 10; 
        System.out.println("Welcome to Guess the Number Game!");

        while (true) {
            GuessNumber guessNumber = new GuessNumber();

            int attempts = 0;
            int score = 0;

            System.out.println("A random number has been generated between 1 and 100.");

            while (true) {
                if (attempts >= maxAttempts) {
                    System.out.println("You've exceeded the maximum number of attempts! Better luck next time.");
                    System.out.println("The correct number was: " + GuessNumber.getGeneratedNumber());
                    break;
                }

                String userGuessString = JOptionPane.showInputDialog(null, "Enter your guess:", "Guess the Number Between 1 and 100", JOptionPane.QUESTION_MESSAGE);


                try {
                    if (userGuessString != null) {
                        int userGuess = Integer.parseInt(userGuessString);
                        attempts++;

                        if (guessNumber.checkGuess(userGuess)) {
                            JOptionPane.showMessageDialog(null, "Congratulations! You've guessed the number!");
                            score = 100 - (attempts - 1);  // Example of scoring based on attempts
                            if (attempts <= 3) {
                                score += 20;  // Bonus points for quick guesses
                            }
                            JOptionPane.showMessageDialog(null, "Attempts: " + attempts);
                            JOptionPane.showMessageDialog(null, "Score: " + score);
                            break;
                        } else {
                            if (userGuess < GuessNumber.getGeneratedNumber()) {
                                JOptionPane.showMessageDialog(null, "Higher!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Lower!");
                            }
                        }

                        // Display remaining attempts after each guess
                        int remainingAttempts = maxAttempts - attempts;
                        JOptionPane.showMessageDialog(null, "Attempts left: " + remainingAttempts);
                    } else {
                        JOptionPane.showMessageDialog(null, "No input provided. Exiting the game.");
                        break;
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                }
            }

            int response = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
            if (response != JOptionPane.YES_OPTION) {
                break;
            }
        }

        JOptionPane.showMessageDialog(null, "Thank you for playing! Goodbye!");
    }
}
