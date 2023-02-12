package servlets;

public class Calculator {
    private int firstNumber;
    private int secondNumber;
    private String operation;
    private double result;

    public Calculator(int firstNumber, int secondNumber, String operation, double result) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operation = operation;
        this.result = result;
    }

    public int getFirstNumber() {
        return firstNumber;
    }
    public int getSecondNumber() {
        return secondNumber;
    }
    public String getOperation() {
        return operation;
    }
    public double getResult() {
        return result;
    }
}
