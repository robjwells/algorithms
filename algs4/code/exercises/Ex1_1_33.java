import edu.princeton.cs.algs4.StdOut;

class Ex1_1_33 {
    public static void main(String[] args) {
        double dotResult = Matrix.dot(
            new double[] {1, 3, -5},
            new double[] {4, -2, -1}
        );
        StdOut.printf("Dot result, expected 3.0, got %s\n", dotResult);
    }
}
