package ch1.sec3.ex33;

public interface Deque<Item> {
    boolean isEmpty();
    int size();
    void pushLeft(Item item);
    void pushRight(Item item);
    Item popLeft();
    Item popRight();
}
