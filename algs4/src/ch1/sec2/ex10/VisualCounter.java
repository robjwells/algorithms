package ch1.sec2.ex10;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.stream.IntStream;

/**
 * Exercise 1.2.10
 *
 * <strong>Problem statement:</strong> Develop a class <code>VisualCounter</code> that allows both
 * increment and decrement operations. Take two arguments <code>n</code> and <code>max</code> in
 * the constructor, where <code>n</code> specifies the maximum number of operations and <code>max</code>
 * specifies the maximum absolute value for the counter. As a side effect, create a plot showing the
 */
public class VisualCounter {
    private final int maximumOperations;
    private final int maximumAbsoluteValue;
    private int count = 0;
    private int actionsPerformed = 0;

    public VisualCounter(int n, int max) {
        this.maximumOperations = n;
        this.maximumAbsoluteValue = Math.abs(max);

        int smallerLimit = Math.min(max, n);
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(-smallerLimit, smallerLimit);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.line(0, 0, n, 0);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            return;
        }
        int counterLimit = Integer.parseInt(args[0]);
        int rounds = Integer.parseInt(args[1]);

        VisualCounter counter = new VisualCounter(rounds, counterLimit);
        IntStream.range(0, rounds)
                .mapToObj(i -> StdRandom.bernoulli())
                .forEach(bool -> {
                    if (bool) {
                        counter.increment();
                    } else {
                        counter.decrement();
                    }
                });
    }

    public int getMaximumOperations() {
        return maximumOperations;
    }

    public int getActionsPerformed() {
        return actionsPerformed;
    }

    public int getCount() {
        return count;
    }

    public int getMaximumAbsoluteValue() {
        return maximumAbsoluteValue;
    }

    private void drawCount() {
        StdDraw.point(actionsPerformed, count);
    }

    /**
     * Increment the <code>VisualCounter</code>’s count, up to its <code>maximumAbsoluteValue</code>,
     * returning <code>true</code> if the count was incremented or <code>false</code> if it has
     * already reached the limit.
     *
     * @return <code>boolean</code> of whether the counter could be incremented up to the maximum value
     */
    public boolean increment() {
        if (count == maximumAbsoluteValue || actionsPerformed == maximumOperations) {
            return false;
        }
        count++;
        actionsPerformed++;
        drawCount();
        return true;
    }

    /**
     * Decrement the <code>VisualCounter</code>’s count, down to its <code>maximumAbsoluteValue</code>,
     * returning <code>true</code> if the count was decremented or <code>false</code> if it has
     * already reached the limit.
     *
     * @return <code>boolean</code> of whether the counter could be decremented up to the limit
     */
    public boolean decrement() {
        if (-count == maximumAbsoluteValue || actionsPerformed == maximumOperations) {
            return false;
        }
        count--;
        actionsPerformed++;
        drawCount();
        return true;
    }

}
