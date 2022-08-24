/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Reverse Integer
 * @id             7
 * @Difficulty     Easy/Medium
 * @Tags           strings
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/880/

Reverse Integer

    Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

    Assume the environment does not allow you to store 64-bit integers (signed or unsigned).


Example 1:

    Input: x = 123
    Output: 321

Example 2:

    Input: x = -123
    Output: -321

Example 3:

    Input: x = 120
    Output: 21


Constraints:

    -2^31 <= x <= 2^31 - 1

*/

#include <stdio.h>
#include <math.h>
#include <limits.h>

#define MAX_DIGITS 10

int intPow10(int n) {
    if (n == 0)
        return 1;
    return (int) (10 * intPow10(n - 1));
}

#if 1
/* Note, that the task says I cannot use 64-bit integers */
int reverse(int x) {
    // printf("x=%d\n", x);
    if (x == INT_MIN || x == 0) 
        return 0;

    char c[MAX_DIGITS];
    int negative = x < 0;
    int beg = 0, end = 0;
    
    if (negative) 
        x *= -1;

    while(x) {
        c[end] = x % 10;
        x /= 10;
        // printf(" c[%d]=%d x=%d\n", end, c[end], x);
        ++end;  
    }
    --end;


    for (beg = 0; beg < MAX_DIGITS && c[beg] == 0 ; ++beg);

    int result = 0;
    int check = 0;

    if (end - beg == 9) {
        if (c[0] > 2) {
            // printf(" overflow would happen at %d*10^%d (%d max)\n", c[0], end - beg , INT_MAX);
            return 0; /* Overflow for sure. Will meet already in pow10() */
        }
        check = 1; /* Might meet already later at summing */
    }

    while(beg <= end) {
        int power = end - beg;
        int val = c[beg];
        int addend = val * intPow10(power);
        // printf(" beg=%d end=%d pow=%d c[%d]=%d addend=%d\n", beg, end, power, beg, c[beg], addend);

        if (check) {
            int fit = INT_MAX - addend;
            if (fit < result) {
                // printf(" overflow at %d+%d=%ld (%d max)\n", result, addend, (long long int)result+addend, INT_MAX);
                return 0;
            }
        }
        result += addend;
        ++beg;
    }

    if (negative) 
        result *= -1;

    return result;
}

#else
/* Somebody's elegant solution I liked. But they are using 64-bit integer */
int reverse(int x) {
    int i;
    long long rev = 0;
    while(x) {
        rev = rev*10 + x%10;
        x = x/10;
    }
    return ((rev > INT_MAX || rev < INT_MIN) ? 0 : rev);
}
#endif

int main() {

    int data[][2] = {
        {123, 321},
        {-123, -321},
        {120, 21},
        {5, 5},
        {50, 5},
        {0, -0},
        {1534236469, 0},
        {1000000022, 0},
        {-2147483648, 0}, /* INT_MIN */
        {1563847412, 0},
        {-2147483412, -2143847412}
    };

    
    for (int i = 0; i < sizeof(data)/sizeof(data[0]); ++i) {
        int input = data[i][0];
        int reversed = reverse(input);
        int expected = data[i][1];
        if (reversed == expected) {
            printf("Correct. %d -> %d\n\n", input, expected);
        } else {
            printf("Wrong!   %d -> %d, expected %d\n\n", input, reversed, expected);
        }
    }
}
