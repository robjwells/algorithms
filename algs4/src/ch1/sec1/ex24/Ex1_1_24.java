package ch1.sec1.ex24;

/**
 * Sequence of p and q values for gcd(105, 24):
 * 105, 24
 *  24,  9
 *   9,  6
 *   6,  3
 *   3,  0
 *
 * GCD of 1,111,111 and 1,234,567: 1
 */
class Ex1_1_24 {
    public static void main(String[] args) {
        System.out.println(
            Euclid.gcd(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1])
            )
        );
    }

    static class Euclid {
        static int gcd(int p, int q) {
            // Tracing code
            System.out.printf("gcd(p=%d, q=%d)\n", p, q);

            if (q == 0) {
                return p;
            }
            int remainder = p % q;
            return gcd(q, remainder);
        }
    }
}
