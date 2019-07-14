/**
 * This works by swapping the values of a, b and c, using a temp variable t.
 *
 * Consider this trace:
 *   a   b   c   Value moved
 * -------------------------
 *   3   2   1   [  t]   // Starting position: (a, b, c) = (3, 2, 1)
 *   2   3   1   [  3]   // a and b are swapped
 *   1   3   2   [  2]   // a (the original b) and c are swapped
 *   1   2   3   [  3]   // b (the original a) and c (the original b) are swapped
 */
class Ex1_1_26 {
    public static void main(String[] args) {
        int a, b, c, t;
        t = Integer.MIN_VALUE;
        a = Integer.parseInt(args[0]);
        b = Integer.parseInt(args[1]);
        c = Integer.parseInt(args[2]);

        System.out.printf("%3d\t%3d\t%3d\t[%3d]\n", a, b, c, t);
        if (a > b) {
            t = a; a = b; b = t;
        }
        System.out.printf("%3d\t%3d\t%3d\t[%3d]\n", a, b, c, t);
        if (a > c) {
            t = a; a = c; c = t;
        }
        System.out.printf("%3d\t%3d\t%3d\t[%3d]\n", a, b, c, t);
        if (b > c) {
            t = b; b = c; c = t;
        }
        System.out.printf("%3d\t%3d\t%3d\t[%3d]\n", a, b, c, t);
    }
}
