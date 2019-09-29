package ch1.sec3.ex16;

import ch1.sec2.ex11.SmartDate;
import edu.princeton.cs.algs4.StdOut;

public class ReadAllDatesClient {
    public static void main(String[] args) {
        for (SmartDate date : SmartDate.readAllDates()) {
            StdOut.println(date);
        }
    }
}
