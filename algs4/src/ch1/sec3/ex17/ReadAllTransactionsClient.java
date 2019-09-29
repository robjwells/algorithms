package ch1.sec3.ex17;

import ch1.sec2.ex13.Transaction;
import edu.princeton.cs.algs4.StdOut;

public class ReadAllTransactionsClient {
    public static void main(String[] args) {
        for (Transaction t : Transaction.readAllTransactions()) {
            StdOut.println(t);
        }
    }
}
