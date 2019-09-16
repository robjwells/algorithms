package ch1.sec1.ex30;

import edu.princeton.cs.algs4.*;

class Ex1_1_30 {
    public static void main(String[] args) {
        int n = 25;
        boolean[][] coprimeArray = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coprimeArray[i][j] = gcd(i + 1, j + 1) == 1;
            }
        }
        for (boolean[] row : coprimeArray) {
            for (boolean value : row) {
                StdOut.printf("%s ", value ? 'T' : ' ');
            }
            StdOut.println();
        }
    }

    static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }
        return gcd(q, p % q);
    }
}
