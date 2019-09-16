package ch1.sec1.ex28;

import java.util.Arrays;

import edu.princeton.cs.algs4.*;

class Ex1_1_28 {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] rawWhitelist = in.readAllInts();
        Arrays.sort(rawWhitelist);
        int[] whitelist = removeDuplicates(rawWhitelist);

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            int index = BinarySearch.indexOf(whitelist, key);
            if (index == -1) {
                StdOut.println(key);
            }
        }
    }

    static int[] removeDuplicates(int[] array) {
        int[] uniquesOnly = new int[array.length];
        int cursor = 0;
        uniquesOnly[0] = array[0];
        for (int index = 0; index < array.length; index++) {
            if (array[index] != uniquesOnly[cursor]) {
                cursor++;
                uniquesOnly[cursor] = array[index];
            }
        }
        int numUniques = cursor + 1;
        return Arrays.copyOf(uniquesOnly, numUniques);
    }

    static class BinarySearch {
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
}
