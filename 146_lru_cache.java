/**
 * @formatter:off
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           LRU Cache
 * @id             146
 * @Difficulty     medium
 * @Tags           hash table, linked list, design, doubly-linked list
 * @Featured       Top Interview 150
 * @Link           https://leetcode.com/problems/lru-cache
 * @Link           https://leetcode.com/problems/lru-cache/description/?envType=study-plan-v2&envId=top-interview-150

LRU Cache
    Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

    Implement the LRUCache class:

    LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    int get(int key) Return the value of the key if the key exists, otherwise return -1.
    void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
    The functions get and put must each run in O(1) average time complexity.

Example 1:
    Input
    ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
    [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    Output
    [null, null, null, 1, null, -1, null, -1, 3, 4]

    Explanation
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    lRUCache.get(1);    // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    lRUCache.get(2);    // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    lRUCache.get(1);    // return -1 (not found)
    lRUCache.get(3);    // return 3
    lRUCache.get(4);    // return 4


Constraints:
    1 <= capacity <= 3000
    0 <= key <= 10^4
    0 <= value <= 10^5
    At most 2 * 10^5 calls will be made to get and put.
 @formatter:on
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiConsumer;


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// Choose what to extend - LRUCache0, LRUCache1 or LRUCache2
class LRUCache extends LRUCache0 {

    public LRUCache(int capacity) {
        super(capacity);
    }

    private static class TestData {
        String[] command; // Input
        Integer[][] data; // Input
        Integer[] exp; // Output

        TestData(String[] command, Integer[][] data, Integer[] result) {
            this.command = command;
            this.data = data;
            this.exp = result;
        }
    }

    public static void main(String[] args) {
        TestData[] testDataArray = new TestData[]{
                new TestData(
                        new String[]{"LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"},
                        new Integer[][]{{2}, {1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}},
                        new Integer[]{null, null, null, 1, null, -1, null, -1, 3, 4}
                ),
                new TestData(
                        new String[]{"LRUCache", "put", "get", "put", "get", "get"},
                        new Integer[][]{{1}, {2, 1}, {2}, {3, 2}, {2}, {3}},
                        new Integer[]{null, null, 1, null, -1, 2}
                ),
                new TestData(
                        new String[]{"LRUCache", "get", "put", "get", "put", "put", "get", "get"},
                        new Integer[][]{{2}, {2}, {2, 6}, {1}, {1, 5}, {1, 2}, {1}, {2}},
                        new Integer[]{null, -1, null, -1, null, null, 2, 6}
                ),
                new TestData(
                        new String[]{"LRUCache", "put", "put", "get", "put", "get", "get"},
                        new Integer[][]{{2}, {2, 1}, {1, 1}, {2}, {4, 1}, {1}, {2}},
                        new Integer[]{null, null, null, 1, null, -1, 1}
                ),
                new TestData(
                        new String[]{"LRUCache", "put", "put", "put", "put", "put", "get", "put", "get", "get", "put", "get", "put", "put", "put", "get", "put", "get", "get", "get", "get", "put", "put", "get", "get", "get", "put", "put", "get", "put", "get", "put", "get", "get", "get", "put", "put", "put", "get", "put", "get", "get", "put", "put", "get", "put", "put", "put", "put", "get", "put", "put", "get", "put", "put", "get", "put", "put", "put", "put", "put", "get", "put", "put", "get", "put", "get", "get", "get", "put", "get", "get", "put", "put", "put", "put", "get", "put", "put", "put", "put", "get", "get", "get", "put", "put", "put", "get", "put", "put", "put", "get", "put", "put", "put", "get", "get", "get", "put", "put", "put", "put", "get", "put", "put", "put", "put", "put", "put", "put"},
                        new Integer[][]{{10}, {10, 13}, {3, 17}, {6, 11}, {10, 5}, {9, 10}, {13}, {2, 19}, {2}, {3}, {5, 25}, {8}, {9, 22}, {5, 5}, {1, 30}, {11}, {9, 12}, {7}, {5}, {8}, {9}, {4, 30}, {9, 3}, {9}, {10}, {10}, {6, 14}, {3, 1}, {3}, {10, 11}, {8}, {2, 14}, {1}, {5}, {4}, {11, 4}, {12, 24}, {5, 18}, {13}, {7, 23}, {8}, {12}, {3, 27}, {2, 12}, {5}, {2, 9}, {13, 4}, {8, 18}, {1, 7}, {6}, {9, 29}, {8, 21}, {5}, {6, 30}, {1, 12}, {10}, {4, 15}, {7, 22}, {11, 26}, {8, 17}, {9, 29}, {5}, {3, 4}, {11, 30}, {12}, {4, 29}, {3}, {9}, {6}, {3, 4}, {1}, {10}, {3, 29}, {10, 28}, {1, 20}, {11, 13}, {3}, {3, 12}, {3, 8}, {10, 9}, {3, 26}, {8}, {7}, {5}, {13, 17}, {2, 27}, {11, 15}, {12}, {9, 19}, {2, 15}, {3, 16}, {1}, {12, 17}, {9, 1}, {6, 19}, {4}, {5}, {5}, {8, 1}, {11, 7}, {5, 2}, {9, 28}, {1}, {2, 2}, {7, 4}, {4, 22}, {7, 24}, {9, 26}, {13, 28}, {11, 26}},
                        new Integer[]{null, null, null, null, null, null, -1, null, 19, 17, null, -1, null, null, null, -1, null, -1, 5, -1, 12, null, null, 3, 5, 5, null, null, 1, null, -1, null, 30, 5, 30, null, null, null, -1, null, -1, 24, null, null, 18, null, null, null, null, -1, null, null, 18, null, null, -1, null, null, null, null, null, 18, null, null, -1, null, 4, 29, 30, null, 12, -1, null, null, null, null, 29, null, null, null, null, 17, 22, 18, null, null, null, -1, null, null, null, 20, null, null, null, -1, 18, 18, null, null, null, null, 20, null, null, null, null, null, null, null}
                ),
                new TestData(
                        new String[]{"LRUCache", "get", "get", "put", "get", "put", "put", "put", "put", "get", "put"},
                        new Integer[][]{{1}, {6}, {8}, {12, 1}, {2}, {15, 11}, {5, 2}, {1, 15}, {4, 2}, {5}, {15, 15}},
                        new Integer[]{null, -1, -1, null, -1, null, null, null, null, -1, null}
                ),

        };

        int testNum = 0;
        boolean finalResult = true;
        for (TestData t : testDataArray) {
            System.out.println("Test #" + testNum);
            LRUCache cache = new LRUCache(t.data[0][0]); // The "LRUCache" command
            for (int i = 0; i < t.command.length; i++) {
                String command = t.command[i];
                Integer[] data = t.data[i];
                Integer exp = t.exp[i];
                System.out.printf("%s %s ", command, Arrays.toString(data));
                Integer result = null;
                switch (command) {
                    case "LRUCache":
                        System.out.println();
                        break;
                    case "put":
                        cache.put(data[0], data[1]);
                        System.out.println();
                        break;
                    case "get":
                        result = cache.get(data[0]);
                        if (result == exp) {
                            System.out.printf("     OK: %d-%d\n", data[0], result);
                        } else {
                            System.out.printf("     FAILED: result=%d exp=%d i=%d\n", result, exp, i);
                            finalResult = false;
                        }
                        break;
                }
                if (!cache.debugCheckLinkingCorrect()) {
                    System.out.printf("     STOP: i=%d\n", i);
                    finalResult = false;
                    break;
                }
            }
            testNum++;
            System.out.println();
        }
        System.out.println("------------");
        System.out.println(finalResult ? "PASSED" : "ERRORS PRESENT");
    }
}

/** LRU cache implementation with using own doubly-linked list.
 * The build-in LinkedList doesn't suit, because it doesn't expose its Node subclass,
 * so we need to iterate over the whole list when removing a node.
 * In own implementation there are pointers to the prev and next, so it's easy to remove a node.
 */
class LRUCache0 {
    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        void setNeighbors(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }
    }

    private final int CAPACITY;
    private Map<Integer, Node> map;
    Node head;
    Node tail;

    public LRUCache0(int capacity) {
        CAPACITY = capacity;
        map = new HashMap<>((int) (CAPACITY * 1.5));
    }

    public int get(int key) {
        Node n = map.get(key);
        if (n == null)
            return -1;
        debug("// get=" + key);
        moveToEnd(n);
        return n.value;
    }

    public void put(int key, int value) {
        Node n = new Node(key, value);
        Node exstingNode = map.get(key);

        if (exstingNode != null) {
            debug("// putExisting=" + key);
            exstingNode.value = value;
            moveToEnd(exstingNode);
        } else {
            if (head == null) {
                head = n; // When here means adding to empty map
            } else {
                tail.next = n; // Edit current tail pointer
            }

            if (CAPACITY == map.size()) {
                debug("// evict=" + head.key);
                map.remove(head.key);
                head = head.next;
            } else {
                debug("// addNew=" + key);
            }

            n.setNeighbors(tail, null);
            map.put(key, n);
            tail = n;
        }
    }

    void moveToEnd(Node n) {
        // Already the last. Don't change anything
        if (n == tail)
            return;

        // When "taking out" node from its place (detach the n from neighbors and connect them if needed). Also, adjust head/tail.
        if (n == head) {
            // Was in the beginning
            head = n.next;
        } else {
            // Was in the middle
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }

        // When "inserting" node to the end (attach the n to new neighbors)
        n.setNeighbors(tail, null);
        tail.next = n; // The previous last element now points to current last (to n)
        tail = n; // Mark n as tail
    }

    boolean debugCheckLinkingCorrect() {
        if (head == null || head.next == null)
            return true;
        Node prev = head;
        Node n = head.next;
        while (n != null) {
            if (n == prev || n == n.next) {
                System.out.printf("// Loop detected prev=%d curr=%d next=%d", prev.key, n.key, n.next == null ? -1 : n.next.key);
                return false; // Loop
            }
            int prevNextKey = prev.next.key;
            int currKey = n.key;
            if (currKey != prevNextKey) {
                debug("// Mutual inconsistency");
                return false;
            }
            prev = n;
            n = n.next;
        }
        return true;
    }

    String debugMapToString(boolean needValue) {
        String s = "";
        if (head != null) {
            Node n = head;
            while (n != null) {
                if (!needValue)
                    s += n.key + " ";
                else
                    s += n.key + "-" + n.value + "  ";

                n = n.next;
            }
        }
        return s;
    }

    void debug(String s) {
//        String list = " list=" + debugMapToString(true);
//        System.out.println(s + list);
    }
}

/** LRU cache that uses a doubly-linked list to age elements.
 *
 * This implementation is even slower, than the LRUCache2 with storing aging in int.
 * I got "Time Limit Exceeded" error on the leetcode on the test with capacity=1101.
 * This is because of removing a node from the LinkedList takes O(n) */
class LRUCache1 {
    private final int CAPACITY;
    private Map<Integer, Integer> map;
    private LinkedList<Integer> list;

    public LRUCache1(int capacity) {
        CAPACITY = capacity;
        map = new HashMap<>((int) (CAPACITY * 1.5));
        list = new LinkedList<>();
    }

    public int get(int key) {
        Integer value = map.get(key);
        if (value == null) {
            return -1;
        } else {
            updatePriority(key);
            return value;
        }
    }

    public void put(int key, int value) {
        if (map.size() >= CAPACITY) {
            if (!map.containsKey(key)) {
                Integer removedKey = list.poll();
                map.remove(removedKey);
                list.add(key);
            } else {
                updatePriority(key);
            }
            map.put(key, value);
        } else {
            Integer previous = map.put(key, value);
            if (previous == null)
                list.add(key);
            else
                updatePriority(key);
        }
    }

    private void updatePriority(int key) {
        Integer element = null;
        int i = 0;

        // Yeah, sadly still have O(n) for updating a key
        for (; i < list.size(); i++) {
            Integer integer = list.get(i);
            if (integer.intValue() == key) {
                element = integer;
                break;
            }
        }

        if (element != null) {
            list.remove(i);
            list.add(element);
        }
    }
}

/** LRU cache that uses an int value to store age.
 *
 * This implementation suites only for small amount of data.
 * "Time Limit Exceeded" faced on a test with capacity=3000.
 * Need to use DoubleLinkedList instead of "aging" every element in map*/
class LRUCache2 {
    private final int CAPACITY;
    private Map<Integer, Integer[]> map; // key, [value, age]
    private Integer oldestKey;
    private Ager ager = new Ager();

    public LRUCache2(int capacity) {
        CAPACITY = capacity;
        map = new HashMap<>((int) (CAPACITY * 1.5));
    }

    public int get(int key) {
        Integer[] a = map.get(key);
        if (a == null) {
            return -1;
        }
        a[1] = 0;
        map.put(key, a);
        oldestKey = ager.age(map);
        return a[0];
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (map.size() == CAPACITY) {
                map.remove(oldestKey);
            }
        }
        map.put(key, new Integer[]{value, 0});
        oldestKey = ager.age(map);
    }

    private class Ager implements BiConsumer<Integer, Integer[]> {
        Integer oldestKey;
        Integer maxAge;


        @Override
        public void accept(Integer k, Integer[] v) {
            v[1]++;
            if (v[1] > maxAge) {
                maxAge = v[1];
                oldestKey = k;
            }
        }

        Integer age(Map<Integer, Integer[]> map) {
            maxAge = 0;
            map.forEach((k, v) -> this.accept(k, v));
            return oldestKey;
        }
    }
}
