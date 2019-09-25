package ch1.sec3.ex09;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FullyParenthesise {
    public static void main(String[] args) {
        // Input: 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
        // Output: ( ( 1 + 2 ) * ( ( 3 -4 ) * ( 5 - 6 ) ) )
        Stack<String> operands = new Stack<>();
        Stack<String> operators = new Stack<>();
        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            switch (token) {
                case "(":   // Ignore opening parentheses (which should be absent)
                    break;
                case "+":
                case "-":
                case "/":
                case "*":
                    operators.push(token);
                    break;
                case ")":
                    String right = operands.pop();
                    String left = operands.pop();
                    String operator = operators.pop();
                    String expression = makeParenthesisedInfixExpression(left, operator, right);
                    operands.push(expression);
                    break;
                default:
                    operands.push(token);
                    break;
            }
        }
        String parenthesisedExpression = operands.pop();
        StdOut.println(parenthesisedExpression);
    }

    private static String makeParenthesisedInfixExpression(
            String leftOperand, String operator, String rightOperand
    ) {
        return String.format("( %s %s %s )", leftOperand, operator, rightOperand);
    }
}
