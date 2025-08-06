package io.github.bokalebsson.util;

import java.util.Scanner;

public class UserInputManager {

    private final Scanner scanner;

    public UserInputManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readNonEmptyString(String prompt) {
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

    public String readValidName(String prompt) {
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

    public String readValidEmail(String prompt) {
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

    public String readPositiveInteger(String prompt) {
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

    public String readFutureDate(String prompt) {
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