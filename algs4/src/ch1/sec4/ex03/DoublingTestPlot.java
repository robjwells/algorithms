package ch1.sec4.ex03;

import edu.princeton.cs.algs4.*;

import java.awt.*;
import java.util.ArrayList;

public class DoublingTestPlot {
    public static double timeTrial(int n) {
        int MAX = 1_000_000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        int count = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    static ArrayList<Point2D> points = new ArrayList<>();
    static double xMaxScale = 10;
    static double yMaxScale = 10;

    static void initialiseCanvas() {
        StdDraw.setCanvasSize(300, 300);
        StdDraw.setXscale(0, xMaxScale);
        StdDraw.setYscale(-5, yMaxScale);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
    }

    static void drawPointOnCanvas(Point2D point) {
        StdDraw.filledCircle(point.x(), point.y(), 0.25);
    }

    static void drawLineOnCanvas(Point2D start, Point2D end) {
        Color savedColour = StdDraw.getPenColor();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(start.x(), start.y(), end.x(), end.y());
        StdDraw.setPenColor(savedColour);
    }

    static void redrawCanvas() {
        StdDraw.clear();
        StdDraw.setXscale(0, xMaxScale);
        StdDraw.setYscale(-5, yMaxScale);
        if (points.size() > 1) {
            for (int index = 1; index < points.size(); index++) {
                Point2D previous = points.get(index - 1);
                Point2D current = points.get(index);
                drawLineOnCanvas(previous, current);
            }
        }
        for (Point2D p : points) {
            drawPointOnCanvas(p);
        }
    }

    static void rescaleIfNeeded(Point2D p) {
        boolean xChange = p.x() >= xMaxScale;
        boolean yChange = p.y() >= yMaxScale;

        if (xChange || yChange) {
            if (xChange) {
                xMaxScale *= 2;
            }
            if (yChange) {
                yMaxScale *= 2;
            }
            redrawCanvas();
        }
    }

    static void addPointToCanvas(double x, double y) {
        Point2D p = new Point2D(x, y);
        rescaleIfNeeded(p);
        drawPointOnCanvas(p);
        if (points.size() != 0) {
            drawLineOnCanvas(points.get(points.size() - 1), p);
        }
        points.add(p);
    }

    public static void main(String[] args) {
        initialiseCanvas();
        StdDraw.show();
        for (int n = 250; n < 16_000; n *= 2) {
            double time = timeTrial(n);
            StdOut.printf("%7d %7.1f\n", n, time);
            StdOut.printf("%f %f", Math.log(n), Math.log(time));
            addPointToCanvas(Math.log(n), Math.log(time));
        }
    }
}
