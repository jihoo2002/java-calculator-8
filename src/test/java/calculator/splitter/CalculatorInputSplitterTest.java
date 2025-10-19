package calculator.splitter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorInputSplitterTest {

    @Nested
    @DisplayName("기본 구분자 분리 테스트")
    class 기본구분자_테스트 {

        @Test
        @DisplayName("쉼표와 콜론으로 정상 분리")
        void split_기본_구분자() {
            String input = "1,2:3";
            String[] result = CalculatorInputSplitter.split(input);
            assertArrayEquals(new String[]{"1", "2", "3"}, result);
        }

        @Test
        @DisplayName("숫자 하나만 있을 때 분리")
        void split_숫자_하나만() {
            String input = "42";
            String[] result = CalculatorInputSplitter.split(input);
            assertArrayEquals(new String[]{"42"}, result);
        }

        @Test
        @DisplayName("빈 문자열 입력 시 분리 결과")
        void split_빈문자열() {
            String input = "";
            String[] result = CalculatorInputSplitter.split(input);
            assertArrayEquals(new String[]{""}, result);
        }
    }

    @Nested
    @DisplayName("커스텀 구분자 분리 테스트")
    class 커스텀구분자_테스트 {

        @Test
        @DisplayName("세미콜론 커스텀 구분자로 정상 분리")
        void split_커스텀_구분자() {
            String input = "//;\\n1;2;3";
            String[] result = CalculatorInputSplitter.split(input);
            assertArrayEquals(new String[]{"1", "2", "3"}, result);
        }

        @Test
        @DisplayName("숫자 하나만 있는 커스텀 구분자 입력")
        void split_커스텀_숫자_하나만() {
            String input = "//;\\n7";
            String[] result = CalculatorInputSplitter.split(input);
            assertArrayEquals(new String[]{"7"}, result);
        }

        @Test
        @DisplayName("커스텀 구분자 선언만 있고 숫자 없음")
        void split_커스텀_구분자_숫자없음() {
            String input = "//;\\n";
            String[] result = CalculatorInputSplitter.split(input);
            assertArrayEquals(new String[]{""}, result);
        }
    }
}
