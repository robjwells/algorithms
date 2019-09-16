package ch1.sec1.ex21;

import edu.princeton.cs.algs4.*;

class Ex1_1_21 {
    public static void main(String[] args) {
        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            int first = StdIn.readInt();
            int second = StdIn.readInt();
            StdOut.printf(
                "%s\t%d\t%d\t%.3f\n",
                name,
                first,
                second,
                // Avoid divide-by-zero error (gives Infinity)
                Float.intBitsToFloat(first) / Float.intBitsToFloat(second)
            );
        }
    }
}
