package ch1.sec3.ex05;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ex1_3_05 {

    public static void main(String[] args) {
        mystery(50);
    }

    /**
     * <strong>Question:</strong> What does the following code fragment [method, here]
     * print when <code>n</code> is 50?
     * <p>
     * Give a high-level description of what it does when presented with a
     * positive integer <code>n</code>.
     *
     * <strong>Answer:</strong>
     * <p>
     * When <code>n</code> is 50: <code>110010</code>
     * <p>
     * The fragment prints the binary representation of <code>n</code>, following the
     * typical manual process: repeatedly dividing by 2, tracking the remainder, and
     * writing out the results last-first.
     *
     * @param n the input number
     */
    static void mystery(int n) {
        Stack<Integer> stack = new Stack<Integer>();
        while (n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }
        for (int d : stack) StdOut.print(d);
        StdOut.println();
    }
}
