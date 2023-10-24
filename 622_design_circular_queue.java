/**
 * @formatter:off
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Design Circular Queue
 * @id             622
 * @Difficulty     medium
 * @Tags           array, linked list, design, queue
 * @Link           https://leetcode.com/problems/design-circular-queue/

Design Circular Queue
    Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle, and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

    One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.

    Implement the MyCircularQueue class:
    - MyCircularQueue(k) Initializes the object with the size of the queue to be k.
    - int Front() Gets the front item from the queue. If the queue is empty, return -1.
    - int Rear() Gets the last item from the queue. If the queue is empty, return -1.
    - boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
    - boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
    - boolean isEmpty() Checks whether the circular queue is empty or not.
    - boolean isFull() Checks whether the circular queue is full or not.
    You must solve the problem without using the built-in queue data structure in your programming language.



Example 1:
    Input
    ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
    [[3], [1], [2], [3], [4], [], [], [], [4], []]

    Output
    [null, true, true, true, false, 3, true, true, true, 4]

    Explanation
    MyCircularQueue myCircularQueue = new MyCircularQueue(3);
    myCircularQueue.enQueue(1); // return True
    myCircularQueue.enQueue(2); // return True
    myCircularQueue.enQueue(3); // return True
    myCircularQueue.enQueue(4); // return False
    myCircularQueue.Rear();     // return 3
    myCircularQueue.isFull();   // return True
    myCircularQueue.deQueue();  // return True
    myCircularQueue.enQueue(4); // return True
    myCircularQueue.Rear();     // return 4


Constraints:
    1 <= k <= 1000
    0 <= value <= 1000
    At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.

 @formatter:on
 */

class MyCircularQueue {
    private final int[] a;
    final private int K;
    private int size = 0;
    private int front = 0; // When dequeue, we remove the `front` element.
    private int rear; // When enqueue, we add to the rear. The current`rear` points to the LAST OCCUPIED position, not a position where we would put.

    /* In order to understand to what position we add and remove -
    think of the queue entities are the occupied data, not a free positions.  */
    public MyCircularQueue(int k) {
        a = new int[k];
        K = k;
        rear = K - 1; // Will be incremented to 0th element at the 1st iteration.
    }

    public boolean enQueue(int value) {
        if (size == K)
            return false;
        rear = (rear + 1) % K;
        a[rear] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (size == 0)
            return false;
        front = (front + 1) % K;
        size--;
        return true;
    }

    public int Front() {
        if (size == 0)
            return -1;
        return a[front];
    }

    public int Rear() {
        if (size == 0)
            return -1;
        return a[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == K;
    }

    private static class TestData {
        String[] command; // Input
        Integer[] arg; // Input
        Integer[] exp; // Output

        TestData(String[] command, Integer[] arg, Integer[] result) {
            this.command = command;
            this.arg = arg;
            this.exp = result;
        }
    }

    public static void main(String[] args) {
        TestData[] testDataArray = new TestData[]{
                new TestData(
                        new String[]{"MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"},
                        new Integer[]{3, 1, 2, 3, 4, null, null, null, 4, null},
                        new Integer[]{null, 1, 1, 1, 0, 3, 1, 1, 1, 4}
                ),
                new TestData(
                        new String[]{"MyCircularQueue", "enQueue", "Rear", "Front", "deQueue", "Front", "deQueue", "Front", "enQueue", "enQueue", "enQueue", "enQueue"},
                        new Integer[]{3, 2, null, null, null, null, null, null, 4, 2, 2, 3},
                        new Integer[]{null, 1, 2, 2, 1, -1, 0, -1, 1, 1, 1, 0}
                ),
                new TestData(
                        new String[]{"MyCircularQueue", "enQueue", "deQueue", "Front", "deQueue", "Front", "Rear", "enQueue", "isFull", "deQueue", "Rear", "enQueue"},
                        new Integer[]{3, 7, null, null, null, null, null, 0, null, null, null, 3},
                        new Integer[]{null, 1, 1, -1, 0, -1, -1, 1, 0, 1, -1, 1}
                ),
        };
        int testNum = 0;
        for (TestData t : testDataArray) {
            System.out.println("Test #" + testNum);
            MyCircularQueue q = new MyCircularQueue(t.arg[0]); // The "MyCircularQueue" command
            for (int i = 0; i < t.command.length; i++) {
                String command = t.command[i];
                Integer arg = t.arg[i];
                Integer exp = t.exp[i];
                System.out.printf("%s %d:", command, arg);

                Integer result = null;
                switch (command) {
                    case "MyCircularQueue":
                        break;
                    case "enQueue":
                        result = q.enQueue(arg) ? 1 : 0;
                        break;
                    case "deQueue":
                        result = q.deQueue() ? 1 : 0;
                        break;
                    case "Front":
                        result = q.Front();
                        break;
                    case "Rear":
                        result = q.Rear();
                        break;
                    case "IsEmpty":
                        result = q.isEmpty() ? 1 : 0;
                        break;
                    case "isFull":
                        result = q.isFull() ? 1 : 0;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + command);
                }
                if (result == exp) {
                    System.out.printf("     OK result=%d\n", result);
                } else {
                    System.out.printf("     FAILED result=%d exp=%d i=%d\n", result, exp, i);
                }
            }
            testNum++;
            System.out.println();
        }
    }
}
