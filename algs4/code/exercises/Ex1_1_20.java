import edu.princeton.cs.algs4.*;

class Ex1_1_20 {
    public static void main(String[] args) {
        long n = StdIn.readLong();
        long factN = factorial(n);
        StdOut.printf("ln(%d!) == ln(%d) == %f\n", n, factN, Math.log(factN));
    }

    public static long factorial(long n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
