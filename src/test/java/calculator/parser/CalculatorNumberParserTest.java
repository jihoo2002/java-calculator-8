package calculator.parser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorNumberParserTest {

    @Test
    void parseNumbers_정상_입력() {
        String[] input = {"1", "20", "300"};
        List<Long> result = CalculatorNumberParser.parseNumbers(input);

        assertEquals(3, result.size());
        assertEquals(1L, result.get(0));
        assertEquals(20L, result.get(1));
        assertEquals(300L, result.get(2));
    }

    @Test
    void parseNumbers_빈_배열() {
        String[] input = {};

        List<Long> result = CalculatorNumberParser.parseNumbers(input);

        assertTrue(result.isEmpty());
    }
}