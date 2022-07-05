/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Sqrt(x)
 * @id             69
 * @Difficulty     Easy
 * @Tags           binary search
 * @Featured       
 * @Link           https://leetcode.com/problems/sqrtx/

Sqrt(x)
    Given a non-negative integer x, compute and return the square root of x.
    Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
    Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.

Example 1:

    Input: x = 4
    Output: 2

Example 2:

    Input: x = 8
    Output: 2
    Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.


Constraints:

    0 <= x <= 2^31 - 1

 */

#include <stdio.h>

#if 0
/* Not the exact binary search algorithm. My thoughts */
int mySqrt(int x) {
    if (x <= 0) 
        return 0;

    long long i = 1;
    do {
        i <<= 1;
    } while ((i*i) < ((long long) x));
    i >>= 1;

    long long m = 0;
    while (m < 0x7FFFFFFF) {
        m = i * i;
        if (m == ((long long) x))
            return i;
        else if (m > ((long long) x))
            return --i;
        ++i;
    }
    return i;
}

#else
/* Binary search algorithm implemented */
int mySqrt(int x) {
    int left = 0;
    int right = x;
    long mid;
    
    do {
        mid = (left + right) >> 1;
        long long t = mid * mid;
        if (t < x)
            left = mid + 1;
        else if (t > x)
            right = mid - 1;
        else
            return mid;

    } while (left <= right);
    
    return (left + right) >> 1;
}
#endif

int main(int agrc, char** argv) {
    int x;
    x = 4; printf("%d = sqrt(%d)\n",  mySqrt(x), x);
    x = 8; printf("%d = sqrt(%d)\n",  mySqrt(x), x);
    x = 46341; printf("%d = sqrt(%d)\n",  mySqrt(x), x);
    x = 2147483647; printf("%d = sqrt(%d)\n",  mySqrt(x), x);

    return 0;
}