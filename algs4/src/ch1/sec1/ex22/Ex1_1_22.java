package ch1.sec1.ex22;

import java.util.Arrays;

import edu.princeton.cs.algs4.*;

class Ex1_1_22 {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.indexOf(whitelist, key) == -1) {
                StdOut.println(key);
            }
        }
    }

    static class BinarySearch {
        static int indexOf(int[] a, int key) {
            return indexOf(a, key, 0, a.length - 1, 0);
        }

        static int indexOf(int[] a, int key, int lo, int hi, int recursionDepth) {
            // Tracing code
            String tracePrefix = "> " + "\t".repeat(recursionDepth);
            StdOut.printf("%s%d,%d\n", tracePrefix, lo, hi);

            if (lo > hi) {
                return -1;
            }
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                return indexOf(a, key, lo, mid - 1, recursionDepth + 1);
            } else if (key > a[mid]) {
                return indexOf(a, key, mid + 1, hi, recursionDepth + 1);
            } else {
                // Found key
                return mid;
            }
        }
    }
}
