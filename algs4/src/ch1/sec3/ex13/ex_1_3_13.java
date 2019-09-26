package ch1.sec3.ex13;

/**
 * Exercise 1.3.13:
 * <p>
 * Suppose that a client performs an intermixed sequence of (queue) <em>enqueue</em> and
 * <em>dequeue</em> operations. The enqueue operation puts the integers 0 through 9 in
 * order onto the queue; the dequeue operations print out the return value.
 * Which of the following sequence(s) could <em>not</em> occur:
 *
 * <pre><code>
 * a.   0 1 2 3 4 5 6 7 8 9
 * b.   4 6 8 7 5 3 2 9 0 1
 * c.   2 5 6 7 4 8 9 3 1 0
 * d.   4 3 2 1 0 5 6 7 8 9
 * </code></pre>
 * <p>
 * Because 0 through 9 are placed onto the queue <em>in order</em> only sequence a can
 * occur. For instance, with sequence 2, if the queue is [01234] there is no way to
 * dequeue 4 before 0123.
 */
public class ex_1_3_13 {
}
