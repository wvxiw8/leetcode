/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Move Zeroes
 * @id             283
 * @Difficulty     Easy
 * @Tags           array
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/567/

Move Zeroes

    Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

    Note that you must do this in-place without making a copy of the array.


Example 1:

    Input: nums = [0,1,0,3,12]
    Output: [1,3,12,0,0]

Example 2:

    Input: nums = [0]
    Output: [0]


Constraints:

    1 <= nums.length <= 104
    -231 <= nums[i] <= 231 - 1

    Follow up: Could you minimize the total number of operations done?

*/


#include <stdio.h>
#include <string.h>

void moveZeroes(int* nums, int numsSize) {
    int zeros = 0;
    int* p = nums;
    int* end = nums + numsSize - 1;
    while (nums <= end) {
        if (*nums == 0) 
            ++zeros;
        else
            *p++ = *nums;

        ++nums;
    }

    for (int i = 0; i < zeros; ++i) {
        *end-- = 0;
    }
}

int main(int argc, char** argv) {
    // int a[] = {0,1,0,3,12}; int exp[] = {1,3,12,0,0};
    // int a[] = {0,2,1,3,4}; int exp[] = {2,1,3,4,0};
    // int a[] = {5,0,6,7,8}; int exp[] = {5,6,7,8,0};
    // int a[] = {2,1}; int exp[] = {2,1};
    // int a[] = {0}; int exp[] = {0};
    // int a[] = {1}; int exp[] = {1};

    moveZeroes(a, sizeof(a)/sizeof(a[0]));
    // printf("%d %d %d %d %d\n", a[0], a[1], a[2], a[3], a[4]);
    if (!memcmp(a, exp, sizeof(a)))
        printf("OK\n");
    else
        printf("Fail\n");

    return 0;
}
