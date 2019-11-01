package ch1.sec3.ex35;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class RandomQueue<Item> {
    private static final int MINIMUM_SIZE = 10;
    @SuppressWarnings("unchecked")
    private Item[] data = (Item[]) new Object[10];
    private int size;

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

}
