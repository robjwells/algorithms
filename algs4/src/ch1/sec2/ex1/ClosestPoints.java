package ch1.sec2.ex1;

import ch1.sec1.ex31.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;
import java.util.stream.IntStream;

public class ClosestPoints {
    public static void main(String[] args) {
        int totalPoints = StdIn.readInt();
        Random random = new Random();
        Point2D[] points = IntStream.range(0, totalPoints)
                .mapToObj(i -> new Point2D(StdRandom.uniform(), StdRandom.uniform()))
                .toArray(Point2D[]::new);

        StdDraw.setScale(-0.1, 1.1);
        for (Point2D point : points) {
            StdDraw.filledCircle(point.getX(), point.getY(), 0.005);
        }
        StdDraw.show();
    }
}
