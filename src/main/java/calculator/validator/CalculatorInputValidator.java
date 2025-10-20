package calculator.validator;

import calculator.util.Constants;
import calculator.util.ErrorMessage;

import java.util.regex.Pattern;

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
        int delimiterEndIndex = input.indexOf(Constants.CUSTOM_DELIMITER_SEPARATOR);

        validateDelimiterPresence(delimiterEndIndex);

        String delimiter = input.substring(Constants.CUSTOM_DELIMITER_PREFIX_LENGTH, delimiterEndIndex).trim();
        validateSingleCharDelimiter(delimiter);

        validateNumbersPartNotEmpty(input, delimiterEndIndex);
    }

    private static void validateDelimiterPresence(int delimiterEndIndex) {
        if (delimiterEndIndex == -1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CUSTOM_DELIMITER_FORMAT.getMessage());
        }
    }

    private static void validateNumbersPartNotEmpty(String input, int delimiterEndIndex) {
        String numbersPart = input.substring(delimiterEndIndex + Constants.CUSTOM_DELIMITER_PREFIX_LENGTH);

        if (numbersPart.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_VALUE.getMessage());
        }
    }

    private static void validateSingleCharDelimiter(String customDelimiter) {
        if (customDelimiter == null || customDelimiter.length() != 1) {
            throw new IllegalArgumentException(ErrorMessage.MULTIPLE_CUSTOM_DELIMITERS_NOT_ALLOWED.getMessage());
        }
    }

    private static void validateDefaultDelimiter(String input) {
        if (Pattern.compile(Constants.DEFAULT_DELIMITER_REGEX).matcher(input).find()) {
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_DEFAULT_DELIMITER.getMessage());
    }

    private static void validateNotEmpty(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_VALUE.getMessage());
        }
    }

    private static void validateIsNumber(String number) {
        if (isPositiveNumber(number)) {
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER.getMessage());
    }

    private static boolean isPositiveNumber(String number) {
        return number.matches(Constants.POSITIVE_NUMBER_REGEX);
    }
}
