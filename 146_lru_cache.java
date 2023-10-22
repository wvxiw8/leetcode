/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           LRU Cache
 * @id             146
 * @Difficulty     medium
 * @Tags           hash table, linked list, design, doubly-linked list
 * Featured        Top Interview 150
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
*/

import java.util.*;
import java.util.function.BiConsumer;


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class LRUCache extends LRUCache1 {

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
//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(1, 1); // cache is {1=1}
//        lRUCache.put(2, 2); // cache is {1=1, 2=2}
//        lRUCache.get(1);    // return 1
//        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//        lRUCache.get(2);    // returns -1 (not found)
//        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//        lRUCache.get(1);    // return -1 (not found)
//        lRUCache.get(3);    // return 3
//        lRUCache.get(4);    // return 4
        if (false)
            return;
        TestData[] testDataArray = new TestData[]{
                new TestData(
                        new String[] {"LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"},
                        new Integer[][] {{2}, {1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}},
                        new Integer[] {null, null, null, 1, null, -1, null, -1, 3, 4}
                ),
                new TestData(
                        new String[] {"LRUCache", "put", "get", "put", "get", "get"},
                        new Integer[][] {{1}, {2,1}, {2}, {3,2}, {2}, {3}},
                        new Integer[] {null, null, 1, null, -1, 2}
                ),
                new TestData(
                        new String[] {"LRUCache","get","put","get","put","put","get","get"},
                        new Integer[][] {{2},{2},{2,6},{1},{1,5},{1,2},{1},{2}},
                        new Integer[] {null,-1,null,-1,null,null,2,6}
                ),
        };

        for (TestData t :testDataArray) {
            LRUCache cache = new LRUCache(t.data[0][0]); // The "LRUCache" command
            for (int i = 0; i < t.command.length ; i++) {
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
                        if (result == exp)
                            System.out.println("     OK: " + result.toString());
                        else
                            System.out.printf("     FAILED: result=%d exp=%d\n", result, exp);
                        break;
                }
            }
            System.out.println();
        }
    }
}
/** LRU cache that uses a doubly-linked list to age elements.
 *
 * This implementation is even slower, than the LRUCache2 with storing aging in int.
 * I got "Time Limit Exceeded" error on the leetcode on the test with capacity=1101.
 * This is because of removing a node from the LinkedList takes O(n) */
class LRUCache1 {
    private final int CAPACITY;
    private Map <Integer, Integer> map;
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
            Integer previous = map.put(key,value);
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
    private Map <Integer, Integer[]> map; // key, [value, age]
    private  Integer oldestKey;
    private Ager ager = new Ager();
    public LRUCache2(int capacity) {
        CAPACITY = capacity;
        map = new HashMap<>((int) (CAPACITY * 1.5));
    }

    public int get(int key) {
        Integer[] a = map.get(key);
        if (a == null) {
            return  -1;
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
        map.put(key, new Integer[] {value, 0});
        oldestKey = ager.age(map);
    }

    private class Ager implements BiConsumer <Integer, Integer[]> {
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
