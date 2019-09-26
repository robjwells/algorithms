package ch1.sec3.ex14;

import edu.princeton.cs.algs4.StdOut;

/**
 * An array-backed queue of strings with a fixed capacity of 10.
 * <p>
 * The backing array is used as a circular buffer.
 */
public class ResizingArrayQueueOfStrings {
    private static final int MIN_SIZE = 4;

    private String[] queue = new String[MIN_SIZE];
    private int head;
    private int tail;
    private int size;

    public static void main(String[] args) {
        ResizingArrayQueueOfStrings q = new ResizingArrayQueueOfStrings();
        for (int i = 0; i < 8; i++) {
            q.enqueue(String.valueOf(i));
        }

        // Clear and then fill space to check circular buffer works as intended
        q.dequeue(); // 0
        q.dequeue(); // 1
        q.enqueue(String.valueOf(9));
        q.enqueue(String.valueOf(10));
        q.enqueue(String.valueOf(11));

        while (q.size() != 0) {
            StdOut.println(q.dequeue());
        }
    }

    public void enqueue(String item) {
        // Resize if full
        if (size == queue.length) {
            resize(2 * queue.length);
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

        // Resize if too empty
        if (size == queue.length / 4 && queue.length / 2 >= MIN_SIZE) {
            resize(queue.length / 2);
        }

        return value;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        String[] temp = new String[capacity];
        if (size == 0) {
            // Don't attempt to copy as queue is empty
        } else if (tail > head) {
            // Tail after head, so just copy the occupied range
            System.arraycopy(queue, head, temp, 0, size);
        } else {
            // Tail before or at head, but the queue is not empty, so copy head->end then
            // start->tail into the new array so that it runs head->tail without wrapping
            System.arraycopy(queue, head, temp, 0, queue.length - head);
            System.arraycopy(queue, 0, temp, queue.length - head, tail);
        }
        queue = temp;
        head = 0;
        tail = size;
    }
}
