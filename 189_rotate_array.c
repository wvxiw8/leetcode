/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Rotate Array
 * @id             189
 * @Difficulty     Easy/Medium
 * @Tags           array, math
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/646/
 
Rotate Array
    Given an array, rotate the array to the right by k steps, where k is non-negative.

Constraints:
    1 <= nums.length <= 105
    -231 <= nums[i] <= 231 - 1
    0 <= k <= 105

Follow up:

    Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
    Could you do it in-place with O(1) extra space?
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void rotate(int* nums, int numsSize, int k) {
    if (k > numsSize) {
        k %= numsSize;
    }
    if (k) { 
        int a[k];
        memcpy(a, &nums[numsSize-k], k * sizeof(int));
        memmove(&nums[k], nums, (numsSize - k) * sizeof(int));
        memcpy(nums, a, k * sizeof(int));
    } 
}


int main() {
    int nums[] = {0,1,2,3,4,5,6,7};
    int i;
    int n = sizeof(nums)/sizeof(nums[0]);
    for (i = 0; i < n; ++i) printf("%d ", nums[i]); printf("\n");
    rotate(nums, n, 3);
    for (i = 0; i < n; ++i) printf("%d ", nums[i]); printf("\n");
}