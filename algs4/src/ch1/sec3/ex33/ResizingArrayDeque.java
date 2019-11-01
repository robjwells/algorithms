package ch1.sec3.ex33;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ResizingArrayDeque<Item> implements Deque<Item> {
    private static final int MINIMUM_SIZE = 2;
    private Item[] data = (Item[]) new Object[MINIMUM_SIZE];
    private int front;
    private int rear;
    private int size;

    public static void main(String[] args) {
        ResizingArrayDeque<String> d = new ResizingArrayDeque<>();
        List<String> instructions = List.of("L1", "R2", "L3", "R4", "L5", "L6", "L7", "L8");
        for (String inst : instructions) {
            if (inst.startsWith("L")) {
                d.pushLeft(inst);
            } else {
                d.pushRight(inst);
            }
            System.out.println(d);
        }
        while (!d.isEmpty()) {
            d.popLeft();
            System.out.println(d);
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
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int maxSize) {
        Item[] resizedArray = (Item[]) new Object[maxSize];
        int firstIndex = indexToRight(front);
        for (int idx = 0; idx < size; idx++) {
            resizedArray[idx] = data[intToIndex(firstIndex + idx)];
        }
        front = resizedArray.length - 1;
        rear = size;
        data = resizedArray;
    }

    private void halveStorageIfNecessary() {
        // Backing array kept at least a minimum size
        if (data.length > MINIMUM_SIZE && size == data.length / 4) {
            resize(data.length / 2);
        }
    }

    private void doubleStorageIfNecessary() {
        // Double storage when size is one less than capacity to avoid the
        // tricky situation where the array is full and the front and rear
        // indices have moved past each other.
        if (size == data.length - 1) {
            resize(data.length * 2);
        }
    }

    private int intToIndex(int i) {
        return Math.floorMod(i, data.length);
    }

    private int indexToLeft(int currentIndex) {
        return intToIndex(currentIndex - 1);
    }

    private int indexToRight(int currentIndex) {
        return intToIndex(currentIndex + 1);
    }

    @Override
    public void pushLeft(Item item) {
        doubleStorageIfNecessary();
        data[front] = item;
        size++;
        if (front == rear) {
            rear = indexToRight(rear);
        }
        front = indexToLeft(front);
    }

    @Override
    public void pushRight(Item item) {
        doubleStorageIfNecessary();
        data[rear] = item;
        size++;
        if (front == rear) {
            front = indexToLeft(front);
        }
        rear = indexToRight(rear);
    }

    @Override
    public Item popLeft() {
        if (size == 0) {
            throw new NoSuchElementException("Pop from an empty deque.");
        }
        front = indexToRight(front);
        return pop(front);
    }

    @Override
    public Item popRight() {
        if (size == 0) {
            throw new NoSuchElementException("Pop from an empty deque.");
        }
        rear = indexToLeft(rear);
        return pop(rear);
    }

    /*
        We can abstract the pop method because the resizing comes last,
        once the item has been retrieved, and the updated indices
        (updated in resize()) aren't needed to perform the pop itself.

        Contrast this with pushing, where if the backing array must increase
        in size, the resize must take place and the new indices be set before
        an attempt at placing the item into the backing array.
     */
    private Item pop(int index) {
        size--;
        Item toReturn = data[index];
        data[index] = null;
        halveStorageIfNecessary();
        return toReturn;
    }
}
