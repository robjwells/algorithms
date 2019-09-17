package ch1.sec2.ex2;

import com.robjwells.util.Pair;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Exercise 1.2.2
 * <p>
 * Write an <code>Interval1D</code> client that takes an <code>int</code> value <em>N</em> as
 * command-line argument, reads <em>N</em> intervals (each defined by a pair of <code>double</code>
 * values) from standard input, and prints all pairs that intersect.
 */
public class IntersectingIntervals {
    public static void main(String[] args) {
        Interval1D[] intervals = readIntervals();
        List<Pair<Interval1D>> intersecting = findIntersectingIntervals(intervals);
        intersecting.forEach(pair -> StdOut.printf("%s\t%s\n", pair.first, pair.second));
    }

    /**
     * Read <em>n</em> pairs of <code>double</code>s from <code>stdin</code> and return corresponding
     * instances of <code>Interval1D</code>.
     *
     * @return an array of <code>Interval1D</code> created from pairs of doubles on <code>stdin</code>
     */
    static Interval1D[] readIntervals() {
        int n = StdIn.readInt();
        return IntStream.range(0, n)
                .mapToObj(i -> new Interval1D(StdIn.readDouble(), StdIn.readDouble()))
                .toArray(Interval1D[]::new);
    }

    /**
     * Find all intervals in the <code>intervals</code> array that intersect.
     * <p>
     * This uses a naive brute-force search that runs in (very roughly) O(n^2).
     * <p>
     * TODO: Implement the intersection search using an interval tree for
     * O(n log n) creation and roughly O(log n) lookup.
     * https://en.wikipedia.org/wiki/Interval_tree
     * <p>
     * Intersection is defined as two intervals overlapping for some of their length,
     * not necessarily all of it.
     *
     * @param intervals the intervals to check for intersection
     * @return <code>Pair</code>s of <code>Interval1D</code> that intersect
     */
    static List<Pair<Interval1D>> findIntersectingIntervals(Interval1D[] intervals) {
        // Create a copy of the input array as we need to use a mutating sort
        Interval1D[] sorted = Arrays.copyOf(intervals, intervals.length);
        Arrays.sort(sorted, Interval1D.MIN_ENDPOINT_ORDER);

        // Initialise our return list
        List<Pair<Interval1D>> intersecting = new ArrayList<>(intervals.length);

        for (int first = 0; first < sorted.length - 1; first++) {
            for (int second = first + 1; second < sorted.length; second++) {
                if (sorted[first].intersects(sorted[second])) {
                    intersecting.add(Pair.of(sorted[first], sorted[second]));
                }
            }
        }

        return intersecting;
    }
}
