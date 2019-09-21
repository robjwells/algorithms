package ch1.sec2.ex03;

import com.robjwells.util.Pair;
import edu.princeton.cs.algs4.*;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Exercise 1.2.3
 * <strong>Problem statement:</strong> Write an Interval2D client that takes command-line arguments
 * <code>N</code>, <code>min</code>, and <code>max</code> and generates <code>N</code> random
 * 2D intervals whose width and height are uniformly distributed between <code>min</code>
 * and <code>max</code> in the unit square.
 * <p>
 * Draw them on <code>StdDraw</code> and print the number of pairs of intervals that intersect
 * and the number of intervals that are contained in one another.
 *
 * <strong>Commentary (RW):</strong> The "contains" part of this exercise would really be
 * significantly more elegant if <code>Interval2D</code> had a "contains" method (which
 * would take another <code>Interval2D</code>). As it is, having to store what should be
 * temporary <code>Interval1D</code> pairs, it’s a bit of a mess.
 * <p>
 * TODO: Subclass Interval2D and add a "contains" method.
 */
public class OverlappingRectangles {
    // Global map of rectangles to their horizontal & vertical 1D intervals.
    // Required to calculate containment.
    private static Map<Interval2D, Pair<Interval1D>> intervalMap = new HashMap<>();

    public static void main(String[] args) {
        int N = StdIn.readInt();
        double min = StdIn.readDouble();
        double max = StdIn.readDouble();
        Interval2D[] rects = IntStream.range(0, N)
                .mapToObj(i -> randomRectInUnitSquare(min, max))
                .toArray(Interval2D[]::new);
        drawRects(rects);

        int totalIntersectingIntervals = findIntersectingRects(rects).size();
        int totalContainedRects = findContainedRects(rects)
                .values()
                .stream()
                .mapToInt(ArrayList::size)
                .sum();
        StdOut.printf("Total Interval2D pairs that intersect: %s\n", totalIntersectingIntervals);
        StdOut.printf("Total Interval2Ds that fully contain another: %s\n", totalContainedRects);
    }

    /**
     * Return an <code>Interval2D</code> at a random position within the unit square with side
     * length in [minExtent, maxExtent), ensuring the interval is fully within the
     * unit square.
     *
     * @param minExtent the minimum length of the rectangle’s sides
     * @param maxExtent the maximum length of the rectangle’s sides
     * @return an <code>Interval2D</code> within the unit square at a random position
     */
    static Interval2D randomRectInUnitSquare(double minExtent, double maxExtent) {
        double width = StdRandom.uniform(minExtent, maxExtent);
        double height = StdRandom.uniform(minExtent, maxExtent);
        double xOrigin = StdRandom.uniform(0, 1.0 - width);
        double yOrigin = StdRandom.uniform(0, 1.0 - height);
        Interval1D xInterval = new Interval1D(xOrigin, xOrigin + width);
        Interval1D yInterval = new Interval1D(yOrigin, yOrigin + height);
        Interval2D rect = new Interval2D(xInterval, yInterval);
        // Storing the Interval1Ds in a global map feels like a hack
        // put Interval2D offers no `contains`, its members are private,
        // and this is the approach used by Rene Argento.
        intervalMap.put(rect, Pair.of(xInterval, yInterval));
        return rect;
    }

    /**
     * Draw each <code>Interval2D</code> in <code>rects</code> within the unit square as an
     * unfilled rectangle.
     *
     * @param rects the <code>Interval2D</code> instances to draw
     */
    static void drawRects(Interval2D[] rects) {
        StdDraw.setScale(-0.1, 1.1);
        Arrays.stream(rects).forEach(Interval2D::draw);
        StdDraw.show();
    }

    /**
     * Return a list of <code>Interval2D</code> pairs which intersect at some point
     *
     * @param rects the full array of <code>Interval2D</code> instances to consider
     * @return a list of unique <code>Interval2D</code> pairs that intersect
     */
    static List<Pair<Interval2D>> findIntersectingRects(Interval2D[] rects) {
        ArrayList<Pair<Interval2D>> intersecting = new ArrayList<>(rects.length);
        for (int first = 0; first < rects.length; first++) {
            for (int second = 0; second < rects.length; second++) {
                if (first == second) {
                    continue;
                }
                if (rects[first].intersects(rects[second])) {
                    intersecting.add(Pair.of(rects[first], rects[second]));
                }
            }
        }
        return intersecting;
    }

    static Map<Interval2D, ArrayList<Interval2D>> findContainedRects(Interval2D[] rects) {
        Map<Interval2D, ArrayList<Interval2D>> containing = new HashMap<>(rects.length);
        for (int first = 0; first < rects.length - 1; first++) {
            containing.put(rects[first], new ArrayList<>());
            for (int second = first + 1; second < rects.length; second++) {
                if (rectContainsRect(rects[first], rects[second])) {
                    containing.get(rects[first]).add(rects[second]);
                }
            }
        }
        return containing;
    }

    static boolean rectContainsRect(Interval2D first, Interval2D second) {
        Pair<Interval1D> firstIntervals = intervalMap.get(first);
        Pair<Interval1D> secondIntervals = intervalMap.get(second);
        return (firstIntervals.first.min() <= secondIntervals.first.min() &&
                firstIntervals.first.max() >= secondIntervals.first.max() &&
                firstIntervals.second.min() <= secondIntervals.second.min() &&
                firstIntervals.second.max() >= secondIntervals.second.max()
        );
    }
}
