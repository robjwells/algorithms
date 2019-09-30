package ch1.sec3.ex22;

/**
 * <b>Ex 1.3.22:</b>
 *
 * Suppose that <tt>x</tt> is a linked-list <tt>Node</tt>. What does the following
 * code fragment do?
 *
 * <pre><code>
 *     t.next = x.next;
 *     x.next = t;
 * </code></pre>
 *
 * Inserts <tt>t</tt> between <tt>x</tt> and <tt>x.next</tt>.
 *
 * <b>Ex 1.3.23:</b>
 *
 * Why does the following code fragment not do the same thing as in the previous question?
 *
 * <pre><code>
 *     x.next = t;
 *     t.next = x.next;
 * </code></pre>
 *
 * This creates a loop at <tt>t</tt> (<tt>x -> t -> t -> t ...</tt>). This is because the
 * order is vitally important: the reference to the original <tt>x.next</tt> was lost in
 * the first line of the fragment.
 */
public class Ex_1_3_22 {
}
