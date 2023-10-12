/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Ugly Number
 * @id             263
 * @Difficulty     easy
 * @Tags           math
 * @Link           https://leetcode.com/problems/ugly-number/

Ugly Number
    An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

    Given an integer n, return true if n is an ugly number.


Example 1:

    Input: n = 6
    Output: true
    Explanation: 6 = 2 × 3


Example 2:

    Input: n = 1
    Output: true
    Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.


Example 3:

    Input: n = 14
    Output: false
    Explanation: 14 is not ugly since it includes the prime factor 7.

Constraints:

    -2^31 <= n <= 2^31 - 1
*/

#include <stdio.h>
#include <stdbool.h>


bool isUgly(int n) {
    if (n <= 0)
        return false;

    // ugly = 2ᵃ × 3ᵇ × 5ᶜ
    while (n != 1) {
        if ((n & 1) == 0) 
            n >>= 1;
        else if (n % 3 == 0) 
            n /= 3;
        else if (n % 5 == 0)
            n /= 5;
        else 
            return false;
    }

    return true;
}

int main() {
    /* Input, Output */
    int data[][2] = {
        {6, 1},
        {1, 1},
        {14, 0}
        };

    int n = sizeof(data)/sizeof(data[0]);
    const char* not = "not";
    for (int i = 0; i < n; ++i) {
        int number = data[i][0];
        int exp = data[i][1];
        bool ret = isUgly(number);
        if (ret == exp) {
             printf("OK   %d is %sugly \n", number, data[i][1] == 1 ? "" : "not ") ;
        } else {
             printf("FAIL %d is %sugly, but %d=isUgly()\n", number, data[i][1] == 1 ? "" : "not", ret) ;
        }
    } 
    return 0;
}
