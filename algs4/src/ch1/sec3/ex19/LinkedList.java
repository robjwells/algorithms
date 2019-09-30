package ch1.sec3.ex19;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> first;

    public static void main(String[] args) {
        testRemoveLast();
        testDeleteKth();
    }

    static void testDeleteKth() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (int index = 4; index >= 0; index--) {
            list.delete(index);
            for (int i : list) {
                StdOut.print(i + " ");
            }
            StdOut.println();
        }
    }

    static void testRemoveLast() {
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
        StdOut.println();
    }

    /**
     * Remove the node immediately after the given <tt>node</tt>.
     * <p>
     * Does nothing if <tt>node</tt> has no next node.
     * <p>
     * Ex 1.3.24.
     *
     * @param node the node whose neighbour is to be removed
     * @param <T>  type of the value stored by the node; not used in this method
     *             — only present as the wildcard (?) won't work with assignment.
     */
    static <T> void removeAfter(Node<T> node) {
        if (node == null || node.next == null) {
            return;
        }
        node.next = node.next.next;
    }

    /**
     * Insert node <tt>second</tt> after node <tt>first</tt>.
     * <p>
     * Does nothing if either argument is null.
     * <p>
     * Ex 1.3.25. What is unclear is what happens to any nodes linked after
     * <tt>second</tt> (ie <tt>second.next</tt>): does <tt>second.next</tt>
     * get set to the original <tt>first.next</tt>? Or is it the other way
     * around? Do we keep the chain from <tt>first</tt> or <tt>second</tt>?
     *
     * @param first  the node after which <tt>second</tt> is inserted
     * @param second the node inserted after <tt>first</tt>
     * @param <T>    the type of the value stored by the two nodes
     */
    static <T> void insertAfter(Node<T> first, Node<T> second) {
        if (first == null || second == null) {
            return;
        }
        first.next = second; // Keep second's following nodes
        // OR:
        // second.next = first.next;
        // first.next = second;
    }

    /**
     * Remove any nodes that contain <tt>key</tt> in their <tt>info</tt> field.
     * <p>
     * Ex 1.3.26.
     *
     * @param key the value to search for
     */
    void remove(T key) {
        // Handle the key-containing nodes at the front
        while (first != null && first.info.equals(key)) {
            first = first.next;
        }
        if (first == null) {
            return;
        }
        Node<T> previous = first;
        Node<T> current = first.next;
        while (current != null) {
            if (current.info.equals(key)) {
                previous.next = current.next;
            }
            current = current.next;
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
     * <p>
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

    /**
     * Remove the <tt>k</tt>th element of the linked list, if it exists.
     * <p>
     * Ex 1.3.20.
     *
     * @param k the position of the element to remove (zero-based)
     */
    void delete(int k) {
        Node<T> previous = null;
        Node<T> current = first;
        while (current != null && k > 0) {
            previous = current;
            current = current.next;
            k--;
        }
        if (current != null && k == 0) {
            if (previous == null) {
                first = current.next;
            } else {
                previous.next = current.next;
            }
        }
    }

    /**
     * Return true if <tt>key</tt> is present in the info field of some <tt>Node</tt>
     * in the linked list.
     * <p>
     * Ex 1.3.21.
     *
     * @param key the value to search for
     * @return true if <tt>key</tt> was found, else false
     */
    boolean find(T key) {
        Node<T> current = first;
        while (current != null) {
            if (current.info.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
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