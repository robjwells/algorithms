package com.robjwells.util;

public class Pair<T> {
    public final T first;
    public final T second;

    private Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public static <T> Pair<T> of(T first, T second) {
        return new Pair<>(first, second);
    }
}
