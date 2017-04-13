package objects.exceptions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ksenia on 13.04.2017.
 */
public class Calculator {
    Double x;
    Double y;
    Scanner sc;
    String s;

    public Calculator() {
        sc = new Scanner(System.in);
        s = "";
        System.out.println("Enter 'exit' or expression like 'x = 2', '5 + 3', 'y + 3'.");
    }

    public void start() throws WrongExpressionException, NoAvailableValueException, DivideByZeroExсeption {
        if (sc.hasNextLine()) {
            s = sc.nextLine();
        }
        if (s.equals("exit")) {
            System.exit(-1);
        }

        assert s.length() > 0 : "Пустая строка";
        boolean isFindSign = checkRegexp(s, "[\\+\\-\\*/=]", true);

        if (!isFindSign) {
            throw new WrongExpressionException("Wrong expression. Operation expected '+', '-', '*', '/', '='.");
        }

        if (!s.contains("=")) {
            if (checkRegexp(s, "\\-?(\\d+.?\\d*|x|y)\\s([\\+\\-\\*/])\\s(\\d+.?\\d*|x|y)", false)) {
                String[] commamd = s.split(" ");
                checkValueIsSet(commamd[0], commamd[2], "x");
                checkValueIsSet(commamd[0], commamd[2], "y");

                double[] a = new double[2];
                for (int i = 0, j = 0; i < 3; i += 2, j++) {
                    if (checkRegexp(commamd[i], "\\-?\\d+.?\\d*", false)) {
                        a[j] = Double.parseDouble(commamd[i]);
                    } else if (commamd[i].equals("x")) {
                        a[j] = x;
                    } else {
                        a[j] = y;
                    }
                }

                System.out.println(doOperation(a[0], a[1], commamd[1]));
            } else {
                throw new WrongExpressionException("Wrong expression. Expected like '5 + 3' or 'x + 2'.");
            }
        } else {
            if (checkRegexp(s, "([xy])\\s=\\s(\\-?(\\d)+)", false)) {
                String[] commamd = s.split(" ");
                if (commamd[0].equals("x")) {
                    x = Double.parseDouble(commamd[2]);
                } else {
                    y = Double.parseDouble(commamd[2]);
                }
            } else {
                throw new WrongExpressionException("Wrong expression. Expected like 'x = 2'.");
            }
        }
    }

    private void checkValueIsSet(String tested1, String tested2, String pattern) throws NoAvailableValueException {
        if (tested1.equals(pattern) || tested2.equals(pattern)) {
            boolean isSet = true;
            switch (pattern) {
                case "x":
                    if (x == null) {
                        isSet = false;
                    }
                    break;
                case "y":
                    if (y == null) {
                        isSet = false;
                    }
            }
            if (!isSet) {
                throw new NoAvailableValueException("Value isn't specified. Use '" + pattern + " = 2' to set it.");
            }
        }
    }

    public double doOperation(double a, double b, String sign) throws DivideByZeroExсeption, WrongExpressionException {
        switch (sign) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0.0) {
                    throw new DivideByZeroExсeption("Devide by zero. Set denominator different from zero.");
                }
                return a / b;
            default:
                throw new WrongExpressionException("Wrong operation. Operation expected '+', '-', '*', '/', '='.");
        }
    }

    private boolean checkRegexp(String s, String pattern, boolean find) {
        Pattern regexp = Pattern.compile(pattern);
        Matcher matcher = regexp.matcher(s);
        if (find) {
            return matcher.find();
        }
        return matcher.matches();
    }
}
