package ch1.sec3.ex18;

/**
 * Exercise 1.3.18: Suppose <tt>x</tt> is a linked-list node and not the last node
 * on the list. What is the effect of the following code fragment?
 *
 * <pre><code>
 *     x.next = x.next.next;
 * </code></pre>
 *
 * (Presuming the list is singly-linked) This removes the node following <tt>x</tt>
 * from the list, as there will no longer be a reference stored to it.
 */
public class Ex_1_3_18 {
}
