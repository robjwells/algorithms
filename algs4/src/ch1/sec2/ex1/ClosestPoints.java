package ch1.sec2.ex1;

import ch1.sec1.ex31.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import com.robjwells.util.Pair;

import java.util.stream.IntStream;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ClosestPoints {
    public static void main(String[] args) {
        int totalPoints = StdIn.readInt();
        Point2D[] points = IntStream.range(0, totalPoints)
                .mapToObj(i -> new Point2D(StdRandom.uniform(), StdRandom.uniform()))
                .toArray(Point2D[]::new);

        StdDraw.setScale(-0.1, 1.1);
        for (Point2D point : points) {
            StdDraw.filledCircle(point.getX(), point.getY(), 0.005);
        }
        Pair<Point2D> closestPoints = findTwoClosestPoints(points);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(
                closestPoints.first.getX(), closestPoints.first.getY(),
                closestPoints.second.getX(), closestPoints.second.getY()
        );
        StdDraw.filledCircle(closestPoints.first.getX(), closestPoints.first.getY(), 0.005);
        StdDraw.filledCircle(closestPoints.second.getX(), closestPoints.second.getY(), 0.005);
        StdDraw.show();
    }

    /**
     * Find the two closest points in the <code>points</code> array using a naive brute-force method.
     *
     * Complexity: O(n^2 / 2) (Very roughly)
     *
     * TODO: Solve this with an O(n log n) divide-and-conquer approach.
     *       https://en.wikipedia.org/wiki/Closest_pair_of_points_problem
     *
     * @param points An array of <code>Point2D</code> in the plane
     * @return A <code>Pair</code> of the two closest points
     */
    public static Pair<Point2D> findTwoClosestPoints(Point2D[] points) {
        Pair<Point2D> closestPair = Pair.of(points[0], points[1]);
        double closestDistance = euclideanDistance(closestPair.first, closestPair.second);
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = euclideanDistance(points[i], points[j]);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestPair = Pair.of(points[i], points[j]);
                }
            }
        }
        return closestPair;
    }

    static double euclideanDistance(Point2D a, Point2D b) {
        return sqrt(pow(b.getX() - a.getX(), 2) + pow(b.getY() - a.getY(), 2));
    }
}
