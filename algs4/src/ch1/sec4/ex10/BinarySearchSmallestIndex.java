package ch1.sec4.ex10;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BinarySearchSmallestIndex {
    public static void main(String[] args) {
        int[] values = {1, 1, 2, 2, 3, 3, 3, 3, 3, 4, 4, 5, 6};
        StdOut.println(Arrays.toString(values));
        for (int i = 1; i <= 6; i++) {
            int index = binarySearch(values, i);
            StdOut.printf("Item %d found at index %d\n", i, index);
        }
    }

    public static int binarySearch(int[] values, int item) {
        return binarySearch(values, item, 0, values.length);
    }

    private static int binarySearch(int[] values, int item, int low, int high) {
        if (low > high) return -1;

        int middle = low + ((high - low) / 2);
        if (values[middle] < item) {
            return binarySearch(values, item, middle + 1, high);
        } else if (values[middle] > item) {
            return binarySearch(values, item, low, middle - 1);
        } else {
            // middle == item
            // Try to find an element equal to item but at a
            // lower index in the array
            int maybeSmallerIndex = binarySearch(values, item, low, middle - 1);
            return maybeSmallerIndex == -1 ? middle : maybeSmallerIndex;
        }
    }
}
