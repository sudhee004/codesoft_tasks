import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GuessingGame {
    public static void main(String[] args) {
        final int min = 1;
        final int max = 100;
        final int target = ThreadLocalRandom.current().nextInt(min, max + 1);

        System.out.printf("I have selected a number between %d and %d. Try to guess it.%n", min, max);

        int attempts = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter your guess: ");
                String input = scanner.nextLine().trim();

                int guess;
                try {
                    guess = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid integer.");
                    continue;
                }

                if (guess < min || guess > max) {
                    System.out.printf("Your guess is out of range (%d-%d).%n", min, max);
                    continue;
                }

                attempts++;

                if (guess == target) {
                    System.out.printf("Correct! You guessed it in %d attempt%s.%n", attempts, attempts == 1 ? "" : "s");
                    break;
                } else if (guess < target) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }
        }
    }
}
