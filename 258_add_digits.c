/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Add Digits
 * @id             258
 * @Difficulty     Easy
 * @Tags           math
 * @Link           https://leetcode.com/problems/add-digits/

Add Digits

    Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.


Example 1:

    Input: num = 38
    Output: 2
    Explanation: The process is
    38 --> 3 + 8 --> 11
    11 --> 1 + 1 --> 2 
    Since 2 has only one digit, return it.

Example 2:

    Input: num = 0
    Output: 0


Constraints:

    0 <= num <= 2^31 - 1


Follow up: Could you do it without any loop/recursion in O(1) runtime?  */

#include <stdio.h>

int addDigits(int num) {
    while (num > 9)
        num = num / 10 + num % 10;
    return num;
}

int main(int argc, char** argv) {
    int num = 38; int output = 2;
    // int num = 0; int output = 0;

    int ret = addDigits(num);
    if (ret == output) {
        printf("OK\n");
    } else {
        printf("Fail\n");
        printf(" Returned: %d, expected: %d\n", ret, output); 
    }
    return 0;
}
