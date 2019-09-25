package ch1.sec3.ex11;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class EvaluatePostfix {
    public static void main(String[] args) {
        Stack<Double> operands = new Stack<>();
        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            switch (token) {
                case "+":
                case "-":
                case "/":
                case "*":
                    Double second = operands.pop();
                    Double first = operands.pop();
                    operands.push(evaluatePostfix(first, second, token));
                    break;
                default:
                    operands.push(Double.parseDouble(token));
            }
        }
        StdOut.println(operands.pop());
    }

    private static Double evaluatePostfix(Double first, Double second, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "/":
                return first / second;
            case "*":
                return first * second;
        }
        throw new IllegalArgumentException("Operator not recognised: " + operator);
    }
}
