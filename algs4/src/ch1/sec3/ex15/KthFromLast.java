package ch1.sec3.ex15;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 1.3.15: Write a <tt>Queue</tt> client that takes a command-line argument
 * <tt>k</tt> and prints the <tt>k</tt>th from the last string found on standard
 * input (assuming that standard input has <tt>k</tt> or more strings).
 *
 * Really this would make more sense as a <tt>Stack</tt> client as we’re focused on
 * the end (ie most recent) of the input.
 */
public class KthFromLast {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Queue<String> q = new Queue<>();
        for (String s : StdIn.readAllStrings()) {
            q.enqueue(s);
        }

        assert k <= q.size() : "Argument k is too big for the given input";
        for (int remaining = q.size() - k; remaining > 0; remaining--) {
            q.dequeue();
        }
        StdOut.println(q.dequeue());
    }
}
