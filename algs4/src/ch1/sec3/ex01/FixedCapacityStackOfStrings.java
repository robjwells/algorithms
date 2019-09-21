package ch1.sec3.ex01;

public class FixedCapacityStackOfStrings {
    private String[] stack;
    private int capacity;
    private int size;

    public FixedCapacityStackOfStrings(int cap) {
        stack = new String[cap];
        capacity = cap;
        size = 0;
    }

    // This is the part for Ex 1.3.1 but I’ve implemented the rest of the class too.
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    /*
     * This is from the book but it’s unsafe.
     *
     * Really you’d want to consider something that returned a boolean (false = not able to push)
     * or threw an exception, or define push on a fixed-capacity stack to drop the item that's on
     * the bottom of the stack.
     */
    public void push(String item) {
        stack[size] = item;
        size += 1;
    }

    /*
     * This is from the book but it’s unsafe.
     *
     * Really you’d want to either check if the collection was empty and throw an exception,
     * or return an Optional<String> (where the optional is empty if the stack is empty).
     */
    public String pop() {
        size -= 1;
        return stack[size];
    }
}
