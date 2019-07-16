import edu.princeton.cs.algs4.*;

class Ex1_1_31 {
    public static void main(String[] args) {
        assert args.length == 2 : "Incorrect number of command-line arguments.";
        int N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);
        Point2D[] points = new Point2D[N];
        for (int index = 0; index < N; index++) {
            // This doesn’t work because cos and sin expect their
            // argument to be in radians, not degrees:
            // Point2D(Math.cos(index * angle), Math.sin(index * // angle)

            // This works because (2 * pi * index / N) gives the angle portion in radians
            points[index] = new Point2D(
                Math.cos(2 * Math.PI * index / N),
                Math.sin(2 * Math.PI * index / N)
            );
        }

        StdDraw.setCanvasSize(640, 640);
        StdDraw.setScale(-1.1, 1.1);

        // Draw points
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        for (Point2D point : points) {
            StdDraw.filledCircle(point.getX(), point.getY(), 0.05 / 2.0);
        }

        // Draw connections between points with probability p
        StdDraw.setPenColor(StdDraw.GRAY);
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (StdRandom.bernoulli(p)) {
                    StdDraw.line(
                        points[i].getX(), points[i].getY(),
                        points[j].getX(), points[j].getY()
                    );
                }
            }
        }
        StdDraw.show();
    }

    static class Point2D {
        private final double x;
        private final double y;

        Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public String toString() {
            return String.format("Point2D [x=%s, y=%s]", x, y);
        }
    }
}
