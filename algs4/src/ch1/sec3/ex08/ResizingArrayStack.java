package ch1.sec3.ex08;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] stack = (Item[]) new Object[1];  // From the book -- should be higher
    private int n = 0;

    /**
     * Ex 1.3.8: report contents & size of backing array
     *
     * Input: it was - the best - of times - - - it was - the - -
     */
    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("-")) {
                StdOut.print(stack.pop() + " ");
            } else {
                stack.push(item);
            }
        }
        StdOut.println(Arrays.toString(stack.stack));
        StdOut.println(stack.backingArraySize());
    }

    // Ex 1.3.8
    // Put this in because stack.stack.length throws a ClassCastException in main
    // for a reason I can’t quite figure out.
    private int backingArraySize() {
        return stack.length;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int max) {
        stack = Arrays.copyOf(stack, max);
    }

    public void push(Item item) {
        if (n == stack.length) {
            resize(2 * stack.length);
        }
        stack[n++] = item;
    }

    public Item pop() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        Item item = stack[--n];
        stack[n] = null;
        if (n == stack.length / 4) {
            resize(stack.length / 2);
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int index = n - 1;

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return stack[index--];
        }
    }
}
