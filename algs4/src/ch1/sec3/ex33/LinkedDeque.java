package ch1.sec3.ex33;

import java.util.NoSuchElementException;

@SuppressWarnings("DuplicatedCode")
public class LinkedDeque<Item> implements Deque<Item> {
    private int size;
    private Node front;     // On the 'left' on the deque
    private Node rear;      // On the 'right' of the deque


    private class Node {
        Node previous;
        Node next;
        Item item;

        Node(Item item) {
            this.item = item;
        }
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
    public void pushLeft(Item item) {
        Node n = new Node(item);
        n.next = front;
        front = n;
        if (n.next == null) {
            // Set rear if adding first node in the list.
            rear = n;
        }
        size++;
    }

    @Override
    public void pushRight(Item item) {
        Node n = new Node(item);
        n.previous = rear;
        rear = n;
        if (n.previous == null) {
            // Set front if adding first node in the list.
            front = n;
        }
        size++;
    }

    @Override
    public Item popLeft() {
        if (front == null) {
            throw new NoSuchElementException("Attempt to popLeft from empty deque.");
        }
        Node toRemove = front;
        front = toRemove.next;
        if (front == null) {
            // Unset if removing last node in the list.
            rear = null;
        }
        size--;
        return toRemove.item;
    }

    @Override
    public Item popRight() {
        if (rear == null) {
            throw new NoSuchElementException("Attempt to popLeft from empty deque.");
        }
        Node toRemove = rear;
        rear = toRemove.previous;
        if (rear == null) {
            // Unset if removing last node in the list.
            front = null;
        }
        size--;
        return toRemove.item;
    }
}
