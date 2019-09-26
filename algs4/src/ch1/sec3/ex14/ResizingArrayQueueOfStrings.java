package ch1.sec3.ex14;

import edu.princeton.cs.algs4.StdOut;

/**
 * An array-backed queue of strings with a fixed capacity of 10.
 * <p>
 * The backing array is used as a circular buffer.
 */
public class ResizingArrayQueueOfStrings {
    private String[] queue = new String[10];
    private int head;
    private int tail;
    private int size;

    public static void main(String[] args) {
        ResizingArrayQueueOfStrings q = new ResizingArrayQueueOfStrings();
        for (int i = 0; i < 10; i++) {
            q.enqueue(String.valueOf(i));
        }
        for (int j = 10; j < 15; j++) {
            q.dequeue();
            q.enqueue(String.valueOf(j));
        }
        for (int k = 0; k < 10; k++) {
            StdOut.println(q.dequeue());
        }
    }

    public void enqueue(String item) {
        if (size == queue.length) {
            throw new IllegalStateException("Queue is full; could not enqueue.");
        }
        queue[tail] = item;
        tail = (tail + 1) % queue.length;
        size++;
    }

    public String dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty; could not dequeue.");
        }
        String value = queue[head];
        queue[head] = null;
        head = (head + 1) % queue.length;
        size--;
        return value;
    }
}
