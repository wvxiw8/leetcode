/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Search Insert Position
 * @id             35
 * @Difficulty     Easy
 * @Tags           binary search
 * @Link           https://leetcode.com/problems/search-insert-position/

Search Insert Position

    Given a sorted array of distinct integers and a target value, return the index if 
    the target is found. If not, return the index where it would be if it were inserted in order.
    You must write an algorithm with O(log n) runtime complexity.


Example 1:

    Input: nums = [1,3,5,6], target = 5
    Output: 2

Example 2:

    Input: nums = [1,3,5,6], target = 2
    Output: 1

Example 3:

    Input: nums = [1,3,5,6], target = 7
    Output: 4


Constraints:

    1 <= nums.length <= 10^4
    -10^4 <= nums[i] <= 10^4
    nums contains distinct values sorted in ascending order.
    -10^4 <= target <= 10^4

*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Input {
    int[] nums; 
    int target; 
    int exp;
    Input (int[] nums, int target, int exp) {
        this.nums = nums;
        this.target = target;
        this.exp = exp;
    }
}
class Solution {

    public int searchInsert(int[] nums, int target) {
        // return searchInsertIncusiveUpperBound(nums, target);
        return searchInsertExclusiveUpperBound(nums, target);
    }


    /* This was my first write, I didn't know that it's correct to right bseach with EXCLUSIVE upper bound.
    esspecially for insert. That was tought to catch all bugs (see notes in comments) */
    private int searchInsertIncusiveUpperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;

        if (target <= nums[left])
            return 0;

        if (target > nums[right])
            return right+1;

        /* 
            - Implement binary search
            - Check the L and R right off when assiginig them, because we may miss the value or bounds may collapse (mid would be out of the current range)
            - Using bounds exclusively alows not to stuck in infinite loop without extra checking bounds
        */ 

        do {
            mid = left + ((right - left) >> 1);
            // System.out.printf("l=[%d]=%d m=[%d]=%d r=[%d]=%d\n", left, nums[left], mid, nums[mid], right, nums[right]);
           
            if (target < nums[mid]) {
                right = mid - 1; 
                if (target == nums[right]) {
                    return right;
                } else if (target > nums[right]) {
                    return right+1;
                }
            } else if (target > nums[mid]) {
                left = mid + 1;
                if (target <= nums[left]) {
                    return left;
                } else if (target < nums[left]) {
                    return left;
                }
            } else /* target == nums[mid] */ {
                return mid; 
            }
         
        }   while (left < right);

        return -1; // Won't be reached
    }


    private int searchInsertExclusiveUpperBound(int[] nums, int target) {
        int l = 0;
        int u = nums.length;
        int m = l;

        while (l < u) {
            m = l + ((u - l) >> 2);

            // if (u == nums.length) System.out.printf("l=[%d]=%d m=[%d]=%d u=[%d]=X\n", l, nums[l], m, nums[m], u);  else  System.out.printf("l=[%d]=%d m=[%d]=%d u=[%d]=%d\n", l, nums[l], m, nums[m], u, nums[u]);

            if (target == nums[m]) {
                return m;
            } else if (target < nums[m]) {
                u = m;
            } else {
                l = m + 1;
            }
        }
        return u;
    }

    public static void main(String[] args) {
        List <Input> inputs = new ArrayList<>();
                                    /* nums, target, expected */
        inputs.add(new Input(new int[]{1,3,5,6}, 5, 2));
        inputs.add(new Input(new int[]{1,3,5,6}, 2, 1));
        inputs.add(new Input(new int[]{1,3,5,6}, 7, 4));
        inputs.add(new Input(new int[]{1,3,5,6}, 0, 0));
        inputs.add(new Input(new int[]{1,3}, 2, 1));
        inputs.add(new Input(new int[]{1,3}, 3, 1));
        inputs.add(new Input(new int[]{1,2,4,6,7}, 3, 2));
        inputs.add(new Input(new int[]{3,4,5,6,7,8}, 6, 3));


        
        Solution solution = new Solution();
        for (Input input : inputs) {
            System.out.println("--");
            System.out.println("nums=" +Arrays.toString(input.nums)+ " target=" +input.target);

            int ret = solution.searchInsert(input.nums, input.target);
            System.out.println("ret=" +ret+ " exp=" +input.exp);

            if (ret == input.exp) 
                System.out.println("OK");
            else
                System.out.println("~~~ Fail ~~~");
        }
    }
}
