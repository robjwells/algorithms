package ch1.sec2.ex15;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ReadInts {
    public static int[] readInts(String fileName) {
        String fileContents = new In(fileName).readAll().trim();
        return Arrays.stream(fileContents.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) {
        String fn = "/Users/robjwells/Dropbox/projects/algorithms/algs4/algs4-data/2Kints.txt";
        StdOut.println(
                Arrays.equals(readInts(fn), new In(fn).readAllInts())
        );
    }
}
