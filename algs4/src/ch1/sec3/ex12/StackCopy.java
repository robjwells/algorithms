package ch1.sec3.ex12;

import edu.princeton.cs.algs4.Stack;

public class StackCopy {
    /**
     * Returns a <code>Stack&lt;T&gt;</code> that is a shallow copy of <code>source</code>.
     * @param source the <code>Stack</code> to copy from
     * @param <T> the type of the <code>source</code> <code>Stack</code>’s contents
     * @return a new <code>Stack&lt;T&gt;</code> that is a shallow copy of <code>source</code>
     */
    public static <T> Stack<T> copy(Stack<T> source) {
        Stack<T> temp = new Stack<>();
        for (T item : source) {
            temp.push(item);
        }
        Stack<T> dest = new Stack<>();
        for (T item : temp) {
            dest.push(item);
        }
        return dest;
    }

    public static void main(String[] args) {
        Stack<String> source = new Stack<>();
        source.push("a");
        source.push("b");
        source.push("c");
        Stack<String> copy = copy(source);
        while (!source.isEmpty()) {
            assert source.pop().equals(copy.pop()); // Assertions must be enabled (-ea)
        }
    }
}
