import java.util.HashMap;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Two Sum
 * @id             204
 * @Difficulty     Easy
 * @Tags           array
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/546/

Two Sum

    Given an array of integers nums and an integer target, 
    return indices of the two numbers such that they add up to target.

    You may assume that each input would have exactly one solution, 
    and you may not use the same element twice.

    You can return the answer in any order.


Example 1:

    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:

    Input: nums = [3,2,4], target = 6
    Output: [1,2]

Example 3:

    Input: nums = [3,3], target = 6
    Output: [0,1]

 

Constraints:

    2 <= nums.length <= 104
    -109 <= nums[i] <= 109
    -109 <= target <= 109
    Only one valid answer exists.

 
Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */


class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = {Integer.MAX_VALUE,Integer.MAX_VALUE};
        HashMap <Integer, Integer> hashMap = new HashMap<>();
        Integer index;

        for (int i = 0; i < nums.length; ++i) {
            index = hashMap.get(nums[i]);
            if (index == null) {
                hashMap.put(target - nums[i], i);
            } else {
                result[0] = index;
                result[1] = i;
                break;
            }
        }
        return result;
    }
public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {-5,0,2,7,11,-1,15,0};

        int target = 9; int[] answer = {2,3};
        // int target = -6; int[] answer = {0,5};
        // int target = 0; int[] answer = {1,7};

        System.out.print("Array: "); 
        for (int i : nums) {
            System.out.print(i + " ");
        }
        
        int[] indexes = solution.twoSum(nums, target);
        System.out.printf("\nCalculation [%d][%d] %d+%d=%d %d? \nCorrect answer [%d][%d]\n", indexes[0], indexes[1], nums[indexes[0]], nums[indexes[1]],  nums[indexes[0]] + nums[indexes[1]], target, answer[0], answer[1]);
        if (indexes[0] == answer[0] && indexes[1] == answer[1]) {
            System.out.printf("OK\n");
        } else {
            System.out.printf("Failed\n");
        }
        return;
    }
}