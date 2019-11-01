package ch1.sec3.ex34;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;

public class RandomBag<Item> implements Iterable<Item> {
    @SuppressWarnings("unchecked")
    private Item[] data = (Item[]) new Object[10];
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    void add(Item item) {
        if (size == data.length - 1) {
            doubleCapacity();
        }
        data[size] = item;
        size++;
    }

    private void doubleCapacity() {
        data = Arrays.copyOf(data, 2 * data.length);
    }

    @Override
    public Iterator<Item> iterator() {
        Item[] dataForIterator = Arrays.copyOf(data, data.length);
        StdRandom.shuffle(dataForIterator);
        return new Iterator<Item>() {
            private Item[] data = dataForIterator;
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != data.length;
            }

            @Override
            public Item next() {
                return data[cursor++];
            }
        };
    }
}
