package ch1.sec3.ex38;

import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class GeneralizedLinkedQueue<Item> implements GeneralizedQueue<Item> {
    private int size;
    private Node front;
    private Node rear;

    public static void main(String[] args) {
        GeneralizedLinkedQueue<Integer> q = new GeneralizedLinkedQueue<>();
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

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(Item x) {
        Node newNode = new Node(x);
        if (front == null) {
            front = newNode;
        } else {
            newNode.frontward = rear;
            rear.rearward = newNode;
        }
        rear = newNode;
        size++;
    }

    @Override
    public Item delete(int k) {
        if (isEmpty() || (k < 1) || (k > size)) {
            throw new NoSuchElementException("k is out of bounds");
        }

        // Walk the list k - 1 steps from the most recently inserted node
        Node current = rear;
        for (int steps = k - 1; steps > 0; steps--) {
            current = current.frontward;
        }

        Item toReturn = current.item;

        // Remove node from the list
        if (current.rearward != null) {
            current.rearward.frontward = current.frontward;
        } else {
            rear = current.frontward;
        }
        if (current.frontward != null) {
            current.frontward.rearward = current.rearward;
        } else {
            front = current.rearward;
        }

        size--;
        return toReturn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Front [ ");
        Node current = front;
        while (current != null) {
            sb.append(current.item).append(" ");
            current = current.rearward;
        }
        sb.append("] Rear");
        return sb.toString();
    }

    private class Node {
        Item item;
        /**
         * The next more-recently inserted node.
         */
        Node rearward;
        /**
         * The next less-recently inserted node.
         */
        Node frontward;

        Node(Item item) {
            this.item = item;
        }
    }
}
