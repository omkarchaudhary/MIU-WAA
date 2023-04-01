package servlets;

public class Calc {
    private int first;
    private int second;
    private String op;
    private double result;

    public Calc(int first, int second, String op, double result) {
        this.first = first;
        this.second = second;
        this.op = op;
        this.result = result;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
