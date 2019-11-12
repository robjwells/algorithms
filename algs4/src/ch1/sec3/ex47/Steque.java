package ch1.sec3.ex47;

public class Steque<Item> implements ch1.sec3.ex32.Steque<Item> {
    private CircularLinkedList<Item> list = new CircularLinkedList<>();

    public static void main(String[] args) {
        Steque<Integer> s = new Steque<>();
        int[] nums = {1, 2, 3, 4};
        for (int n : nums) {
            if (n % 2 == 0) {
                s.push(n);
            } else {
                s.enqueue(n);
            }
        }
        System.out.println(s);

        while (!s.isEmpty()) {
            s.pop();
            System.out.println(s);
        }

        for (int n : nums) {
            if (n % 2 == 0) {
                s.push(n);
            } else {
                s.enqueue(n);
            }
        }
        // Queue<Integer> t = new Queue<>();
        Steque<Integer> t = new Steque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int n = nums[i];
            if (n % 2 == 0) {
                t.push(n);
            } else {
                t.enqueue(n);
            }
        }
        System.out.println("s: " + s);
        System.out.println("t: " + t);
        s.catenate(t);
        System.out.println("s: " + s);
        System.out.println("t: " + t);
        System.out.println(s.pop());
    }

    @Override
    public void push(Item item) {
        list.insertAtFront(item);
    }

    @Override
    public Item pop() {
        return list.removeFromFront();
    }

    @Override
    public void enqueue(Item item) {
        list.insertAtEnd(item);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return "Steque{" +
                "size=" + list.size() +
                ", list=" + list +
                '}';
    }

    /**
     * Destructively place all of the items of <tt>other</tt> at the
     * end of this steque, so that this steque’s front element is at the
     * front and <tt>other</tt>’s last element is at the end.
     *
     * <tt>other</tt> will be empty after the operation.
     *
     * @param other the steque from which to take the elements.
     */
    public void catenate(Steque<Item> other) {
        list.catenate(other.list);
    }
}
