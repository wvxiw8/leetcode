import java.util.HashMap;

/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Single Number
 * @id             136
 * @Difficulty     Easy
 * @Tags           array
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/549

Single Number
    Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
    You must implement a solution with a linear runtime complexity and use only constant extra space.

Constraints:
    1 <= nums.length <= 3 * 10^4
    -3 * 10^4 <= nums[i] <= 3 * 10^4
    Each element in the array appears twice except for one element which appears only once. */



class Solution {
    static public int singleNumberMy(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i : nums) {
            if (null != hashMap.put(i, i)) {
                hashMap.remove(i);
            }
        }
        for (int i : nums) {
            if (hashMap.get(i) != null) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }

    static public int singleNumberFast(int[] nums) {
        int singleNum = 0;

        for (int num : nums) {
            singleNum ^= num;
        }

        return singleNum;
    }

    public static void main(String[] args) {
        int[] array = {0,1,0,-1,3,2,7,1,4,4,6,-1,2,5,7,6,3}; // 5
        int answer = 5;

        for (int i : array) {
            System.out.print(i + " ");
        }

        int single = singleNumberFast(array);
        if (single == answer ) {
            System.out.print("\nSingle number is " + single + " \n");
            return;
        }
        
    }
}