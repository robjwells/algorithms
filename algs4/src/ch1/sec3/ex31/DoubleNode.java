package ch1.sec3.ex31;

public class DoubleNode<Item> {
    private Item item;
    private DoubleNode<Item> previous;
    private DoubleNode<Item> next;

    public DoubleNode(Item item) {
        this.item = item;
    }

    public static <T> DoubleNode<T> insertAtBeginning(T item, DoubleNode<T> someNode) {
        DoubleNode<T> newNode = new DoubleNode<>(item);
        while (someNode.previous != null) {
            someNode = someNode.previous;
        }
        newNode.next = someNode;
        return newNode;
    }

    public static <T> DoubleNode<T> insertAtEnd(T item, DoubleNode<T> someNode) {
        DoubleNode<T> newNode = new DoubleNode<>(item);
        while (someNode.next != null) {
            someNode = someNode.next;
        }
        newNode.previous = someNode;
        return newNode;
    }

    public static <T> DoubleNode<T> insertBefore(T item, DoubleNode<T> someNode) {
        DoubleNode<T> newNode = new DoubleNode<>(item);
        newNode.next = someNode;
        newNode.previous = someNode.previous;
        someNode.previous = newNode;
        return newNode;
    }

    public static <T> DoubleNode<T> insertAfter(T item, DoubleNode<T> someNode) {
        DoubleNode<T> newNode = new DoubleNode<>(item);
        newNode.previous = someNode;
        newNode.next = someNode.next;
        someNode.next = newNode;
        return newNode;
    }

    public static <T> T remove(DoubleNode<T> someNode) {
        if (someNode.previous != null) {
            someNode.previous.next = someNode.next;
        }
        if (someNode.next != null) {
            someNode.next.previous = someNode.previous;
        }
        return someNode.item;
    }
}
