import java.util.Arrays;
import edu.princeton.cs.algs4.*;

/**
 * The question asks what is the largest fibonacci number that
 * naive recursive answer can compute in one hour, with the
 * answer on my laptop of:
 *      fib(56) = 225851433717
 *
 * (Bear in mind, this is 56 after previously and separately
 * computing 0...55)
 *
 * Using an array as a cache for previously computed values,
 * my laptop computes the fibonacci numbers from 0 to 89 in
 * about 0.25 seconds. (!)
 */
class Ex1_1_19 {
    public static void main(String[] args) {
        int limit = 90;
        Fibonacci f = new Fibonacci(limit);
        for (int n = 0; n < limit; n++) {
            StdOut.println(n + " " + f.fibonacci(n));
        }
    }

    public static class Fibonacci {
        private long[] fibs;
        private long sentinel = -1;

        Fibonacci(int limit) {
            fibs = new long[limit];
            Arrays.fill(fibs, sentinel);
            fibs[0] = 0;
            fibs[1] = 1;
        }

        public long fibonacci(int n) {
            if (fibs[n] == sentinel) {
                fibs[n] = fibonacci(n - 1) + fibonacci(n - 2);
            }
            return fibs[n];
        }
    }
}
