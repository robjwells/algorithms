package ch1.sec3.ex03;

/**
 * Suppose that a client performs an intermixed sequence of (stack) <em>push</em>
 * and <em>pop</em> operations. The push operations put the integers 0 through 9
 * in order onto the stack; the pop operations print out the return values.
 * Which of the following sequence(s) could <em>not</em> occur?
 *
 * <pre><code>
 * a. 4 3 2 1 0 9 8 7 6 5   --  01234-----56789-----
 * b. 4 6 8 7 5 3 2 9 0 1   --  01234-56-78-----9!      --  Could not occur: 1 would be above 0
 * c. 2 5 6 7 4 8 9 3 1 0   --  012-345-6-7--8-9----
 * d. 4 3 2 1 0 5 6 7 8 9   --  01234-----5-6-7-8-9-
 * e. 1 2 3 4 5 6 9 8 7 0   --  01-2-3-4-5-6-789----
 * f. 0 4 6 5 3 8 1 7 2 9   --  0-1234-56---78-!        --  Could not occur: 7 would be above 1
 * g. 1 4 7 9 8 6 5 3 0 2   --  01-234-567-89-----!     --  Could not occur: 2 would be above 0
 * h. 2 1 4 3 6 5 8 7 9 0   --  012--34--56--78--9--
 * </code></pre>
 */
public class ex1_3_03 {
}
