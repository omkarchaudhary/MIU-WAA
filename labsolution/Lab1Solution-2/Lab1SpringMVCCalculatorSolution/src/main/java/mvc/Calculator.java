package mvc;

public class Calculator {
    public static double calculate(String operator, int x, int y) {
        if (operator.equals("+")) {
            return x + y;
        } else if (operator.equals("-")) {
            return x - y;
        } else if (operator.equals("*")) {
            return x * y;
        } else if (operator.equals("/")) {
            return x / y;
        }
        return 0;
    }
}
