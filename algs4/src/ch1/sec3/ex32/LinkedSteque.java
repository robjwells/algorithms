package ch1.sec3.ex32;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A Steque implemented using a singly-linked list, maintaining pointers
 * to the front and rear nodes in the list.
 * <p>
 * You can add at either end but only remove from the front.
 *
 * @param <Item> the contained item type
 */
public class LinkedSteque<Item> implements Steque<Item> {
    private Node front;
    private Node rear;
    private int size;

    public static void main(String[] args) {
        Steque<Integer> s = new LinkedSteque<>();
        List.of(1, 2, 3, 4, 5).forEach(s::enqueue);
        s.push(6);
        while (s.size() > 0) {
            System.out.println(s.pop());
        }
        s.push(7);
        s.enqueue(8);
        System.out.println(s.pop());
        System.out.println(s.pop());
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(Item item) {
        Node newNode = new Node(item);
        newNode.next = front;
        front = newNode;
        if (newNode.next == null) {
            // Set rear if adding first node in the list.
            rear = newNode;
        }
        size++;
    }

    @Override
    public Item pop() {
        if (front == null) {
            throw new NoSuchElementException("Pop from empty structure.");
        }
        Node toRemove = front;
        front = toRemove.next;
        if (front == null) {
            // Unset rear if removing last node in the last.
            rear = null;
        }
        size--;
        return toRemove.item;
    }

    @Override
    public void enqueue(Item item) {
        Node newNode = new Node(item);
        if (rear == null) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    private class Node {
        Node next;
        Item item;

        Node(Item item) {
            this.item = item;
        }
    }
}
