package ch1.sec3.ex07;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Stack<Item> implements Iterable<Item> {
    private Node first; // top of stack (most recently added node)
    private int n;      // number of items

    /**
     * Ex 1.3.50: Fail-fast iterator
     * <p>
     * Track the number of push and pop operations to allow iterators
     * to detect concurrent modifications and to abort iteration.
     */
    private int pushCount;
    private int popCount;

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.stream().map(i -> i * 2).forEach(System.out::println);
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    // Add item to top of stack
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
        pushCount++;
    }

    // Remove item from top of stack
    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        popCount++;
        return item;
    }

    /**
     * Ex 1.3.7: Add a method <code>peek()</code> to <code>Stack</code> that returns
     * the most recently inserted item on the stack (without popping it).
     *
     * @return the item most recently pushed onto the stack, or <code>null</code> if empty
     */
    public Item peek() {
        if (first == null) return null;
        return first.item;
    }

    public Stream<Item> stream() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator(), Spliterator.NONNULL),
                false
        );
    }

    /**
     * Ex 1.3.50
     * <p>
     * The solution given in the book, and Rene Argento’s implementation, use a single value
     * to track (combined) pushes and pops. However, my concern with this is that a sequence
     * of pushes and pops could occur that leaves the value unchanged (ie pop then push).
     * This still intuitively counts as concurrent modification.
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;
            private int pushesAtIteratorCreation = pushCount;
            private int popsAtIteratorCreation = popCount;

            @Override
            public boolean hasNext() {
                if (pushesAtIteratorCreation != pushCount || popsAtIteratorCreation != popCount) {
                    throw new ConcurrentModificationException();
                }
                return (current != null);
            }

            @Override
            public Item next() {
                if (pushesAtIteratorCreation != pushCount || popsAtIteratorCreation != popCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    // nested class to define nodes
    private class Node {
        Item item;
        Node next;
    }
}
