/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Kth Largest Element in an Array
 * @id             215
 * @Difficulty     Medium
 * @Tags           array, divide and conquer, sorting, heap (priority queue), quickselect
 * @Link           https://leetcode.com/problems/kth-largest-element-in-an-array/

Kth Largest Element in an Array

    Given an integer array nums and an integer k, return the kth largest element in the array.
    Note that it is the kth largest element in the sorted order, not the kth distinct element.
    Can you solve it without sorting?


Example 1:

    Input: nums = [3,2,1,5,6,4], k = 2
    Output: 5

Example 2:

    Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
    Output: 4


Constraints:

    1 <= k <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
*/

import java.util.Arrays;
import com.wvxiw.leetcode.datastructures.Heap;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        Heap heap = new Heap(nums);
        heap.build(nums);
        int result = nums[0];
        while (k > 0) {
            result = heap.extractTop();
            k--;
        }
        return result;
    }

    public static void main(String[] args) {
        /* {{nums}, {k}, {output/expected}} */
        int [][][] data = {
                {{3,2,1,5,6,4}, {2}, {5}},
                {{3,2,3,1,2,4,5,5,6}, {4}, {4}},
                {{3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6}, {20}, {2}},
        };

        Solution solution = new Solution();
        for (int[][] d : data) {
            int[] nums = d[0];
            int k = d[1][0];
            int exp = d[2][0];
            System.out.print("k=" + k + " nums=" + Arrays.toString(nums) + "\n");
            int result = solution.findKthLargest(nums, k);
            if (result == exp) {
                System.out.println("   OK");
            } else {
                System.out.print("   Failed");
                System.out.print(" expected=" + exp);
                System.out.println(" result=" + result);
            }
        }
    }
}
