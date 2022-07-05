/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Intersection of Two Arrays II
 * @id             350
 * @Difficulty     Easy
 * @Tags           array, binary search
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/674/

Intersection of Two Arrays II
    Given two integer arrays nums1 and nums2, return an array of their intersection. 

    Each element in the result must appear as many times as it shows in both arrays 
    and you may return the result in any order.


Example 1:

    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2,2]

Example 2:

    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [4,9]
    Explanation: [9,4] is also accepted.


Constraints:

    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 1000


Follow up:

    What if the given array is already sorted? How would you optimize your algorithm?
    What if nums1's size is small compared to nums2's size? Which algorithm is better?
    What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int cmpfunc(const void * a, const void * b) {
    return *(int*)a - *(int*)b;
}

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* intersect(int* nums1, int nums1Size, int* nums2, int nums2Size, int* returnSize) {

    int* a = malloc((nums1Size > nums2Size ? nums1Size : nums2Size) * sizeof(int));
    int* b = a;
 
    qsort(nums1, nums1Size, sizeof(int), cmpfunc);
    qsort(nums2, nums2Size, sizeof(int), cmpfunc);
    int *m1 = nums1 + nums1Size;
    int *m2 = nums2 + nums2Size;

    while (nums1 < m1  && nums2 < m2) {
        int r = *nums1 - *nums2;
        if (r == 0) {
            *a++ = *nums1++;
            ++nums2;
        } else if (r > 0) {
            ++nums2;
        } else {
            ++nums1;
        }
    }
    *returnSize = a - b;
    return b;
}

int main() {
    int nums1[] = {4,9,5}; int nums2[] = {9,4,9,8,4};
    // int nums1[] = {1,2,2,1}; int nums2[] = {2,2};
    int i;
    int n1 = sizeof(nums1)/sizeof(nums1[0]);
    int n2 = sizeof(nums2)/sizeof(nums2[0]);
    int s;
    int *res;
    for (i = 0; i < n1; ++i) printf("%d ", nums1[i]); printf("\n");
    for (i = 0; i < n2; ++i) printf("%d ", nums2[i]); printf("\n");
    res = intersect(nums1, n1, nums2, n2, &s);
    for (i = 0; i < s; ++i) printf("%d ", res[i]); printf("\n");
    free(res);
}