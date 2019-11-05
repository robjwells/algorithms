package ch1.sec3.ex39;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class RingBuffer<Item> {
    private int size;
    private Item[] data;
    private int readCursor;
    private int writeCursor;

    public static void main(String[] args) {
        RingBuffer<Integer> rb = new RingBuffer<>(4);
        IntStream.range(1, 5).forEach(rb::enqueue);
        System.out.println(rb);
        IntStream.range(6, 17).forEach(n -> {
            int r = rb.dequeue();
            rb.enqueue(n);
            System.out.println(rb);
        });

        rb.dequeue();
        rb.dequeue();
        rb.dequeue();
        System.out.println(rb);

        for (int n = 0; n < 3; n++) {
            rb.enqueue(n);
            System.out.println(rb);
        }
        rb.dequeue();
        System.out.println(rb);
    }

    RingBuffer(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Buffer size must be greater than 0.");
        }
        //noinspection unchecked
        data = (Item[]) new Object[size];
    }

    RingBuffer() {
        this(10);
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == data.length;
    }

    int size() {
        return size;
    }

    int capacity() {
        return data.length;
    }

    void enqueue(Item item) {
        if (size == data.length) {
            throw new BufferFullException();
        }
        size++;
        data[writeCursor] = item;
        writeCursor = (writeCursor + 1) % data.length;
    }

    Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        Item toReturn = data[readCursor];
        data[readCursor] = null;
        readCursor = (readCursor + 1) % data.length;
        return toReturn;
    }

    static class BufferFullException extends RuntimeException {
    }

    @Override
    public String toString() {
        return "RingBuffer{" +
                "size=" + size +
                ", data=" + Arrays.toString(data).replaceAll("null", " ") +
                ", readCursor=" + readCursor +
                ", writeCursor=" + writeCursor +
                '}';
    }
}
