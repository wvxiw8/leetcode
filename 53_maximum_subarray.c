/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Maximum Subarray
 * @id             53
 * @Difficulty     Medium
 * @Tags           array, divide and conquer, dynamic programming
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/566/

Maximum Subarray

    Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

    A subarray is a contiguous part of an array.


Example 1:

    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    Output: 6
    Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:

    Input: nums = [1]
    Output: 1

Example 3:

    Input: nums = [5,4,-1,7,8]
    Output: 23


Constraints:

    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4


Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle. */

#include <stdio.h>
#include <limits.h>

int maxSubArray(int* nums, int numsSize) {
    int max = INT_MIN;
    int curr = 0;

    while (numsSize) {
        // printf("max=%d curr=%d num=%d\n", max, curr, *nums);
        curr = (curr + *nums) < *nums ? *nums : (curr + *nums);
        max = curr > max ? curr : max;
        ++nums;
        --numsSize;
        // printf("max=%d curr=%d\n", max, curr);
    }
    return max;
}


int main() {
    int a[] = {-2,1,-3,4,-1,2,1,-5,4};  int sum = 6;
    // int a[] = {1};  int sum = 1;
    // int a[] = {5,4,-1,7,8};  int sum = 23;
    
    for (int i = 0; i <  sizeof(a)/sizeof(a[0]); i++) printf("%d ", a[i]); printf("\n");

    int ret = maxSubArray(a, sizeof(a)/sizeof(a[0]));
    if (sum == ret) 
        printf("OK\n");
    else 
        printf("Failed. ret=%d exp=%d\n", ret, sum);

    return 0;
}
