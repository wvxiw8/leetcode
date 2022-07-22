/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Merge Sorted Array
 * @id             88
 * @Difficulty     Easy
 * @Tags           array, two pointers, sorting
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/featured/card/top-interview-questions-easy/96/sorting-and-searching/587/

Merge Sorted Array

You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, 
and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. 
To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that 
should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

 

Example 1:

    Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    Output: [1,2,2,3,5,6]
    Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
    The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
                                - -   -

Example 2:

    Input: nums1 = [1], m = 1, nums2 = [], n = 0
    Output: [1]
    Explanation: The arrays we are merging are [1] and [].
    The result of the merge is [1].

Example 3:

    Input: nums1 = [0], m = 0, nums2 = [1], n = 1
    Output: [1]
    Explanation: The arrays we are merging are [] and [1].
    The result of the merge is [1].
    Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.

 

Constraints:

    nums1.length == m + n
    nums2.length == n
    0 <= m, n <= 200
    1 <= m + n <= 200
    -10^9 <= nums1[i], nums2[j] <= 10^9

*/

import java.util.Arrays;

class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m-1;
        int i2 = n-1;
        int o = m+n-1;

        while (i1 != -1 || i2 != -1) {
            int v1 = i1 == -1 ? Integer.MIN_VALUE : nums1[i1];
            int v2 = i2 == -1 ? Integer.MIN_VALUE : nums2[i2];
            // System.out.printf("v1[%d]=%d v2[%d]=%d -> [%d]\n", i1, v1, i2, v2, o);

            if (v1 > v2) {
                nums1[o--] = v1; 
                --i1;
            } else if (v1 < v2) {
                nums1[o--] = v2;
                --i2;
            } else {
                nums1[o--] = v2;
                --i2;
                nums1[o--] = v1;
                --i1;
            }
        }

    }
    public static void main(String[] args) {
        int a[][][] = {
            {{1,2,3,0,0,0}, {3}, {2,5,6}, {3}},
            {{1,6,9,10,0}, {4}, {2}, {1}},
            {{1}, {1}, {0}, {0}},
            {{0}, {0}, {1}, {1}},
            {{0,0,0}, {0}, {7,8,9}, {3}},
        };
        Solution solution = new Solution();
        for (int i = 0; i < a.length; ++i) {
            System.out.println("In:  " +Arrays.toString(a[i][0]) + " : " +a[i][1][0]+ ",     " + Arrays.toString(a[i][2]) + " : " +a[i][3][0]);
            solution.merge(a[i][0], a[i][1][0], a[i][2], a[i][3][0]);
            System.out.println("Out: " +Arrays.toString(a[i][0]) + "\n");
        }

    }
}
