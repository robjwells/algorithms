import java.util.stream.IntStream;

public class Matrix {
    public static double dot(double[] x, double[] y) {
        return IntStream
            .range(0, x.length)
            .mapToDouble(index -> x[index] * y[index])
            .sum();
    }
}
