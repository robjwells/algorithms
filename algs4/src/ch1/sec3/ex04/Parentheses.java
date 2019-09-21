package ch1.sec3.ex04;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 1.3.4
 * <p>
 * Write a stack client <code>Parentheses</code> that reads in a text stream from standard input
 * and uses a stack to determine whether its parentheses are properly balanced.
 * For example, your program should print <code>true</code> for <code>[()]{}{[()()]()}</code>
 * and <code>false</code> for <code>[(])</code>.
 */
public class Parentheses {
    public static void main(String[] args) {
        String input = StdIn.readLine();
        StdOut.println(parenthesesAreBalanced(input));
    }

    /**
     * Check if brackets in the <code>input</code> string are balanced.
     * <p>
     * The brackets considered are (, [ and {.
     *
     * @param input a string consisting solely of characters in <code>()[]{}</code>
     * @return <code>true</code> if all brackets in <code>input</code> are balanced, else <code>false</code>
     */
    public static boolean parenthesesAreBalanced(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            switch (c) {
                case ')':
                    if (stack.pop() != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.pop() != '[') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.pop() != '{') {
                        return false;
                    }
                    break;
                default:
                    stack.push(c);
            }
        }
        return true;
    }
}
