package ch1.sec2.ex13;

import ch1.sec2.ex11.SmartDate;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

public class Transaction {
    private final String name;
    private final SmartDate date;
    private final double amount;

    public Transaction(String who, SmartDate when, double amount) {
        if (who == null || when == null) {
            throw new IllegalArgumentException("who and when cannot be null");
        }
        this.name = who;
        this.date = when;
        this.amount = amount;
    }

    public Transaction(String transaction) {
        String[] parts = transaction.split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Transaction does not match format of 'Name mm/dd/yyyy amount'"
            );
        }

        this.name = parts[0];
        this.date = new SmartDate(parts[1]); // Throws IllegalArgumentException if invalid
        try {
            this.amount = Double.parseDouble(parts[2]);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(
                    "Could not parse given amount in transaction as a double",
                    nfe
            );
        }
    }

    public static void main(String[] args) {
        StdOut.println(
                new Transaction("Marx 5/5/1818 18.83") // Check the Transaction round-trips
        );
    }

    public String who() {
        return name;
    }

    public SmartDate when() {
        return date;
    }

    public double amount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", name, date, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        // This is auto-generated, but I’m not clear on whether Double.compare does anything
        // particular to make the double comparison more robust than just `amount == that.amount`
        return Double.compare(that.amount, amount) == 0 &&
                name.equals(that.name) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, amount);
    }

    /**
     * Ex 1.3.17: Read all transactions from standard input.
     */
    public static Transaction[] readAllTransactions() {
        Queue<String> queue = new Queue<>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readLine());
        }
        Transaction[] transactions = new Transaction[queue.size()];
        for (int index = 0; index < transactions.length; index++) {
            transactions[index] = new Transaction(queue.dequeue());
        }
        return transactions;
    }
}
