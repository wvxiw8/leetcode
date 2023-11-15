/**
 * @formatter:off
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Task Scheduler
 * @id             621
 * @Difficulty     medium
 * @Tags           array, hash table, greedy, sorting, heap (priority queue), counting
 * @Link           https://leetcode.com/problems/task-scheduler/

Task Scheduler
    Given a characters array `tasks`, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
    However, there is a non-negative integer `n` that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least `n` units of time between any two same tasks.
    Return the least number of units of times that the CPU will take to finish all the given tasks.

Example 1:
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation:
    A -> B -> idle -> A -> B -> idle -> A -> B
    There is at least 2 units of time between any two same tasks.

Example 2:
    Input: tasks = ["A","A","A","B","B","B"], n = 0
    Output: 6
    Explanation: On this case any permutation of size 6 would work since n = 0.
    ["A","A","A","B","B","B"]
    ["A","B","A","B","A","B"]
    ["B","B","B","A","A","A"]
    ...
    And so on.

Example 3:
    Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
    Output: 16
    Explanation:
    One possible solution is
    A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A


Constraints:
    1 <= task.length <= 10^4
    tasks[i] is upper-case English letter.
    The integer n is in the range [0, 100].

 @formatter:on
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : tasks) {
            Integer repeats = map.get(c);
            if (repeats == null)
                repeats = 0;
            map.put(c, repeats + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(map.size(), (e1, e2) -> e2.getValue() - e1.getValue());
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            queue.add(entry);

        final int N = n + 1;
        final Map.Entry<Character, Integer>[] entries = new Map.Entry[N];
        int interval = 0;
        int numExisted = 0;

        while (!queue.isEmpty()) {
            numExisted = 0;

            // Poll N entries from the queue
            for (int i = 0; i < N; i++) {
                entries[i] = queue.poll();
                if (entries[i] != null)
                    numExisted++;
            }

            // Update <numExisted> entries and put back to the queue
            for (int i = 0; i < numExisted; i++) {
                Integer v = entries[i].getValue();
                if (v > 1) {
                    entries[i].setValue(--v);
                    queue.add(entries[i]);
                }
            }
            interval += N;

        }
        interval -= N - numExisted;

        return interval;
    }

    
    private static class TestData {
        char[] tasks;
        int n;
        int exp;

        TestData(char[] tasks, int n, int expected) {
            this.tasks = tasks;
            this.n = n;
            this.exp = expected;
        }
    }

    public static void main(String[] args) {
        TestData[] testDataArray = new TestData[]{
                new TestData(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2, 8),
                new TestData(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0, 6),
                new TestData(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2, 16),
        };
        Solution solution = new Solution();
        int testNum = 0;

        for (TestData t : testDataArray) {
            System.out.printf("#%d ", testNum);
            int leastInterval = solution.leastInterval(t.tasks, t.n);

            for (int i = 0; i < t.tasks.length; i++)
                System.out.printf("%c", t.tasks[i]);
            System.out.printf(" n=%d exp=%d\n", t.n, t.exp);


            if (leastInterval == t.exp) {
                System.out.printf("     OK result=%d\n", leastInterval);
            } else {
                System.out.printf("     FAILED result=%d exp=%d\n", leastInterval, t.exp);
            }
            testNum++;
            System.out.println();
        }
    }
}
