package ch1.sec3.ex32;

public interface Steque<Item> {
    void push(Item item);
    Item pop();
    void enqueue(Item item);
    int size();
}
