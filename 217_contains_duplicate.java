/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Contains Duplicate
 * @id             217
 * @Difficulty     Easy
 * @Tags           array, hash table, sorting
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/578/
 
Contains Duplicate

    Given an integer array nums, return true if any value appears at least twice in the array, 
    and return false if every element is distinct.


Example 1:

    Input: nums = [1,2,3,1]
    Output: true

Example 2:

    Input: nums = [1,2,3,4]
    Output: false

Example 3:

    Input: nums = [1,1,1,3,3,4,3,2,4,2]
    Output: true


Constraints:

    1 <= nums.length <= 10^5
    -10^9 <= nums[i] <= 10^9

 */

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet();
        for (int i : nums) {
            if (!set.add(i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int a[][][] = {
            {{1,2,3,1}, {1}},
            {{1,2,3,4}, {0}},
            {{1,1,1,3,3,4,3,2,4,2}, {1}},
        };
        Solution solution = new Solution();
        for (int i = 0; i < a.length; ++i) {
            System.out.print(Arrays.toString(a[i][0]));
            boolean res = solution.containsDuplicate(a[i][0]);
            if (res == (a[i][1][0] != 0)) {
                System.out.println(" OK");
            } else {
                System.out.println(" Failed");
            }
        }
    }
}
