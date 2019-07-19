import java.util.Arrays;
import java.util.stream.IntStream;

public class Matrix {
    public static double dot(double[] x, double[] y) {
        return IntStream
            .range(0, x.length)
            .mapToDouble(index -> x[index] * y[index])
            .sum();
    }

    public static double[][] transpose(double[][] a) {
        int aRows = a.length;
        int aCols = a[0].length;

        double[][] resultMatrix = new double[aCols][aRows];
        for (int rowIdx = 0; rowIdx < aRows; rowIdx++) {
            for (int colIdx = 0; colIdx < aCols; colIdx++) {
                resultMatrix[colIdx][rowIdx] = a[rowIdx][colIdx];
            }
        }
        return resultMatrix;
    }

    public static double[][] mult(double[][] a, double[][] b) {
        // Tranpose b to allow use of dot product (cols become rows)
        double[][] transposedB = transpose(b);
        double[][] result = new double[a.length][transposedB.length];
        for (int aRowIdx = 0; aRowIdx < a.length; aRowIdx++) {
            for (int bColIdx = 0; bColIdx < transposedB.length; bColIdx++) {
                result[aRowIdx][bColIdx] = dot(a[aRowIdx], transposedB[bColIdx]);
            }
        }
        return result;
    }

    /**
     * Matrix-vector multiplication
     *
     * Number of columns in matrix should match the length of the vector.
     *
     * Given a matrix A = [a b]
     *                    [c d]
     * And a vector V = [x]
     *                  [y]
     *
     * Result is a vector [ax + by]
     *                    [cx + dy]
     *
     * Reference:
     * https://reference.wolfram.com/language/tutorial/MultiplyingVectorsAndMatrices.html
     */
    public static double[] mult(double[][] a, double[] x) {
        // // Imperative
        // double[] result = new double[a.length];
        // for (int rowIdx = 0; rowIdx < a.length; rowIdx++) {
        //     result[rowIdx] = dot(a[rowIdx], x);
        // }
        // System.out.println("Imperative: " + Arrays.toString(result));

        // Functional
        return Arrays.stream(a)
            .mapToDouble(row -> dot(row, x))
            .toArray();
    }

    /**
     * Vector-matrix multiplication
     *
     * Number of rows in matrix should match the length of the vector.
     *
     * Given a matrix A = [a b]
     *                    [c d]
     * And a vector V = [x]
     *                  [y]
     *
     * Result is a vector [ax + cy]
     *                    [bx + dy]
     *
     * Reference:
     * https://reference.wolfram.com/language/tutorial/MultiplyingVectorsAndMatrices.html
     */
    public static double[] mult(double[] y, double[][] a) {
        // Imperative
        // double[][] transposed = transpose(a);
        // double[] result = new double[a[0].length];
        // for (int colIdx = 0; colIdx < a[0].length; colIdx++) {
        //     result[colIdx] = dot(transposed[colIdx], y);
        // }
        // System.out.println("Imperative: " + Arrays.toString(result));

        // Functional
        return Arrays.stream(transpose(a))  // Turn columns into rows
            .mapToDouble(col -> dot(col, y))
            .toArray();
    }

}
