package ch1.sec3.ex19;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> first;

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (int i : list) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        list.removeLast();
        for (int i : list) {
            StdOut.print(i + " ");
        }
    }

    /**
     * Add <tt>info</tt> to the head of the linked-list, as with a stack.
     *
     * @param info the value to put at the head of the list
     */
    void add(T info) {
        Node<T> node = new Node<>();
        node.info = info;
        node.next = first;
        first = node;
    }

    /**
     * Remove the last node in the list, if one exists.
     *
     * Ex 1.3.19: Give a code fragment that removes the last node in a linked list
     * whose first node is <tt>first</tt>.
     */
    void removeLast() {
        // Special case where list is empty or has one node
        if (first == null || first.next == null) {
            first = null;
            return;
        }

        Node<T> previous = first;
        Node<T> current = first.next;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        // `current` is now the last node, so remove the reference to it
        previous.next = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T info = current.info;
                current = current.next;
                return info;
            }
        };
    }

    static class Node<T> {
        T info;
        Node<T> next;
    }
}
