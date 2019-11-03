package ch1.sec3.ex37;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.stream.IntStream;

public class Josephus {
    public static void main(String[] args) {
        int N;  // Total number of people in the circle
        int M;  // Mth person to remove
        try {
            N = Integer.parseInt(args[0]);
            M = Integer.parseInt(args[1]);
        } catch (IndexOutOfBoundsException exc) {
            System.err.println(
                    "Too few arguments given. Please supply N (total people) and M (Mth person to remove)."
            );
            return;
        } catch (NumberFormatException exc) {
            System.err.println("Please supply N and M and integers.");
            return;
        } catch (Exception exc) {
            // Should be unreachable.
            exc.printStackTrace();
            return;
        }

        Queue<Integer> circle = new Queue<>();
        IntStream.range(0, N).forEach(circle::enqueue);

        while (!circle.isEmpty()) {
            // Skip the first M - 1 people, by removing and adding them at the back.
            IntStream.range(0, M - 1).forEach(m -> circle.enqueue(circle.dequeue()));
            // Remove the Mth person
            StdOut.print(circle.dequeue());
            if (!circle.isEmpty()) StdOut.print(" ");
        }
    }
}
