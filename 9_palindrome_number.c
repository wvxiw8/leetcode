/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Palindrome Number
 * @id             9
 * @Difficulty     Easy
 * @Tags           other
 * @Link           https://leetcode.com/problems/palindrome-number/

Palindrome Number

    Given an integer x, return true if x is palindrome integer.

    An integer is a palindrome when it reads the same backward as forward.

    For example, 121 is a palindrome while 123 is not.


Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.

Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:

    Input: x = 10
    Output: false
    Explanation: Reads 01 from right to left. Therefore it is not a palindrome.


Constraints:

    -2^31 <= x <= 2^31 - 1


Follow up: Could you solve it without converting the integer to a string? */

#include <stdio.h>
#include <stdbool.h>

bool isPalindrome(int x) {
    char s[12];
    int n = 0;
    int i = 0;

    if (x < 0)
        return false;
    
    while (x) {
        s[n++] = x % 10;
        x /= 10;
    }

    while (i < n / 2) {
        if (s[i] != s[n-1-i])
            return false;
        ++i; 
    }
    return true;
}

int main(int argc, char** argv) {
    printf("%d\n", isPalindrome(-121));
    return 0;
}
