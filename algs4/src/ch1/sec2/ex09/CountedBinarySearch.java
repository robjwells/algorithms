package ch1.sec2.ex09;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Exercise 1.2.9
 *
 * <strong>Problem statement:</strong> Instrument <code>BinarySearch</code> (page 47) to use a <code>Counter</code> to count the
 * total number of keys examined during all searches and then print the total after all
 * searches are complete.
 *
 * <strong>Commentary (RW):</strong> This is just code copied from the <code>BinarySearch</code>
 * class in the <code>algs4.jar</code>, but with appropriate <code>Counter</code> calls
 * inserted into {@link #main(String[])} and {@link #indexOf(int[], int, Counter)}, and with
 * the total tally printed at the end of <code>main</code>.
 */
public class CountedBinarySearch {
    /**
     * Returns the index of the specified key in the specified array.
     * <p>
     * Instrumented with a <code>Counter</code> to track the number of keys examined.
     *
     * @param a       the array of integers, must be sorted in ascending order
     * @param key     the search key
     * @param counter a <code>Counter</code> object to track the number of keys examined
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key, Counter counter) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            counter.increment();
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * Reads in a sequence of integers from the whitelist file, specified as
     * a command-line argument; reads in integers from standard input;
     * prints to standard output those integers that do <em>not</em> appear in the file.
     * <p>
     * Also prints to standard output the total number of keys examined during all
     * binary searches conducted.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        // read the integers from a file
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        Counter counter = new Counter("CountedBinarySearch");

        // read integer key from standard input; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (indexOf(whitelist, key, counter) == -1)
                StdOut.println(key);
        }

        StdOut.printf("Total number of keys examined in all searches: %s\n", counter.tally());
    }
}
