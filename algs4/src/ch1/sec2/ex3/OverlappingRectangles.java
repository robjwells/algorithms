package ch1.sec2.ex3;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;
import java.util.stream.IntStream;

public class OverlappingRectangles {
    public static void main(String[] args) {
//        int N = StdIn.readInt();
//        int min = StdIn.readInt();
//        int max = StdIn.readInt();
        Interval2D[] rects = IntStream.range(0, 10)
                .mapToObj(i -> randomRectInUnitSquare(0.25, 0.75))
                .toArray(Interval2D[]::new);
        drawRects(rects);
    }

    static Interval2D randomRectInUnitSquare(double minExtent, double maxExtent) {
        double width = StdRandom.uniform(minExtent, maxExtent);
        double height = StdRandom.uniform(minExtent, maxExtent);
        double xOrigin = StdRandom.uniform(0, 1.0 - width);
        double yOrigin = StdRandom.uniform(0, 1.0 - height);
        Interval1D xInterval = new Interval1D(xOrigin, xOrigin + width);
        Interval1D yInterval = new Interval1D(yOrigin, yOrigin + height);
        return new Interval2D(xInterval, yInterval);
    }

    static void drawRects(Interval2D[] rects) {
        StdDraw.setScale(-0.1, 1.1);
        Arrays.stream(rects).forEach(Interval2D::draw);
        StdDraw.show();
    }
}
