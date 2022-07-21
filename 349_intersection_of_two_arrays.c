/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Intersection of Two Arrays
 * @id             349
 * @Difficulty     Easy
 * @Tags           array, binary search
 * @Link           https://leetcode.com/problems/intersection-of-two-arrays/

Intersection of Two Arrays
    Given two integer arrays nums1 and nums2, return an array of their intersection. 
    Each element in the result must be unique and you may return the result in any order.


Example 1:

    Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2]

Example 2:

    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    Output: [9,4]
    Explanation: [4,9] is also accepted.


Constraints:

    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 1000

 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


#if 0
int cmpfunc(const void * a, const void * b) {
    return *(int*)a - *(int*)b;
}

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* intersection(int* nums1, int nums1Size, int* nums2, int nums2Size, int* returnSize) {

    int* out = malloc((nums1Size > nums2Size ? nums1Size : nums2Size) * sizeof(int));
    int* begin = out;
 
    qsort(nums1, nums1Size, sizeof(int), cmpfunc);
    qsort(nums2, nums2Size, sizeof(int), cmpfunc);
    int *m1 = nums1 + nums1Size;
    int *m2 = nums2 + nums2Size;

    int prev = -1;
    while (nums1 < m1 && nums2 < m2) {
        int r = *nums1 - *nums2;
        if (r == 0) {
            if (prev != *nums1) {
                *out++ = *nums1;
                prev = *nums1;
            }
            ++nums1;
            ++nums2;
        } else if (r > 0) {
            ++nums2;
        } else {
            ++nums1;
        }
    }
    *returnSize = out - begin;
    return begin;
}
#else
/* A faster way. Resembles a task with detecting duplicates by using hash/sets */
#define MAX_VALUE 1000
#define FLAG 1
int* intersection(int* nums1, int nums1Size, int* nums2, int nums2Size, int* returnSize) {
    int a[MAX_VALUE]; /* Use an extra array to mark what values present in nums1 */
    memset(a, 0, sizeof(a));

    for (int i = 0; i < nums1Size; ++i) {
        a[nums1[i]] = FLAG;
    }

    int* out = malloc((nums1Size > nums2Size ? nums1Size : nums2Size) * sizeof(int));
    int* begin = out;
    for (int i = 0; i < nums2Size; ++i) {
        if (a[nums2[i]] == FLAG) {
            *out++ = nums2[i];
            a[nums2[i]] = 0;
        }
    }
    *returnSize = out - begin;
    return begin;
}
#endif

int main() {
    int nums1[] = {4,9,5}; int nums2[] = {9,4,9,8,4}; // Output: [9,4] or [4,9]
    // int nums1[] = {1,2,2,1}; int nums2[] = {2,2}; // Output: [2]
    int i;
    int n1 = sizeof(nums1)/sizeof(nums1[0]);
    int n2 = sizeof(nums2)/sizeof(nums2[0]);
    int s;
    int *res;
    for (i = 0; i < n1; ++i) printf("%d ", nums1[i]); printf("\n");
    for (i = 0; i < n2; ++i) printf("%d ", nums2[i]); printf("\n");
    res = intersection(nums1, n1, nums2, n2, &s);
    printf("out[%d]: ", s); 
    for (i = 0; i < s; ++i) printf("%d ", res[i]); printf("\n");
    free(res);
}
