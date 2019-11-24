package ch1.sec4.ex08;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CountPairs {
    public static int countPairs(int[] values) {
        if (values.length < 2) return 0;
        Arrays.sort(values);
        int count = 0;
        int currentFrequency = 1;
        for (int index = 1; index < values.length; index++) {
            if (values[index - 1] == values [index]) {
                currentFrequency++;
            } else {
                // New number, so increment count by the number of matching pairs.
                // Matching pairs calculated by the formula n(n - 1) / 2.
                // Each item matches with each other item, except itself,
                // and we don’t double-count matches (a matches b, b matches a).
                count += (currentFrequency * (currentFrequency - 1)) / 2;
                currentFrequency = 1;
            }
        }
        // Handle last group of items
        if (currentFrequency > 1) {
            count += (currentFrequency * (currentFrequency - 1)) / 2;
        }
        return count;
    }

    public static void main(String[] args) {
        // In in = new In(args[0]);
        // StdOut.println(in.readAllInts());
        StdOut.println(countPairs(new int[] {1, 1, 1, 4, 5, 5}));
        StdOut.println(countPairs(new int[] {1, 2, 3, 4, 5, 6}));
    }
}
