package calculator.validator;

import calculator.util.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorInputValidatorTest {

    @Nested
    @DisplayName("기본 구분자 검증")
    class 기본구분자_테스트 {

        @Test
        @DisplayName("정상 입력 시 예외 없음")
        void validator_정상() {
            String input = "1,2:3";
            assertDoesNotThrow(() -> CalculatorInputValidator.validateInput(input));
        }

        @Test
        @DisplayName("잘못된 문자 포함시 예외 발생")
        void validator_잘못된문자() {
            String input = "1,2:a";
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> CalculatorInputValidator.validateInput(input));
            assertEquals(ErrorMessage.INVALID_DEFAULT_DELIMITER.getMessage(), e.getMessage());
        }

        @Test
        @DisplayName("빈 입력값 예외 발생")
        void validator_빈값() {
            String input = "";
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> CalculatorInputValidator.validateInput(input));
            assertEquals(ErrorMessage.EMPTY_VALUE.getMessage(), e.getMessage());
        }

        @Test
        @DisplayName("null 입력 예외 발생")
        void validator_null값() {
            String input = null;
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> CalculatorInputValidator.validateInput(input));
            assertEquals(ErrorMessage.EMPTY_VALUE.getMessage(), e.getMessage());
        }
    }

    @Nested
    @DisplayName("커스텀 구분자 검증")
    class 커스텀구분자_테스트 {

        @Test
        @DisplayName("정상 커스텀 구분자 입력 시 예외 없음")
        void validator_정상() {
            String input = "//;\\n1;2;3";
            assertDoesNotThrow(() -> CalculatorInputValidator.validateInput(input));
        }

        @Test
        @DisplayName("줄바꿈(\\n) 없는 커스텀 구분자 입력 시 예외 발생")
        void validator_잘못된형식_newline없음() {
            String input = "//;1;2;3";
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> CalculatorInputValidator.validateInput(input));
            assertEquals(ErrorMessage.INVALID_CUSTOM_DELIMITER_FORMAT.getMessage(), e.getMessage());
        }

        @Test
        @DisplayName("구분자가 두 글자 이상일 때 예외 발생")
        void validator_여러글자_구분자() {
            String input = "//;;\\n1;;2;;3";
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> CalculatorInputValidator.validateInput(input));
            assertEquals(ErrorMessage.MULTIPLE_CUSTOM_DELIMITERS_NOT_ALLOWED.getMessage(), e.getMessage());
        }

        @Test
        @DisplayName("구분자 정의 이후 숫자 미입력 시 예외 발생")
        void validator_구분자후_숫자없음() {
            String input = "//;\\n";
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> CalculatorInputValidator.validateInput(input));
            assertEquals(ErrorMessage.EMPTY_VALUE.getMessage(), e.getMessage());
        }
    }

    @Nested
    @DisplayName("숫자 검증")
    class 숫자검증_테스트 {

        @Test
        @DisplayName("정상 숫자 배열 입력 시 예외 없음")
        void validator_정상() {
            String[] numbers = {"1", "2", "3"};
            assertDoesNotThrow(() -> CalculatorInputValidator.validateNoNegativeNumbers(numbers));
        }

        @Test
        @DisplayName("음수 포함시 예외 발생")
        void validator_음수값() {
            String[] numbers = {"1", "-2", "3"};
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> CalculatorInputValidator.validateNoNegativeNumbers(numbers));
            assertEquals(ErrorMessage.INVALID_NUMBER.getMessage(), e.getMessage());
        }

        @Test
        @DisplayName("빈 문자열 포함시 예외 발생")
        void validator_빈문자열포함() {
            String[] numbers = {"1", "", "3"};
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> CalculatorInputValidator.validateNoNegativeNumbers(numbers));
            assertEquals(ErrorMessage.EMPTY_VALUE.getMessage(), e.getMessage());
        }

        @Test
        @DisplayName("null 포함시 예외 발생")
        void validator_null포함() {
            String[] numbers = {"1", null, "3"};
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> CalculatorInputValidator.validateNoNegativeNumbers(numbers));
            assertEquals(ErrorMessage.EMPTY_VALUE.getMessage(), e.getMessage());
        }

        @Test
        @DisplayName("숫자가 아닌 값 포함 시 예외 발생")
        void validator_숫자아닌값포함() {
            String[] numbers = {"1", "A", "3"};
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> CalculatorInputValidator.validateNoNegativeNumbers(numbers));
            assertEquals(ErrorMessage.INVALID_NUMBER.getMessage(), e.getMessage());
        }
    }
}
