/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Plus One
 * @id             66
 * @Difficulty     Easy
 * @Tags           array
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/559/

Plus One

    You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

    Increment the large integer by one and return the resulting array of digits.


Example 1:

    Input: digits = [1,2,3]
    Output: [1,2,4]
    Explanation: The array represents the integer 123.
    Incrementing by one gives 123 + 1 = 124.
    Thus, the result should be [1,2,4].

Example 2:

    Input: digits = [4,3,2,1]
    Output: [4,3,2,2]
    Explanation: The array represents the integer 4321.
    Incrementing by one gives 4321 + 1 = 4322.
    Thus, the result should be [4,3,2,2].

Example 3:

    Input: digits = [9]
    Output: [1,0]
    Explanation: The array represents the integer 9.
    Incrementing by one gives 9 + 1 = 10.
    Thus, the result should be [1,0].


Constraints:

    1 <= digits.length <= 100
    0 <= digits[i] <= 9
    digits does not contain any leading 0's.

*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* plusOne(int* digits, int digitsSize, int* returnSize) {
    int* buf = malloc(sizeof(int) * (digitsSize + 1));
    int overflow = digits[digitsSize - 1] == 9 ? 1 : 0;

    digits[digitsSize - 1]++;
    for (int i = 0; i < digitsSize; ++i) {
        int j = digitsSize - 1 - i;
        if (digits[j] + overflow > 9) {
            buf[j] = 0;
            overflow = 1;
        } else {
            buf[j] = digits[j] + overflow;
            overflow = 0;
        }
    }
    *returnSize = digitsSize;

    if (overflow) {
        memmove(&buf[1], buf, sizeof(int) * digitsSize);
        buf[0] = 1;
        (*returnSize)++;
    }

    return buf;
}

void printArray(int* a, int len) {
    for (int i = 0; i < len; ++i)
        printf("%d ", a[i]); 
    printf("\n");
}

int main(int argc, char** argv) {
    int a[] = {1,2,3}; 
    // int a[] = {4,3,2,1};
    // int a[] = {9};
    // int a[] = {9,8,7,6,5,4,3,2,1,1};
    // int a[] = {7,1,4,9};
    // int a[] = {0};
    // int a[] = {3,9,9};
    // int a[] = {9,9,9};
    
    int len = sizeof(a)/sizeof(a[0]);
    int outlen;

    printArray(a, len);
    int* b = plusOne(a, len, &outlen);
    printArray(b, outlen);
    
    free(b);
    return 0;
}
