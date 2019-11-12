package ch1.sec3.ex50;

import ch1.sec3.ex07.Stack;

import java.util.ConcurrentModificationException;

public class FailFastStackIteratorClient {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        // Normal iteration
        for (int n : s) {
            System.out.println(n);
        }
        // Concurrent modification of the stack should cause an exception
        try {
            for (int n : s) {
                s.push(n);
            }
        } catch (ConcurrentModificationException exc) {
            System.out.println("Iterator aborted because of concurrent modification.");
        }
    }
}
