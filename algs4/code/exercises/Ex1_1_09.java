import edu.princeton.cs.algs4.StdOut;

class Ex1_1_9 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        String s = "";
        while (n > 0) {
            s = (n % 2) + s;
            n /= 2;
        }
        StdOut.println(s);
    }
}
