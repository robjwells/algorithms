# Skipped exercises

| Exercise ID | Reason             |
|-------------|--------------------|
| 1.1.25      | Mathematical proof |
| 1.1.36      | Experimental       |
| 1.1.37      | Experimental       |
| 1.1.38      | Experimental       |
| 1.1.39      | Experimental       |
| 1.2.18      | Experimental/Stats |
| 1.3.49      | Very complicated   |

## Elaboration

### 1.3.49: “Queue with a constant number of stacks.”

This is marked with “*Warning*: high degree of difficulty” in the text.

[Rene Argento][1.3.49-rene] has solved it using the algorithm described in the Hood/Melville paper “[Real Time Queue Operations in Pure LISP][hood-melville]” which uses six stacks to implement a queue to achieve constant-time operations. I’ve quickly read the paper and the algorithm is not obvious to me, nor Rene’s Java implementation.

Apparently one the printings of Algorithms 4E had the question as using three stacks. There is [a StackOverflow question][1.3.49-so] where the consensus (including from one of Sedgewick’s colleagues) is that three stacks appears impossible without lazy evaluation. 

This is one for further study at a relaxed pace.

[1.3.49-rene]: https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter1/section3/Exercise49_QueueWithStacks.java
[hood-melville]: https://ecommons.cornell.edu/bitstream/handle/1813/6273/80-433.pdf
[1.3.49-so]: https://stackoverflow.com/questions/5538192/how-to-implement-a-queue-with-three-stacks/
