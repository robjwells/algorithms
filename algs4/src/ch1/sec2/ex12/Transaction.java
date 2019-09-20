package ch1.sec2.ex12;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;

public class Transaction {
    private final String name;
    private final Date date;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
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
        this.date = new Date(parts[1]); // Throws IllegalArgumentException if invalid
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

    public Date when() {
        return date;
    }

    public double amount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", name, date, amount);
    }
}
