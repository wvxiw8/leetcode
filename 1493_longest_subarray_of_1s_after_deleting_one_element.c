/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Longest Subarray of 1's After Deleting One Element
 * @id             1493
 * @Difficulty     Medium
 * @Tags           array, dynamic programming, sliding window
 * @Link           https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
 
Longest Subarray of 1's After Deleting One Element

    Given a binary array nums, you should delete one element from it.

    Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.


Example 1:

    Input: nums = [1,1,0,1]
    Output: 3
    Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.

Example 2:

    Input: nums = [0,1,1,1,0,1,1,0,1]
    Output: 5
    Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].

Example 3:

    Input: nums = [1,1,1]
    Output: 2
    Explanation: You must delete one element.
 

Constraints:

    1 <= nums.length <= 10^5
    nums[i] is either 0 or 1.
*/

#include <stdio.h>
#include <stddef.h>

int longestSubarray(int* nums, int numsSize) {
    int maxLen = 0;
    int curLen = 0;
    int* b = nums;
    int* e = nums;
    const int* stop = nums + numsSize;
    int* zero = NULL;

    // int i = 0;
    while (e < stop) {
        // printf("%d %d [%ld,%ld] %ld/%d\n", i++, *e, b-nums, e-nums, e-b, maxLen);
        if (*e == 0) {
            if (zero != NULL) {
                curLen = e - b;
                if (curLen > maxLen)
                    maxLen = curLen; 
                b = zero + 1;
            }
            zero = e;
        }
        e++;
    }
    curLen = e - b;
    if (curLen > maxLen)
        maxLen = curLen; 
    if (maxLen != 0) 
        maxLen--;

    return maxLen;
}


int main(int argc, char** argv) {
    int n0[] = {1,1,0,1}; int exp0 = 3;
    int n1[] = {0,1,1,1,0,1,1,0,1}; int exp1 = 5;
    int n2[] = {1,1,1}; int exp2 = 2;
    int n3[] = {1,0,1,1,1,1,0,1,1,1}; int exp3 = 7; 
    
    int* tests[][3] = {
        {n0, (int*) (sizeof(n0)/sizeof(n0[0])), &exp0},
        {n1, (int*) (sizeof(n1)/sizeof(n1[0])), &exp1},
        {n2, (int*) (sizeof(n2)/sizeof(n2[0])), &exp2},
        {n3, (int*) (sizeof(n3)/sizeof(n3[0])), &exp3},
    };

    int testsNum = sizeof(tests)/sizeof(tests[0]);
    for (size_t i = 0; i < testsNum; i++) {
        printf("--------------------\n");
        int* nums = tests[i][0];
        size_t size = (size_t) tests[i][1];
        int exp = *tests[i][2];
        printf("size=%zu exp=%d [ ", size, exp);
        for (size_t j = 0; j < size; j++) {
            printf("%d ", nums[j]);
        }
        printf("]\n");
    
        int output = longestSubarray(nums, (int) size);
        if (output == exp)
            printf("OK\n");
        else
            printf("Failed. output=%d\n", output);  
    }
}
