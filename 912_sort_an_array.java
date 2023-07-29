/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Sort an Array
 * @id             912
 * @Difficulty     Medium
 * @Tags           array, divide and conquer, sorting, heap (priority queue), merge sort, bucket sort, radix sort, counting sort
 * @Link           https://leetcode.com/problems/sort-an-array/

Sort an Array

    Given an array of integers nums, sort the array in ascending order and return it.
    You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.


Example 1:

    Input: nums = [5,2,3,1]
    Output: [1,2,3,5]
    Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).

Example 2:

    Input: nums = [5,1,1,2,0,0]
    Output: [0,0,1,1,2,5]
    Explanation: Note that the values of nums are not necessarily unique.


Constraints:

    1 <= nums.length <= 5 * 10^4
    -5 * 10^4 <= nums[i] <= 5 * 10^4
*/

import java.util.Arrays;
import com.wvxiw.leetcode.datastructures.Heap;

class Solution {

    public int[] sortArray(int[] nums) {
        Heap heap = new Heap(nums, true);
        int[] sorted = new int[nums.length];
        for (int i = 0; i < nums.length ; i++)
            sorted[i] = heap.extractTop();
        return sorted;
    }

    public static void main(String[] args) {
        /* {{nums}, {expected}} */
        int [][][] data = {
                {{5,2,3,1}, {1,2,3,5}},
                {{5,1,1,2,0,0}, {0,0,1,1,2,5}},
                {{-74,48,-20,2,10,-84,-5,-9,11,-24,-91,2,-71,64,63,80,28,-30,-58,-11,-44,-87,-22,54,-74,-10,-55,-28,-46,29,10,50,-72,34,26,25,8,51,13,30,35,-8,50,65,-6,16,-2,21,-78,35,-13,14,23,-3,26,-90,86,25,-56,91,-13,92,-25,37,57,-20,-69,98,95,45,47,29,86,-28,73,-44,-46,65,-84,-96,-24,-12,72,-68,93,57,92,52,-45,-2,85,-63,56,55,12,-85,77,-39}, {-96,-91,-90,-87,-85,-84,-84,-78,-74,-74,-72,-71,-69,-68,-63,-58,-56,-55,-46,-46,-45,-44,-44,-39,-30,-28,-28,-25,-24,-24,-22,-20,-20,-13,-13,-12,-11,-10,-9,-8,-6,-5,-3,-2,-2,2,2,8,10,10,11,12,13,14,16,21,23,25,25,26,26,28,29,29,30,34,35,35,37,45,47,48,50,50,51,52,54,55,56,57,57,63,64,65,65,72,73,77,80,85,86,86,91,92,92,93,95,98}},
        };

        Solution solution = new Solution();
        for (int[][] d : data) {
            int[] nums = d[0];
            int[] exp = d[1];
            System.out.print("nums=" + Arrays.toString(nums) + "\n");
            int[] res = solution.sortArray(nums);
            System.out.print(" res=" + Arrays.toString(res) + "\n");
            if (Arrays.equals(res, exp)) {
                System.out.println("   OK");
            } else {
                System.out.println("   Failed");
                System.out.println("     exp=" + Arrays.toString(exp));
                System.out.println("     res=" + Arrays.toString(res));
            }
            System.out.println();
        }
    }
}
