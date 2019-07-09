import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class Ex1_1_11 {
    public static void main(String[] args) {
        int limit = 10;
        boolean[][] bools = new boolean[limit][limit];
        for (int i = 0; i < limit * limit; i++) {
            bools[i / limit][i % limit] = StdRandom.bernoulli();
        }

        // Print header line
        StdOut.print("  ");
        for (int i = 0; i < limit; i++) {
            StdOut.print(i);
        }
        StdOut.println();

        // Print matrix
        String rowString;
        for (int i = 0; i < limit; i++) {
            boolean[] row = bools[i];
            rowString = i + " ";
            for (boolean item : row) {
                rowString += item ? "*" : " ";
            }
            StdOut.println(rowString);
        }
    }
}
