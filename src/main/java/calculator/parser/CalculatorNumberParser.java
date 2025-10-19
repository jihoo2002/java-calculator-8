package calculator.parser;

import java.util.ArrayList;
import java.util.List;

public class CalculatorNumberParser {

    public static List<Long> parseNumbers(String[] inputs) {
        List<Long> numbers = new ArrayList<>();

        for (String value : inputs) {
            long parsedValue = Long.parseLong(value);
            numbers.add(parsedValue);
        }
        return numbers;
    }
}
