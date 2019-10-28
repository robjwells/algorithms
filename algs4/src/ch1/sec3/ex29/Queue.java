package ch1.sec3.ex29;

public interface Queue<Item> {
    void enqueue(Item item);
    Item dequeue();
    boolean isEmpty();
    int size();
}
