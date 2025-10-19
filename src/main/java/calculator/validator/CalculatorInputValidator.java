package calculator.validator;

import calculator.util.Constants;
import calculator.util.ErrorMessage;

public class CalculatorInputValidator {

    public static void validateInput(String input) {
        validateInputNotEmpty(input);

        if (input.startsWith(Constants.CUSTOM_DELIMITER_PREFIX)) {
            validateCustomDelimiter(input);
            return;
        }
        validateDefaultDelimiter(input);
    }

    public static void validateNoNegativeNumbers(String[] numbers) {
        for (String number : numbers) {
            validateNotEmpty(number);
            validateIsNumber(number);
        }
    }

    private static void validateInputNotEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_VALUE.getMessage());
        }
    }

    private static void validateCustomDelimiter(String input) {
        int newlineIndex = input.indexOf(Constants.CUSTOM_DELIMITER_SEPARATOR);

        validateDelimiterPresence(newlineIndex);

        String delimiter = input.substring(Constants.CUSTOM_DELIMITER_PREFIX_LENGTH, newlineIndex).trim();
        validateSingleCharDelimiter(delimiter);

        validateNumbersPartNotEmpty(input, newlineIndex);
    }

    private static void validateDelimiterPresence(int newlineIndex) {
        if (newlineIndex == -1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CUSTOM_DELIMITER_FORMAT.getMessage());
        }
    }

    private static void validateNumbersPartNotEmpty(String input, int newlineIndex) {
        String numbersPart = input.substring(newlineIndex + Constants.CUSTOM_DELIMITER_PREFIX_LENGTH);
        if (numbersPart.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_VALUE.getMessage());
        }
    }

    private static void validateSingleCharDelimiter(String delimiter) {
        if (delimiter == null || delimiter.length() != 1) {
            throw new IllegalArgumentException(ErrorMessage.MULTIPLE_CUSTOM_DELIMITERS_NOT_ALLOWED.getMessage());
        }
    }

    private static void validateDefaultDelimiter(String input) {
        if (!input.matches("[0-9,:]*")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DEFAULT_DELIMITER.getMessage());
        }
    }

    private static void validateNotEmpty(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_VALUE.getMessage());
        }
    }

    private static void validateIsNumber(String number) {
        if (isNumber(number)) {
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER.getMessage());
    }

    private static boolean isNumber(String number) {
        return number.matches(Constants.POSITIVE_NUMBER_REGEX);
    }
}
