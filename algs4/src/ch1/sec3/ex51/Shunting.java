package ch1.sec3.ex51;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Exercise 1.3.51 -- Expression evaluation with precedence.
 *
 * Uses Dijkstra’s “shunting” algorithm.
 */
public class Shunting {
    private static final int TOKEN_IS_NUMBER = -1;
    private static final Map<String, Integer> precedence = new HashMap<>();
    private static final Set<String> knownOperators = new HashSet<>();

    static {
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
        precedence.put("sqrt", 3);
        precedence.put("(", 0);
        precedence.put(")", 4);
    }

    static {
        knownOperators.add("+");
        knownOperators.add("-");
        knownOperators.add("*");
        knownOperators.add("/");
    }

    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            int p = precedence.getOrDefault(s, TOKEN_IS_NUMBER);
            if (p == TOKEN_IS_NUMBER) {
                vals.push(Double.parseDouble(s));
            } else if ("sqrt".equals(s)) {
                // This could be generalised to check against a set of functions, but
                // we only have square root so we’re checking for it explicitly here.
                vals.push(Math.sqrt(vals.pop()));
            } else if (knownOperators.contains(s)) {
                // While read operator’s precedence is <= that at the top of the stack
                // **and** there isn’t a left paren at the top of the stack, evaluate
                // the operators on the stack (ie clear through higher precedence
                // operators that have been seen and stored previously).
                while (!ops.isEmpty() && p <= precedence.get(ops.peek()) && !"(".equals(ops.peek())) {
                    vals.push(evaluateFirstOperator(ops, vals));
                }
                ops.push(s);
            } else if ("(".equals(s)) {
                ops.push(s);
            } else if (")".equals(s)) {
                while (!"(".equals(ops.peek())) {
                    vals.push(evaluateFirstOperator(ops, vals));
                }
                ops.pop(); // Discard left parenthesis.
            }
        }

        while (!ops.isEmpty()) {
            vals.push(evaluateFirstOperator(ops, vals));
        }
        StdOut.println(vals.pop());
    }

    private static Double evaluateFirstOperator(Stack<String> operators, Stack<Double> values) {
        String op = operators.pop();
        double right = values.pop();
        double left = values.pop();
        return evaluateOperator(op, left, right);
    }

    private static Double evaluateOperator(String operator, Double left, Double right) {
        switch (operator) {
            case "*":
                return left * right;
            case "/":
                return left / right;
            case "+":
                return left + right;
            case "-":
                return left - right;
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }
}
