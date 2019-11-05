package ch1.sec3.ex38;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class GeneralizedArrayQueue<Item> implements GeneralizedQueue<Item> {
    private static final int MINIMUM_SIZE = 4;
    private int size;
    @SuppressWarnings("unchecked")
    private Item[] data = (Item[]) new Object[MINIMUM_SIZE];

    public static void main(String[] args) {
        GeneralizedArrayQueue<Integer> q = new GeneralizedArrayQueue<>();

        IntStream.range(1, 6).forEach(q::insert);
        System.out.println(q);
        for (int k = 5; k > 0; k--) {
            System.out.println("k: " + k + ", " + q.delete(k));
            System.out.println(q);
        }

        q.insert(6);
        System.out.println(q);
        System.out.println(1 + ", " + q.delete(1));
        System.out.println(q);

        IntStream.range(1, 6).forEach(q::insert);
        System.out.println(q);
        while (!q.isEmpty()) {
            int k = StdRandom.uniform(1, q.size() + 1);
            System.out.println(k + ", " + q.delete(k));
            System.out.println(q);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(Item x) {
        resizeIfNecessary();
        data[size] = x;
        size++;
    }

    @Override
    public Item delete(int k) {
        if (isEmpty() || (k < 1) || (k > size)) {
            throw new NoSuchElementException("k is out of bounds");
        }

        int index = size - k;
        Item toRemove = data[index];
        data[index] = null;

        // Shift remaining items down
        for (int moveFrom = index + 1; moveFrom < size; moveFrom++) {
            data[moveFrom - 1] = data[moveFrom];
            data[moveFrom] = null;
        }
        size--; // Decrement size *after* accessing items at original positions
        resizeIfNecessary();
        return toRemove;
    }

    private void resizeIfNecessary() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        } else if (data.length > MINIMUM_SIZE && size == data.length / 4) {
            data = Arrays.copyOf(data, data.length / 2);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Front ")
                .append(Arrays.toString(data).replaceAll("null", " "))
                .append(" Rear");
        return sb.toString();
    }
}
