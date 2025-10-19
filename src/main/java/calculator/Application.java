package calculator;

import calculator.service.CalculatorService;
import calculator.view.CalculatorView;

public class Application {
    public static void main(String[] args) {
        CalculatorView view = new CalculatorView();
        String input = view.calculatorInput();
        CalculatorService service = new CalculatorService();
        long calculator = service.calculate(input);
        view.calculatorOutput(calculator);
    }
}
