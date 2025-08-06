package io.github.bokalebsson.util;

import java.util.Scanner;

public class UserInputManager {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readNonEmptyString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!InputValidator.isNonEmptyString(input)) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (!InputValidator.isNonEmptyString(input));
        return input.trim();
    }

    public static String readValidName(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!InputValidator.isValidName(input)) {
                System.out.println("Invalid name. Use letters, spaces, or hyphens only.");
            }
        } while (!InputValidator.isValidName(input));
        return input.trim();
    }

    public static String readValidEmail(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!InputValidator.isValidEmail(input)) {
                System.out.println("Invalid email format. Please try again.");
            }
        } while (!InputValidator.isValidEmail(input));
        return input.trim();
    }

    public static String readPositiveInteger(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!InputValidator.isPositiveInteger(input)) {
                System.out.println("Please enter a positive integer.");
            }
        } while (!InputValidator.isPositiveInteger(input));
        return input.trim();
    }

    public static int readIntInRange(String prompt, int min, int max) {
        int number;
        do {
            System.out.print(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Input must be between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        } while (true);
    }

    public static String readFutureDate(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!InputValidator.isValidDate(input) || !InputValidator.dateNotInThePast(input)) {
                System.out.println("Invalid date. Please enter a valid date in yyyy-MM-dd format that is today or in the future.");
            }
        } while (!InputValidator.isValidDate(input) || !InputValidator.dateNotInThePast(input));
        return input.trim();
    }
}