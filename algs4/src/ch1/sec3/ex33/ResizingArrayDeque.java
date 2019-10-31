package ch1.sec3.ex33;

import edu.princeton.cs.algs4.ResizingArrayQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ResizingArrayDeque<Item> implements Deque<Item> {
    private Item[] data = (Item[]) new Object[2];
    private int front = 0;
    private int rear = 1;
    private int size;

    public static void main(String[] args) {
        ResizingArrayDeque<Integer> d = new ResizingArrayDeque<>();
        System.out.println(d);
        d.pushLeft(1);
        System.out.println(d);
        d.pushLeft(2);
        System.out.println(d);
        d.pushRight(3);
        System.out.println(d);
        d.pushRight(4);
        d.popLeft();
        System.out.println(d);
        d.popLeft();
        System.out.println(d);
        d.popLeft();
        System.out.println(d);
        d.popLeft();
        System.out.println(d);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int maxSize) {
        Item[] original = data;
        if (size == 0) {
            data = (Item[]) new Object[maxSize];
            front = 0;
            rear = 0;
        } else if (front < rear) {
            Item[] occupiedPortion = Arrays.copyOfRange(data, front + 1, rear);
            Item[] resized = Arrays.copyOf(occupiedPortion, maxSize);
            data = resized;
            front = maxSize - 1;
            rear = occupiedPortion.length;
        } else {
            Item[] frontPortion = Arrays.copyOfRange(data, front + 1, data.length);
            Item[] rearPortion = Arrays.copyOfRange(data, 0, rear);
            Item[] resized = Arrays.copyOf(frontPortion, maxSize);
            int targetIndex = frontPortion.length;
            for (Item item: rearPortion) {
                resized[targetIndex] = item;
                targetIndex++;
            }
            data = resized;
            front = maxSize - 1;
            rear = frontPortion.length + rearPortion.length;
        }
    }

    @Override
    public String toString() {
        return "ResizingArrayDeque{" +
                "front=" + front +
                ", rear=" + rear +
                ", size=" + size +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    @Override
    public void pushLeft(Item item) {
        if (size == data.length) {
            resize(size * 2);
        }
        data[front] = item;
        size++;
        front--;
        if (front == -1) {
            front = data.length - 1;
        }
    }

    @Override
    public void pushRight(Item item) {
        if (size == data.length) {
            resize(size * 2);
        }
        data[rear] = item;
        size++;
        rear++;
        if (rear == data.length) {
            rear = 0;
        }
    }

    @Override
    public Item popLeft() {
        if (size == 0) {
            throw new NoSuchElementException("Pop from an empty deque.");
        }
        size--;
        front++;
        if (front == data.length) {
            front = 0;
        }
        Item toReturn = data[front];
        data[front] = null;
        return toReturn;
    }

    @Override
    public Item popRight() {
        if (size == 0) {
            throw new NoSuchElementException("Pop from an empty deque.");
        }
        size--;
        rear--;
        if (rear == -1) {
            rear = data.length - 1;
        }
        Item toReturn = data[rear];
        data[rear] = null;
        return toReturn;
    }
}
