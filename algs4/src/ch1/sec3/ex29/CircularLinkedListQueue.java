package ch1.sec3.ex29;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class CircularLinkedListQueue<Item> implements Queue<Item> {
    private Node<Item> last;
    private int size;

    public static void main(String[] args) {
        Queue<String> q = new CircularLinkedListQueue<>();
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
        q.enqueue(String.valueOf(9));
        q.enqueue(String.valueOf(10));
        q.enqueue(String.valueOf(11));
        while (q.size() != 0) {
            StdOut.println(q.dequeue());
        }
    }

    @Override
    public void enqueue(Item item) {
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        size++;
        if (last != null) {
            newNode.next = last.next;
            last.next = newNode;
        } else {
            newNode.next = newNode;
        }
        last = newNode;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        Node<Item> toReturn = last.next;
        size--;
        if (last == last.next) {
            last = null;
        } else {
            last.next = last.next.next;
        }
        return toReturn.item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[ ");
        Node<Item> current = last.next;
        while (current != last) {
            sb.append(current.item.toString()).append(", ");
            current = current.next;
        }
        sb.append(last.item.toString()).append(" ]");
        return sb.toString();
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
}
