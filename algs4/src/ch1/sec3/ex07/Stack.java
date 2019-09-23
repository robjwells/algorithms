package ch1.sec3.ex07;

public class Stack<Item> {
    private Node first; // top of stack (most recently added node)
    private int n;      // number of items

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
    }

    // Remove item from top of stack
    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
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

    // nested class to define nodes
    private class Node {
        Item item;
        Node next;
    }
}
