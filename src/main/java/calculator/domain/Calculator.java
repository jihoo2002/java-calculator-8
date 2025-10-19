package calculator.domain;

import java.util.List;

public class Calculator {

    public static long sum(List<Long> numbers) {
        return numbers.stream()
                .mapToLong(Long::longValue)
                .sum();
    }
}
