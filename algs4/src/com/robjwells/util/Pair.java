package com.robjwells.util;

/**
 * Immutable generic container of two items of the same type.
 *
 * Create a <code>Pair</code> using the factory method {@link #of(Object, Object)},
 * which cuts down on some boilerplate (and mirrors {@link java.util.List#of(Object, Object)}.
 *
 * @param <Item> The type of the contained items.
 */
public class Pair<Item> {
    public final Item first;
    public final Item second;

    private Pair(Item first, Item second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Create a new <code>Pair</code> containing items <code>first</code> and <code>second</code>.
     * @param first an item
     * @param second another item
     * @param <Item> the type of <code>first</code> and <code>second</code>
     * @return a new <code>Pair&lt;Item&gt;</code> containing <code>first</code> and <code>second</code>
     */
    public static <Item> Pair<Item> of(Item first, Item second) {
        return new Pair<>(first, second);
    }
}
