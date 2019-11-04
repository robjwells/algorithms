package ch1.sec3.ex35;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class RandomQueue<Item> implements Iterable<Item> {
    private static final int MINIMUM_SIZE = 10;
    @SuppressWarnings("unchecked")
    private Item[] data = (Item[]) new Object[10];
    private int size;


    public static void main(String[] args) {
        RandomQueue<Integer> q = new RandomQueue<>();
        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        for (int i = 0; i < 10; i++) {
            StdOut.print(i + ": ");
            nums.forEach(q::enqueue);
            while (!q.isEmpty()) {
                StdOut.print(q.dequeue());
            }
            StdOut.println();
        }

        StdOut.println("From iterator:");
        nums.forEach(q::enqueue);
        for (int n : q) {
            StdOut.print(n);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        doubleCapacityIfNecessary();
        data[size++] = item;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Dequeue from an empty queue.");
        }

        int swapPosition = StdRandom.uniform(size);
        Item toRemove = data[swapPosition];
        data[swapPosition] = data[--size];
        data[size] = null;

        halveCapacityIfNecessary();
        return toRemove;
    }

    private void doubleCapacityIfNecessary() {
        if (size == data.length) {
            data = Arrays.copyOf(data, 2 * data.length);
        }
    }

    private void halveCapacityIfNecessary() {
        if (data.length > MINIMUM_SIZE && size == data.length / 4) {
            data = Arrays.copyOf(data, data.length / 2);
        }
    }

    @Override
    public Iterator<Item> iterator() {
        Item[] dataCopy = Arrays.copyOf(data, size);
        StdRandom.shuffle(dataCopy);

        return new Iterator<Item>() {
            private final Item[] shuffledData = dataCopy;
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < shuffledData.length;
            }

            @Override
            public Item next() {
                return shuffledData[cursor++];
            }
        };
    }
}
