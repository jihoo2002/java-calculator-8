package calculator.splitter;

import calculator.util.Constants;

import java.util.regex.Pattern;

public class CalculatorInputSplitter {

    public static String[] split(String input) {
        if (input.startsWith(Constants.CUSTOM_DELIMITER_PREFIX)) {
            return splitCustom(input);
        }

        return input.split(Constants.DEFAULT_DELIMITER_REGEX);
    }

    private static String[] splitCustom(String input) {
        int delimiterEndIndex = input.indexOf(Constants.CUSTOM_DELIMITER_SEPARATOR);
        String delimiter = input.substring(Constants.CUSTOM_DELIMITER_PREFIX_LENGTH, delimiterEndIndex).trim();
        String numbersPart = input.substring(delimiterEndIndex + Constants.CUSTOM_DELIMITER_PREFIX_LENGTH);
        return numbersPart.split(Pattern.quote(delimiter));
    }
}
