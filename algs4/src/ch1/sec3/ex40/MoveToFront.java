package ch1.sec3.ex40;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Linked list that only stores unique elements; repeated elements have
 * their one occurrence in the list moved to the front of the list.
 * <p>
 * This is a naive implementation that is O(n) on insertion.
 * <p>
 * You could trivially amortise the cost of removal by storing present
 * elements in a set, though that has a space cost (up to double).
 * <p>
 * Mostly I haven’t done that as we’re not there in the book.
 *
 * @param <Item> the content of the list
 */
public class MoveToFront<Item> implements Iterable<Item> {
    private Node front;
    private int size;

    public static void main(String[] args) {
        MoveToFront<Integer> m = new MoveToFront<>();
        List<Integer> n = List.of(1, 2, 3, 4, 5);
        n.forEach(m::add);
        System.out.println(m);
        List<Integer> o = List.of(1, 4, 6, 6, 4);
        for (int x : o) {
            m.add(x);
            System.out.println(m);
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node current = front;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Item toReturn = current.item;
                current = current.next;
                return toReturn;
            }
        };
    }

    boolean isEmpty() {
        return size == 0;
    }

    void add(Item item) {
        Node newNode = new Node(item);
        remove(item);
        newNode.next = front;
        front = newNode;
        size++;
    }

    void remove(Item item) {
        if (isEmpty()) return;

        if (front.item.equals(item)) {
            front = front.next;
            return;
        }

        Node previous = front;
        Node current = front.next;
        while (current != null) {
            if (current.item.equals(item)) {
                previous.next = current.next;
                return;
            }
            previous = current;
            current = current.next;
        }
        size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node current = front;
        while (current != null) {
            sb.append(current.item).append(" ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    class Node {
        Node next;
        Item item;

        Node(Item item) {
            this.item = item;
        }
    }
}
