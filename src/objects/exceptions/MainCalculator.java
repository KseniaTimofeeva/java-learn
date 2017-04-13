package objects.exceptions;

/**
 * Created by ksenia on 13.04.2017.
 */
public class MainCalculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        while (true) {
            try {
                calculator.start();
            } catch (WrongExpressionException | NoAvailableValueException | DivideByZeroExсeption ex) {
                ex.printStackTrace();
            }
        }
    }
}
