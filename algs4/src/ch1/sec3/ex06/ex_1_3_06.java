package ch1.sec3.ex06;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ex_1_3_06 {
    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");

        mystery(queue);

        for (String s : queue) StdOut.println(s);
    }

    /**
     * What does the following code fragment [method, here] do to the queue <code>q</code>?
     * <p>
     * It reverses the order of its contents.
     *
     * @param q an input <code>Queue</code> of <code>String</code>
     */
    static void mystery(Queue<String> q) {
        Stack<String> stack = new Stack<String>();
        while (!q.isEmpty())
            stack.push(q.dequeue());
        while (!stack.isEmpty())
            q.enqueue(stack.pop());
    }
}
