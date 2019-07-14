import java.util.Arrays;

class Ex1_1_27 {
    public static void main(String[] args) {
        Binomial b = new Binomial();
        int n = 100;
        int k = 50;

        b.callCount = 0;
        double p = 0.25;
        double result = b.betterBinomial(n, k, p);
        System.out.printf("r = %f\tn = %d\tk = %d\tc = %,d\n", result, n, k, b.callCount);
    }

    /**
     * The time complexity of this is (roughly) O(2^n). This is because at each
     * level the number of calls doubles, so we can think of it as a sum of the
     * powers of 2 (1 + 2 + 4 + 8 + 16 ...)
     *
     * This exercise was asked about on Stack Overflow:
     * https://stackoverflow.com/questions/16762101/reasoning-about-recursive-functions
     */
    static class Binomial {
        public long callCount = 0;
        double sentinel = -1.0;

        double binomial(int n, int k, double p) {
            // Tracing code
            callCount++;

            if ((n == 0) && (k == 0)) {
                return 1.0;
            } else if ((n < 0) || (k < 0)) {
                return 0.0;
            }
            return (1 - p) * binomial(n - 1, k, p) + p * binomial(n - 1, k - 1, p);
        }

        double betterBinomial(int n, int k, double p) {
            double[][] cache = new double[n + 1][k + 1];
            for (double[] row : cache) {
                Arrays.fill(row, sentinel);
            }
            double result = betterBinomial(cache, n, k, p);
            return result;
        }

        double betterBinomial(double[][] cache, int n, int k, double p) {
            // Tracing code
            callCount++;

            // Base cases
            if ((n == 0) && (k == 0)) {
                return 1.0;
            } else if ((n < 0) || (k < 0)) {
                return 0.0;
            }

            // Store computed value in cache if not present
            if (cache[n][k] == sentinel) {
                cache[n][k] = (
                    (1 - p) * betterBinomial(cache, n - 1, k, p) +
                         p  * betterBinomial(cache, n - 1, k - 1, p)
                );
                // for (double[] row : cache) {
                // for (double val : row) {
                // if (val != sentinel) {
                // System.out.printf("%5.2f ", val);
                // } else {
                // System.out.printf(" ", val);
                // }
                // }
                // System.out.println();
                // }
            }
            return cache[n][k];
        }
    }
}
