package ch1.sec3.ex38;

public interface GeneralizedQueue<Item> {
    int size(); // Not in the API described in the book but too useful not to include
    boolean isEmpty();
    void insert(Item x);

    /**
     * Delete the kth least recently inserted item, where 1 <= k <= size.
     *
     * When <tt>k</tt> is 1, the item at the end of the queue is returned
     * (i.e. the most recently inserted item).
     *
     * When <tt>k</tt> is equal to the size of the queue, the item at the
     * front of the queue is returned (i.e. the least recently inserted item).
     *
     * Throws <tt>NoSuchElementException</tt> if <tt>k</tt> is out of bounds.
     *
     * @param k the distance from the end of the queue, from 1 to the size of the queue
     * @return the element <tt>k</tt> positions from the end of the queue.
     */
    Item delete(int k);     // Remove the kth least recently inserted item, 0 <= k < size
}
