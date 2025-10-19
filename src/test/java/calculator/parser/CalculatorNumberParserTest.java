package calculator.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorNumberParserTest {

    @Test
    @DisplayName("정상 숫자 입력 파싱")
    void parseNumbers_정상_입력() {
        String[] input = {"1", "20", "300"};
        List<Long> result = CalculatorNumberParser.parseNumbers(input);

        assertEquals(3, result.size());
        assertEquals(1L, result.get(0));
        assertEquals(20L, result.get(1));
        assertEquals(300L, result.get(2));
    }

    @Test
    @DisplayName("빈 배열 입력 시 반환 값 없음")
    void parseNumbers_빈_배열() {
        String[] input = {};
        List<Long> result = CalculatorNumberParser.parseNumbers(input);

        assertTrue(result.isEmpty());
    }
}