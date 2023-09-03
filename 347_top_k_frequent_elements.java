/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Top K Frequent Elements
 * @id             347
 * @Difficulty     Medium
 * @Tags           array, hash table, divide and conquer, sorting, heap (priority queue), bucket sort, counting, quickselect
 * @Link           https://leetcode.com/problems/top-k-frequent-elements/

Top K Frequent Elements

    Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.


Example 1:

    Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]

Example 2:

    Input: nums = [1], k = 1
    Output: [1]


Constraints:

    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
    k is in the range [1, the number of unique elements in the array].
    It is guaranteed that the answer is unique.


Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    // Using hash table
    public int[] topKFrequent1(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int key: nums) {
            Integer quantity = map.get(key);
            if (quantity == null)
                quantity = 0;
            map.put(key, quantity+1);
        }

        int[] result = map.entrySet()
                .stream()
                .sorted((a,b) -> Integer.compare(b.getValue(), a.getValue()))
                .limit(k)
                .map(Map.Entry::getKey)
                .mapToInt(Integer::intValue)
                .toArray();
        return result;
    }

    // Using heap (priority queue)
    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int key: nums) {
            Integer quantity = map.get(key);
            if (quantity == null)
                quantity = 0;
            map.put(key, quantity+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(map.size(), (e1,  e2 ) -> e2.getValue() - e1.getValue());
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            heap.add(entry);
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = heap.poll();
            result[i] = entry.getKey();
        }
        return result;
    }


    public int[] topKFrequent(int[] nums, int k) {
//        return topKFrequent1(nums, k); // Using hash table
        return topKFrequent2(nums, k); // Using heap (priority queue)
    }


    public static void main(String[] args) {
        /* {{nums}, {k}, {output/expected}} */
        int [][][] data = {
                {{1,1,1,2,2,3}, {2}, {1,2}},
                {{1}, {1}, {1}},
                {{-1,-1}, {1}, {-1}}
        };

        Solution solution = new Solution();
        for (int[][] d : data) {
            int[] nums = d[0];
            int k = d[1][0];
            int[] exp = d[2];
            int[] result = solution.topKFrequent(nums, k);
            System.out.print("k=" + k + " nums=" + Arrays.toString(nums));

             if (Arrays.equals(exp, result)) {
                 System.out.println("   OK");
             } else {
                 System.out.println("   Failed ");
                 System.out.println(" expected=" + Arrays.toString(exp));
                 System.out.println("   result=" + Arrays.toString(result));
             }
        }
    }
}
