package ch1.sec3.ex48;

import ch1.sec3.ex33.Deque;
import ch1.sec3.ex33.LinkedDeque;

import java.util.NoSuchElementException;

/**
 * TwoStack implements two practically independent stacks using a single Deque.
 * <p>
 * This is really just a thin wrapper around the deque -- even the methods of
 * the deque in use are push/pop. Separate size counts are maintained for the
 * two virtual stacks.
 */
public class TwoStack<Item> {
    private Deque<Item> deque = new LinkedDeque<>();
    private int leftSize;
    private int rightSize;

    public void pushLeft(Item item) {
        deque.pushLeft(item);
        leftSize++;
    }

    public Item popLeft() {
        // Explicitly check that the left virtual stack is not empty to
        // prevent us from 'popping' from what would be the bottom of
        // the right virtual stack.
        if (leftSize == 0) throw new NoSuchElementException("Attempt to pop from empty stack.");
        leftSize--;
        return deque.popLeft();
    }

    public int leftStackSize() {
        return leftSize;
    }

    public boolean leftStackIsEmpty() {
        return leftSize == 0;
    }

    public void pushRight(Item item) {
        deque.pushRight(item);
        rightSize++;
    }

    public Item popRight(Item item) {
        // Explicitly check that the right virtual stack is not empty to
        // prevent us from 'popping' from what would be the bottom of
        // the left virtual stack.
        if (rightSize == 0) throw new NoSuchElementException("Attempt to pop from empty stack.");
        rightSize--;
        return deque.popRight();
    }

    public int rightStackSize() {
        return rightSize;
    }

    public boolean rightStackIsEmpty() {
        return rightSize == 0;
    }
}
