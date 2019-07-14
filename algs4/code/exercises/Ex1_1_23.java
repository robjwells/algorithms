import java.util.Arrays;

import edu.princeton.cs.algs4.*;

class Ex1_1_23 {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);

        boolean printAbsent = args[1].equals("+");

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            int index = indexOf(whitelist, key);
            if ((index == -1 && printAbsent) || (index != -1 && !printAbsent)) {
                StdOut.println(key);
            }
        }
    }

    static int indexOf(int[] a, int key) {
        return indexOf(a, key, 0, a.length - 1, 0);
    }

    static int indexOf(int[] a, int key, int lo, int hi, int recursionDepth) {
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
