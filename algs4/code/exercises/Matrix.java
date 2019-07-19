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
        double[][] result = new double[a.length][a.length];
        for (int aRowIdx = 0; aRowIdx < a.length; aRowIdx++) {
            for (int bColIdx = 0; bColIdx < transposedB.length; bColIdx++) {
                result[aRowIdx][bColIdx] = dot(a[aRowIdx], transposedB[bColIdx]);
            }
        }
        return result;
    }
}
