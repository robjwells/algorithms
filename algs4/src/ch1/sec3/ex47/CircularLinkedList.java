package ch1.sec3.ex47;

import java.util.NoSuchElementException;

/**
 * A circular linked list, where the last node in the list points to the front
 * node (which may be itself if there is only one item in the list).
 * <p>
 * This enables O(1) insertion at either and O(1) deletion at the front.
 * Deletion from the rear would be O(n) and is not permitted in this
 * implementation.
 *
 * @param <Item> the type of the items contained in the list.
 */
public class CircularLinkedList<Item> {
    /**
     * A (nullable) reference to the node at the end of the list.
     */
    private Node last;
    private int size;

    /**
     * Insert <tt>item</tt> into the circular list after the node stored in <tt>last</tt>
     * and return a reference to its containing <tt>Node</tt>.
     * <p>
     * If <tt>last</tt> is null, set <tt>last</tt> to be the newly created <tt>Node</tt>.
     *
     * @param item the item to be inserted into the list
     * @return the <tt>Node</tt> containing <tt>item</tt> that has been inserted into the list.
     */
    private Node insertAfterLast(Item item) {
        Node newNode = new Node(item);
        if (last != null) {
            // Place the new node between last and last.next.
            newNode.next = last.next;
            last.next = newNode;
        } else {
            // The new node is the only node in the list.
            newNode.next = newNode;
            last = newNode;
        }
        size++;
        return newNode;
    }

    /**
     * Insert <tt>item</tt> at the end of the list.
     *
     * @param item the item to be appended to the end of the list.
     */
    public void insertAtEnd(Item item) {
        // Ensure last is updated in all cases (ie when the list is non-empty).
        last = insertAfterLast(item);
    }

    /**
     * Insert <tt>item</tt> at the front of the list.
     *
     * @param item the item to be prepended to the front of the list.
     */
    public void insertAtFront(Item item) {
        insertAfterLast(item);
    }

    public Item removeFromFront() {
        if (last == null) {
            throw new NoSuchElementException("Attempt to remove item from empty list.");
        }
        Node removed = last.next;
        last.next = last.next.next;
        if (removed == last) {
            // The empty list is represented by having last be null.
            last = null;
        }
        size--;
        return removed.item;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public int size() {
        return size;
    }

    /**
     * Destructively place all of the items of <tt>other</tt> in order at the
     * end of this list, after which <tt>other</tt> will be made empty.
     *
     * For instance, if this list is [A B C], with A at the front and C at the end,
     * and the other is [D E F], then the resulting list will be [A B C D E F].
     *
     * @param other the list whose items will be emptied and its items placed
     *              in order at the end of this circular linked list.
     */
    public void catenate(CircularLinkedList<Item> other) {
        if (other.isEmpty()) return;

        // Link the two lists together
        Node thisFront = last.next;
        this.last.next = other.last.next;
        other.last.next = thisFront;

        // Update size and empty other list
        this.size += other.size;
        other.size = 0;
        other.last = null;
    }

    /**
     * A singly-linked-list node class that wraps a generic Item.
     */
    class Node {
        Item item;
        Node next;

        Node(Item item) {
            this.item = item;
        }
    }
}
