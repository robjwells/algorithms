import java.util.Arrays;

import edu.princeton.cs.algs4.*;

class Ex1_1_32 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double lo = Double.parseDouble(args[1]);
        double hi = Double.parseDouble(args[2]);

        int[] bins = new int[n];
        double binWidth = (hi - lo) / n;

        StdOut.printf("%s %s %s\n", n, lo, hi);

        while (!StdIn.isEmpty()) {
            double value = StdIn.readDouble();
            if (lo <= value && value <= hi) {
                Double floored = Math.floor((value - lo) / binWidth);
                bins[floored.intValue()] += 1;
            }
        }
        StdOut.println(Arrays.toString(bins));

        int height = Arrays.stream(bins).max().getAsInt();
        StdDraw.setCanvasSize(1024, 640);
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, height);
        // Draw canvas colour
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(n / 2, height / 2, n / 2, height / 2);
        // Draw histogram bars
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        for (int idx = 0; idx < n; idx++) {
            // StdDraw.line(idx + 0.5, 0, idx + 0.5, bins[idx]);
            StdDraw.filledRectangle(
                idx + 0.5,
                bins[idx] / 2,
                0.525,
                bins[idx] / 2
            );
        }
        StdDraw.show();
    }
}
