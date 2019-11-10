package ch1.sec3.ex45;

import edu.princeton.cs.algs4.Stack;

import java.util.List;

public class StackGenerability {
    /**
     * Test if the given stack directives will lead to underflow.
     *
     * @param directives An array of space-separated directives, where a hyphen (-)
     *                   is a directive to pop from the stack, and anything else is
     *                   to be pushed onto the stack.
     * @return true if the given directives will cause the stack to underflow,
     * false otherwise
     */
    public static boolean directiveSequenceCausesStackUnderflow(String[] directives) {
        long stackSize = 0;
        for (String directive : directives) {
            stackSize += directive.equals("-") ? -1 : 1;
            if (stackSize < 0) return true;
        }
        return false;
    }

    public static boolean possibleSequenceOfPops(String[] sequence) {
        Stack<Integer> stack = new Stack<>();
        int current = 0;

        for (String s : sequence) {
            // Seen is the next item shown (ie popped) in the sequence
            int seen = Integer.parseInt(s);

            if (stack.isEmpty() || seen > stack.peek()) {
                // Push up to but not including seen
                while (current < seen) {
                    stack.push(current++);
                }
                // Increment current without pushing to simulate push & then pop
                current++;
            } else if (stack.peek() == seen) {
                stack.pop();
            } else {
                return false;
            }
        }
        return true;
    }

    public static void reportUnderflow(String directives) {
        String[] split = directives.split(" ");
        System.out.printf(
                "%s causes underflow? %s\n",
                directives,
                directiveSequenceCausesStackUnderflow(split)
        );
    }

    public static void reportPossible(String results) {
        String[] split = results.split(" ");
        System.out.printf(
                "%s is possible? %s\n",
                results,
                possibleSequenceOfPops(split)
        );
    }

    public static void main(String[] args) {
        List<String> underflowDirectives = List.of(
                "1 2 - - 3 -",
                "1 2 - - 3 - -",
                "-"
        );
        for (String d : underflowDirectives) reportUnderflow(d);

        List<String> possibleSequence = List.of(
                "4 3 2 1 0 5 6 7 8 9",
                "9 7 0 1 2 3 4 5 6 8",
                "8 7 0 1 2 3 4 5 6 9",
                "0 1 2 3 4 9 8 7 6 5"
        );
        for (String d : possibleSequence) reportPossible(d);
    }
}
