package ch1.sec3.ex47;

public class Stack<Item> {
    private CircularLinkedList<Item> list = new CircularLinkedList<>();

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        int[] nums = {1, 2, 3, 4};
        for (int n : nums) {
            s.push(n);
        }
        System.out.println(s);

        while (!s.isEmpty()) {
            s.pop();
            System.out.println(s);
        }

        for (int n : nums) {
            s.push(n);
        }
        // Queue<Integer> t = new Queue<>();
        Stack<Integer> t = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            t.push(nums[i]);
        }
        System.out.println("s: " + s);
        System.out.println("t: " + t);
        s.catenate(t);
        System.out.println("s: " + s);
        System.out.println("t: " + t);
        System.out.println(s.pop());
    }

    public void push(Item item) {
        list.insertAtFront(item);
    }

    public Item pop() {
        return list.removeFromFront();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return "Stack{" +
                "size=" + list.size() +
                ", list=" + list +
                '}';
    }

    /**
     * Destructively place all of the items of <tt>other</tt> at the
     * beneath this stack, so that this stack’s top element is at the
     * top and <tt>other</tt>’s bottom element is at the bottom.
     *
     * <tt>other</tt> will be empty after the operation.
     */
    public void catenate(Stack<Item> other) {
        list.catenate(other.list);
    }
}
