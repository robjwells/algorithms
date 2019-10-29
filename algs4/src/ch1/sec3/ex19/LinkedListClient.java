package ch1.sec3.ex19;

import edu.princeton.cs.algs4.StdOut;

public class LinkedListClient {
    public static void main(String[] args) {
        testRemoveLast();
        testDeleteKth();
        testReverse();
    }

    private static LinkedList<Integer> createLinkedList123() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }

    private static void testReverse() {
        LinkedList<Integer> list = createLinkedList123();
        StdOut.println("Reverse setup:");
        for (int i : list) {
            StdOut.print(i + " ");
        }
        StdOut.println("\nReversed:");
        list.reverse();
        for (int i : list) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }


    private static void testDeleteKth() {
        StdOut.println("Test delete kth item from LinkedList:");
        LinkedList<Integer> list = createLinkedList123();
        for (int index = 4; index >= 0; index--) {
            list.delete(index);
            for (int i : list) {
                StdOut.print(i + " ");
            }
            StdOut.println();
        }
    }

    private static void testRemoveLast() {
        StdOut.println("Test remove last item from LinkedList:");
        LinkedList<Integer> list = createLinkedList123();
        for (int i : list) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        list.removeLast();
        for (int i : list) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }
}
