package ch1.sec3.ex44;

import edu.princeton.cs.algs4.Stack;

/**
 * Exercise 1.3.44.
 *
 * As the API description in the textbook is not explicit, I’m
 * interpreting the “character at the cursor” to be the closest
 * character on the left.
 *
 * A more sophisticated approach would be to maintain a variable
 * that stores the current character at the cursor, but this can’t
 * be a primitive <tt>char</tt> because what would it be when the
 * buffer is empty? (For this reason I abandoned an attempt to
 * implement this.)
 */
public class Buffer {
    private final Stack<Character> charsToLeft = new Stack<>();
    private final Stack<Character> charsToRight = new Stack<>();

    void insert(char c) {
        charsToLeft.push(c);
    }

    char get() {
        return charsToLeft.peek();
    }

    char delete() {
        return charsToLeft.pop();
    }

    void left(int k) {
        while (k > 0 && !charsToLeft.isEmpty()) {
            charsToRight.push(charsToLeft.pop());
        }
    }

    void right(int k) {
        while (k > 0 && !charsToRight.isEmpty()) {
            charsToLeft.push(charsToRight.pop());
        }
    }

    int size() {
        return charsToLeft.size() + charsToRight.size();
    }
}
