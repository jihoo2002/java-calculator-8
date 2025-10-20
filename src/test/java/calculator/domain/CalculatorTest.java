package calculator.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    @DisplayName("여러 숫자의 합 구하기")
    void sum_여러_숫자_합() {
        List<Long> numbers = List.of(1L, 2L, 3L, 4L, 5L);
        long result = Calculator.sum(numbers);
        assertEquals(15L, result);
    }

    @Test
    @DisplayName("빈 리스트 입력 시 합 0 반환")
    void sum_빈_리스트() {
        List<Long> numbers = List.of();
        long result = Calculator.sum(numbers);
        assertEquals(0L, result);
    }

    @Test
    @DisplayName("하나의 숫자 입력 시 그대로 반환")
    void sum_단일_숫자() {
        List<Long> numbers = List.of(1L);
        long result = Calculator.sum(numbers);
        assertEquals(1L, result);
    }
}
