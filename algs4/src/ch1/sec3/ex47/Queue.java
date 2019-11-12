package ch1.sec3.ex47;

public class Queue<Item> implements ch1.sec3.ex29.Queue<Item> {
    private CircularLinkedList<Item> list = new CircularLinkedList<>();

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        int[] nums = {1, 2, 3, 4};
        for (int n : nums) {
            q.enqueue(n);
        }
        System.out.println(q);

        while (!q.isEmpty()) {
            q.dequeue();
            System.out.println(q);
        }

        for (int n : nums) {
            q.enqueue(n);
        }
        Queue<Integer> r = new Queue<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            r.enqueue(nums[i]);
        }
        System.out.println("q: " + q);
        System.out.println("r: " + r);
        q.catenate(r);
        System.out.println("q: " + q);
        System.out.println("r: " + r);
        System.out.println(q.dequeue());
    }

    @Override
    public void enqueue(Item item) {
        list.insertAtEnd(item);
    }

    @Override
    public Item dequeue() {
        return list.removeFromFront();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "Queue{" +
                "size=" + list.size() +
                ", list=" + list +
                '}';
    }

    /**
     * Destructively place all of the items of <tt>other</tt> at the
     * end of this queue, so that this queue’s front element is at the
     * front and <tt>other</tt>’s last element is at the end.
     *
     * <tt>other</tt> will be empty after the operation.
     *
     * @param other the queue from which to take the elements.
     */
    public void catenate(Queue<Item> other) {
        list.catenate(other.list);
    }
}
