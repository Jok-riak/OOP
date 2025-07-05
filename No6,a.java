import java.util.Scanner;

public class PerfectNumberChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;

        // (i) Prompt user and validate input using exception handling
        while (true) {
            try {
                System.out.print("Enter a positive integer: ");
                number = Integer.parseInt(scanner.nextLine());
                if (number <= 0) {
                    System.out.println("Number must be greater than 0. Try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }

        // (ii) Calculate sum of proper divisors
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        // (iii) Determine if the number is perfect
        if (sum == number) {
            System.out.println(number + " is a perfect number.");
        } else {
            System.out.println(number + " is not a perfect number.");
        }

        scanner.close();
    }
}

