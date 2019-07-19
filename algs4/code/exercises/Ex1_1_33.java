import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

class Ex1_1_33 {
    public static void main(String[] args) {
        double dotResult = Matrix.dot(
            new double[] {1, 3, -5},
            new double[] {4, -2, -1}
        );
        StdOut.printf("Dot result, expected 3.0, got %s\n", dotResult);

        double[][] a = new double[][] {new double[] {1, 2, 3}, new double[] {4, 5, 6}};
        StdOut.println("Matrix `a`:");
        for (double[] row : a) {
            StdOut.println(Arrays.toString(row));
        }

        double[][] transposed = Matrix.transpose(a);
        StdOut.println("Matrix `a` after transposition:");
        for (double[] row : transposed) {
            StdOut.println(Arrays.toString(row));
        }

        double[][] multResult = Matrix.mult(a, transposed);
        StdOut.println("Matrix `a` multiplied by its transposition");
        for (double[] row : multResult) {
            StdOut.println(Arrays.toString(row));
        }

        double[][] threeByTwoMatrix = new double[][] { new double[] {1, 2}, new double[] {3, 4}, new double[] {5, 6} };
        StdOut.println("Matrix `3x2`:");
        for (double[] row : threeByTwoMatrix) {
            StdOut.println(Arrays.toString(row));
        }
        double[][] twoByFourMatrix = new double[][] { new double[] {1, 2, 3, 4}, new double[] {5, 6, 7, 8} };
        StdOut.println("Matrix `2x4`:");
        for (double[] row : twoByFourMatrix) {
            StdOut.println(Arrays.toString(row));
        }
        double[][] unequalMultResult = Matrix.mult(threeByTwoMatrix, twoByFourMatrix);
        StdOut.println("Matrix `3x2` * `2x4`:");
        for (double[] row : unequalMultResult) {
            StdOut.println(Arrays.toString(row));
        }
    }
}
