package io.github.bokalebsson.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidator {

    // Checks if the input string is not null and not blank
    public static boolean isNonEmptyString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Checks if the input string is a positive integer (e.g., for age or IDs)
    public static boolean isPositiveInteger(String input) {
        try {
            int value = Integer.parseInt(input.trim());
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Simple email format check
    public static boolean isValidEmail(String input) {
        if (input == null) return false;
        return input.trim().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    // Checks if the name contains only letters, spaces or hyphens and is not empty
    public static boolean isValidName(String input) {
        if (input == null) return false;
        String trimmed = input.trim();
        if (trimmed.isEmpty()) return false;
        return trimmed.matches("^[A-Za-zåäöÅÄÖ\\-\\s]+$");
    }

    // Checks if the date is valid and in ISO format (yyyy-MM-dd)
    public static boolean isValidDate(String input) {
        if (input == null) return false;
        String trimmed = input.trim();
        if (trimmed.isEmpty()) return false;

        try {
            LocalDate.parse(trimmed, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Checks if the date is valid and is today or a future date
    public static boolean dateNotInThePast(String input) {
        if (!isValidDate(input)) return false;

        LocalDate date = LocalDate.parse(input.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
        return !date.isBefore(LocalDate.now());
    }
}