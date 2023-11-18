/**
 * @formatter:off
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Exclusive Time of Functions
 * @id             636
 * @Difficulty     Medium
 * @Tags           array, stack
 * @Link           https://leetcode.com/problems/exclusive-time-of-functions

Exclusive Time of Functions
    On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.

    Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.

    You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.

    A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.

    Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.


Example 1:
    Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
    Output: [3,4]
    Explanation:
    Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
    Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
    Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
    So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.

Example 2:
    Input: n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
    Output: [8]
    Explanation:
    Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
    Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
    Function 0 (initial call) resumes execution then immediately calls itself again.
    Function 0 (2nd recursive call) starts at the beginning of time 6 and executes for 1 unit of time.
    Function 0 (initial call) resumes execution at the beginning of time 7 and executes for 1 unit of time.
    So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.

Example 3:
    Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
    Output: [7,1]
    Explanation:
    Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
    Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
    Function 0 (initial call) resumes execution then immediately calls function 1.
    Function 1 starts at the beginning of time 6, executes 1 unit of time, and ends at the end of time 6.
    Function 0 resumes execution at the beginning of time 6 and executes for 2 units of time.
    So function 0 spends 2 + 4 + 1 = 7 units of total time executing, and function 1 spends 1 unit of total time executing.


Constraints:
    1 <= n <= 100
    1 <= logs.length <= 500
    0 <= function_id < n
    0 <= timestamp <= 10^9
    No two start events will happen at the same timestamp.
    No two end events will happen at the same timestamp.
    Each function has an "end" log for each "start" log.
 @formatter:on
 */


import java.util.*;


class Solution {
    public static final int NOT_STARTED = Integer.MIN_VALUE; // Used to set interruptedTime;
    public static final int START = 0;
    public static final int END = 1;

    private static class Event {
        int id;
        int cmd;
        int time;

        Event(String log) {
            String[] values = log.split(":");
            id = Integer.parseInt(values[0]);
            cmd = values[1].equals("start") ? START : END;
            time = Integer.parseInt(values[2]);
        }
    }

    private static class Function {
        int id;
        int duration = 0;
        int interruptTime;

        Function(int id, int interruptedTime) {
            this.id = id;
            this.interruptTime = interruptedTime;
        }

        void printStateLn(String begin, String end) {
            printState(begin, end);
            System.out.println();
        }

        void printState(String begin, String end) {
            System.out.printf("%sid=%d dur=%d itr=%d%s", begin == null ? "" : begin, id, duration, interruptTime, end == null ? "" : end);
        }

        void addDuration(Integer duration) {
//            System.out.printf("id=%d dur=%d+%d=%d\n", this.id, this.duration, duration, this.duration + duration);
            this.duration += duration;
        }
    }

    void printStack(Stack<Function> stack) {
        System.out.println("  Stack:");
        stack.forEach(f -> f.printStateLn("    ", null));
    }

    void printFullState(String eventStr, HashMap<Integer, Function> map, Stack<Function> stack) {
        System.out.println(eventStr);
        Map<Integer, Function> sortedMap = new TreeMap<>(map);
        sortedMap.forEach((id, func) -> {
            func.printStateLn("    ", null);
        });
        printStack(stack);
    }


    public int[] exclusiveTime(int n, List<String> logs) {
        var map = new HashMap<Integer, Function>(n);
        Stack<Function> stack = new Stack<>();

        Event e = new Event(logs.get(0));

        Function curr;
        Function prev = new Function(e.id, 0);
        map.put(prev.id, prev);
        stack.push(prev);

        int prevTime = e.time;
        int prevCmd = e.cmd;

        for (int i = 1; i < logs.size(); i++) {
            e = new Event(logs.get(i));

            curr = map.get(e.id);
            if (curr == null) {
                curr = new Function(e.id, e.time);
                map.put(curr.id, curr);
            }

            if (e.cmd == START) {
                prev = stack.isEmpty() ? null : stack.peek();
                stack.push(curr);

                if (prev != null) {
                    if (prevCmd == END && e.time != prevTime + 1) { // Was a gap. The prev function was executing
                        prev.addDuration(e.time - prevTime - 1);
                        prev.interruptTime = e.time;  // prev now suspended
                    } else if (prev.interruptTime != NOT_STARTED) {
                        prev.addDuration(e.time - prev.interruptTime);
                        prev.interruptTime = e.time; // prev now suspended
                    }
                }
                curr.interruptTime = e.time; // curr started
            } else /* (e.cmd == END) */ {
                stack.pop();
                if (prevCmd == START) {
                    curr.addDuration(e.time - curr.interruptTime + 1);
                } else {
                    curr.addDuration(e.time - prevTime);
                }
                curr.interruptTime = NOT_STARTED;

                prev = stack.isEmpty() ? null : stack.pop();
                if (prev != null) {
                    prev.interruptTime = e.time + 1;
                    stack.push(prev);
                }
            }

            prevTime = e.time;
            prevCmd = e.cmd;

//            printFullState(logs.get(i), map, stack);
        }

        int[] result = new int[n];
        map.forEach((id, function) -> {
            result[id] = function.duration;
        });
        return result;
    }

    private static class TestData {
        int n;
        List<String> logs;
        int[] expected;

        TestData(int n, List<String> logs, int[] expected) {
            this.n = n;
            this.expected = expected;
            this.logs = logs;
        }
    }


    public static void main(String[] args) {
        TestData[] testData = {
                /* Bot start and end are inclusive. E.g. "1:start:2" "1:end:5" means F1 lasts four slots: 2,3,4,5 */
                new TestData(2, List.of("0:start:0", "1:start:2", "1:end:5", "0:end:6"), new int[]{3, 4}), // 1st
                new TestData(1, List.of("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7"), new int[]{8}), // 2nd
                new TestData(2, List.of("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"), new int[]{7, 1}), // 3rd
                new TestData(2, List.of("0:start:0", "0:start:2", "0:end:5", "1:start:7", "1:end:7", "0:end:8"), new int[]{8, 1}), // 4th
                new TestData(2, List.of("0:start:0", "1:start:2", "1:end:5", "1:start:7", "1:end:7", "0:end:8"), new int[]{4, 5}), /* own test */
                new TestData(3, List.of("0:start:0", "1:start:1", "2:start:2", "2:end:2", "1:end:3", "0:end:4"), new int[]{2, 2, 1}), /* own test */
                new TestData(2, List.of("0:start:0", "0:end:2", "1:start:3", "1:end:4"), new int[]{3, 2}), /* own test */
                new TestData(2, List.of("0:start:0", "0:end:2", "0:start:3", "0:end:4", "1:start:5", "0:start:6", "0:end:6", "1:end:7"), new int[]{6, 2}), /* own test */
                new TestData(1, List.of("0:start:0", "0:end:2", "0:start:3", "0:end:4"), new int[]{5}), /* own test */
                new TestData(3, List.of("0:start:0", "1:start:1", "2:start:2", "2:end:2", /*1:lasts:3*/ "0:start:4", "0:end:4", /*1:lasts:5*/ "1:start:6", "1:end:6", "1:end:7", "0:end:8"), new int[]{3, 5, 1}), /* own test */
                new TestData(5, List.of("0:start:0", "1:start:2", "2:start:4", "3:start:6", "4:start:8", "1:start:10", "1:end:11", "4:end:13", "3:end:15", "2:end:17", "1:end:19", "0:end:21"), new int[]{4, 6, 4, 4, 4}), /* own test */
                new TestData(3, List.of("0:start:0", "1:start:1", "2:start:2", "2:end:2", /*1:lasts:3*/ "0:start:4", "0:end:4", /*1:lasts:5*/ "1:start:6", "1:end:6", /*1:lasts:7*/ "0:start:8", "0:end:8", "1:end:9", "0:end:10"), new int[]{4, 6, 1}), /* own test */
                new TestData(3, List.of("0:start:0", "0:end:0", "1:start:1", "1:end:1", "2:start:2", "2:end:2", "2:start:3", "2:end:3"), new int[]{1, 1, 2}), // 10th
                new TestData(8, List.of("0:start:0", "1:start:5", "2:start:6", "3:start:9", "4:start:11", "5:start:12", "6:start:14", "7:start:15", "1:start:24", "1:end:29", "7:end:34", "6:end:37", "5:end:39", "4:end:40", "3:end:45", "0:start:49", "0:end:54", "5:start:55", "5:end:59", "4:start:63", "4:end:66", "2:start:69", "2:end:70", "2:start:74", "6:start:78", "0:start:79", "0:end:80", "6:end:85", "1:start:89", "1:end:93", "2:end:96", "2:end:100", "1:end:102", "2:start:105", "2:end:109", "0:end:114"), new int[]{20, 14, 35, 7, 6, 9, 10, 14}),
                new TestData(1, List.of("0:start:0", "0:start:3", "0:end:6", "0:end:10"), new int[]{11}), /* own test */
                new TestData(2, List.of("0:start:0", "1:start:2", "1:start:4", "1:start:5", "1:end:10", "1:end:12", "1:end:16", "0:end:18"), new int[]{4, 15}), /* own test */
                new TestData(1, List.of("0:start:0", "0:start:2", "0:end:4", "0:start:6", "0:end:10", "0:end:14"), new int[]{15}), /* own test */
                new TestData(2, List.of("0:start:0", "1:start:3", "1:end:5", "0:start:8", "0:end:10", "1:start:11", "1:end:15", "0:start:18", "0:end:20", "0:end:24"), new int[]{17, 8}), /* own test */
                new TestData(9, List.of("0:start:0", "1:start:5", "2:start:8", "3:start:12", "4:start:15", "5:start:19", "6:start:22", "7:start:24", "8:start:27", "6:start:32", "6:end:35", "8:end:39", "2:start:40", "5:start:42", "5:end:46", "2:end:49", "7:end:52", "7:start:54", "7:end:55", "0:start:59", "1:start:64", "1:end:65", "0:end:68", "2:start:71", "7:start:75", "6:start:79", "6:start:81", "6:end:85", "6:end:86", "7:start:88", "7:end:92", "2:start:97", "2:end:102", "1:start:105", "7:start:107", "8:start:111", "8:end:115", "7:end:118", "1:end:121", "7:end:122", "2:end:126", "6:end:129", "5:end:130", "4:start:132", "4:end:133", "4:end:135", "3:end:140", "1:start:145", "1:end:147", "2:end:148", "1:end:151", "2:start:156", "1:start:160", "4:start:164", "2:start:165", "7:start:169", "7:end:172", "2:end:175", "4:end:179", "1:start:182", "4:start:183", "4:end:184", "1:end:186", "1:end:187", "2:end:190", "0:end:192"), new int[]{19, 26, 42, 8, 16, 9, 23, 36, 14}), // 25th
        };

        Solution s = new Solution();
        for (TestData t : testData) {
            int[] result = s.exclusiveTime(t.n, t.logs);
            if (Arrays.equals(result, t.expected)) {
                System.out.println("OK");
            } else {
                System.out.println("FAILED");
                System.out.printf("   res=");
                Arrays.stream(result).forEach(x -> {
                    System.out.printf(" %d", x);
                });
                System.out.printf("\n   exp=");
                Arrays.stream(t.expected).forEach(x -> {
                    System.out.printf(" %d", x);
                });
                System.out.println();
            }
        }
    }
}
