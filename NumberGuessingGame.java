import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {
        final int MAX_TRIES = 5;
        final int MAX_SECRET = 100;
        final int MIN_SECRET = 1;
        int totalScore = 0;
        boolean continuePlaying = true;

        System.out.println("Welcome to the Number Guessing game!");
        System.out.println("Enter the number betweeen 1 to 100!");

        Scanner inputScanner = new Scanner(System.in);

        while (continuePlaying) {
            int secret = (int) (Math.random() * (MAX_SECRET - MIN_SECRET + 1)) + MIN_SECRET;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            while (!hasGuessedCorrectly && attempts < MAX_TRIES) {
                System.out.print("Enter your guess: ");
                int userGuess = inputScanner.nextInt();
                attempts++;

                if (userGuess == secret) {
                    hasGuessedCorrectly = true;
                    totalScore += MAX_TRIES - attempts + 1;
                    System.out.println("Congratulations! You've guessed the number correctly.");
                    System.out.println("You took " + attempts + " attempts.");
                    System.out.println("Current score: " + totalScore);
                } else if (userGuess < secret) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("You have reached the maximum number of attempts.");
                System.out.println("The secret number was: " + secret);
            }

            System.out.print("Do you want to play again? (Y/N): ");
            String playAgainResponse = inputScanner.next();
            continuePlaying = playAgainResponse.equalsIgnoreCase("Y");
        }

        System.out.println("Game over! Your final score is: " + totalScore);
        inputScanner.close();
    }
}
