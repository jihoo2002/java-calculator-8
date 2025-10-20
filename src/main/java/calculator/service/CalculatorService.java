package calculator.service;

import calculator.domain.Calculator;
import calculator.parser.CalculatorNumberParser;
import calculator.splitter.CalculatorInputSplitter;
import calculator.validator.CalculatorInputValidator;

import java.util.List;

public class CalculatorService {

    public long calculate(String input) {
        validateInput(input);
        String[] numbers = splitInput(input);
        validateNoNegative(numbers);
        List<Long> parsedNumbers = parseNumbers(numbers);

        return sumNumbers(parsedNumbers);
    }

    private void validateInput(String input) {
        CalculatorInputValidator.validateInput(input);
    }

    private String[] splitInput(String input) {
        return CalculatorInputSplitter.split(input);
    }

    private void validateNoNegative(String[] numbers) {
        CalculatorInputValidator.validateNoNegativeNumbers(numbers);
    }

    private List<Long> parseNumbers(String[] numbers) {
        return CalculatorNumberParser.parseNumbers(numbers);
    }

    private long sumNumbers(List<Long> numbers) {
        return Calculator.sum(numbers);
    }
}
