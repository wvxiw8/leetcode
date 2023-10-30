/**
 * @formatter:off
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Peeking Iterator
 * @id             284
 * @Difficulty     medium
 * @Tags           array, design, iterator
 * @Link           https://leetcode.com/problems/peeking-iterator/

Peeking Iterator
    Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.
    Implement the `PeekingIterator` class:
        - `PeekingIterator(Iterator<int> nums)` Initializes the object with the given integer iterator `iterator`.
        - `int next()` Returns the next element in the array and moves the pointer to the next element.
        - `boolean hasNext()` Returns true if there are still elements in the array.
        - `int peek()` Returns the next element in the array without moving the pointer.
    Note: Each language may have a different implementation of the constructor and `Iterator`, but they all support the `int next()` and `boolean hasNext()` functions.

Example 1:
    Input
        ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
        [[[1, 2, 3]], [], [], [], [], []]
    Output
        [null, 1, 2, 2, 3, false]

    Explanation
        PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
        peekingIterator.next();    // return 1, the pointer moves to the next element [1,2,3].
        peekingIterator.peek();    // return 2, the pointer does not move [1,2,3].
        peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,3]
        peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
        peekingIterator.hasNext(); // return False

Constraints:
    1 <= nums.length <= 1000
    1 <= nums[i] <= 1000
    All the calls to next and peek are valid.
    At most 1000 calls will be made to next, hasNext, and peek.

Follow up: How would you extend your design to be generic and work with all types, not just integer?

Hint 1
    Think of "looking ahead". You want to cache the next element.
Hint 2
    Is one variable sufficient? Why or why not?
Hint 3
    Test your design with call order of peek() before next() vs next() before peek().
Hint 4
    For a clean implementation, check out Google's guava library source code.

 @formatter:on
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iter;
    private Integer pointer; // Needed when invoking next() for the last element
    private Integer curr;

    public PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
        curr = iter.next();
        pointer = curr;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return curr;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() throws NoSuchElementException {
        if (pointer == null) {
            throw new NoSuchElementException();
        }
        Integer ret = curr;
        pointer = iter.hasNext() ? iter.next() : null;
        curr = pointer;
        return ret;
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext() || pointer != null;
    }

    private static Integer boolToInt(boolean b) {
        return b == true ? 1 : 0;
    }

    private static class TestData {
        String[] command; // Input
        Integer[][] arg; // Input
        Integer[] exp; // Output

        TestData(String[] command, Integer[][] arg, Integer[] result) {
            this.command = command;
            this.arg = arg;
            this.exp = result;
        }
    }

    public static void main(String[] args) {
        TestData[] testDataArray = new TestData[]{
                new TestData(
                        new String[]{"PeekingIterator", "next", "peek", "next", "next", "hasNext"},
                        new Integer[][]{{1, 2, 3}, {}, {}, {}, {}, {}},
                        new Integer[]{null, 1, 2, 2, 3, boolToInt(false)}
                ),
                new TestData(
                        new String[]{"PeekingIterator", "hasNext", "peek", "peek", "next", "next", "peek", "peek", "next", "hasNext", "peek", "hasNext", "next", "hasNext"},
                        new Integer[][]{{1, 2, 3, 4}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}},
                        new Integer[]{null, boolToInt(true), 1, 1, 1, 2, 3, 3, 3, boolToInt(true), 4, boolToInt(true), 4, boolToInt(false)}
                ),
        };
        int testNum = 0;
        boolean success = true;
        for (TestData t : testDataArray) {
            System.out.println("Test #" + testNum);
            ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(t.arg[0]));
            PeekingIterator iter = new PeekingIterator(arrayList.iterator()); // The "PeekingIterator" command
            for (int i = 0; i < t.command.length; i++) {
                String command = t.command[i];
                Integer[] arg = t.arg[i];
                Integer exp = t.exp[i];
                System.out.printf("%s %s:", command, Arrays.toString(arg));

                Integer result = null;
                switch (command) {
                    case "PeekingIterator":
                        break;
                    case "hasNext":
                        result = iter.hasNext() ? 1 : 0;
                        break;
                    case "next":
                        result = iter.next();
                        break;
                    case "peek":
                        result = iter.peek();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + command);
                }
                if (result == exp) {
                    System.out.printf("     OK result=%d\n", result);
                } else {
                    success = false;
                    System.out.printf("     FAILED result=%d exp=%d (i=%d)\n", result, exp, i);
                }
            }
            testNum++;
            System.out.println("---------");
            System.out.println(success ? "OK" : "FAILED");
            System.out.println();
        }
    }
}
