package ch1.sec3.ex42;

import edu.princeton.cs.algs4.Stack;

/**
 * As with exercise 1.3.41, this assumes that we haven’t implemented
 * the Iterable interface for the Stack class, and can only copy a
 * stack using primitive stack operations.
 */
public class StackCopy<Item> extends Stack<Item> {
    public StackCopy(Stack<Item> other) {
        Stack<Item> reversed = new Stack<>();
        while (!other.isEmpty()) {
            reversed.push(other.pop());
        }
        while (!reversed.isEmpty()) {
            Item item = reversed.pop();
            other.push(item);
            push(item);
        }
    }
}
