package ch1.sec3.ex41;

import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

public class QueueCopy<Item> extends Queue<Item> {
    /**
     * Create a copy of <tt>other</tt>.
     *
     * This approach assumes {@link Queue} doesn’t implement {@link Iterator},
     * in which case you could just iterate over the other queue and enqueue
     * each item onto the new queue.
     * @param other the queue to copy from
     */
    public QueueCopy(Queue<Item> other) {
        int originalSize = other.size();
        for (int x = 0; x < originalSize; x++) {
            Item item = other.dequeue();
            other.enqueue(item);
            enqueue(item);
        }
        // Simpler using Iterator:
        // for (Item item : other) {
        //     enqueue(item);
        // }
    }
}
