/* 

https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/884/

String to Integer (atoi)

    Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).

    The algorithm for myAtoi(string s) is as follows:

        1. Read in and ignore any leading whitespace.
        2. Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
        3. Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
        4. Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
        5. If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
        6. Return the integer as the final result.

    Note:

        Only the space character ' ' is considered a whitespace character.
        Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.

 

Example 1:

    Input: s = "42"
    Output: 42
    Explanation: The underlined characters are what is read in, the caret is the current reader position.
    Step 1: "42" (no characters read because there is no leading whitespace)
            ^
    Step 2: "42" (no characters read because there is neither a '-' nor '+')
            ^
    Step 3: "42" ("42" is read in)
            ^
    The parsed integer is 42.
    Since 42 is in the range [-231, 231 - 1], the final result is 42.

Example 2:

    Input: s = "   -42"
    Output: -42
    Explanation:
    Step 1: "   -42" (leading whitespace is read and ignored)
                ^
    Step 2: "   -42" ('-' is read, so the result should be negative)
                ^
    Step 3: "   -42" ("42" is read in)
                ^
    The parsed integer is -42.
    Since -42 is in the range [-231, 231 - 1], the final result is -42.

Example 3:

    Input: s = "4193 with words"
    Output: 4193
    Explanation:
    Step 1: "4193 with words" (no characters read because there is no leading whitespace)
            ^
    Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
            ^
    Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
                ^
    The parsed integer is 4193.
    Since 4193 is in the range [-231, 231 - 1], the final result is 4193.

 

Constraints:

    0 <= s.length <= 200
    s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.



 */

#include <stdio.h>
#include <limits.h>

int myAtoi(char* s) {
    long int sign = 1; 
    
    while (s && *s == ' ') {
        ++s;
    }
    
    if (!s) {
        return 0;
    }
    
    if (*s == '-') {
        sign = -1;
        ++s;
    } else if (*s == '+') {
        ++s;
    }
    
    while (s && *s == '0') { 
        ++s;
    }
    
    if (*s > '9' || *s < '0' ) {
        return 0;
    }


    int i = 0; 
    long int result = 0;
    while (s && (*s <= '9' && *s >= '0')) {
        result = result * 10 + (*s - '0');
        if (i++ > 10) {
            break;
        }
        ++s;
    }

    if (sign == 1 && result > (long)INT_MAX) {
        return INT_MAX;
    }
    if (sign == -1 && (-result < (long)INT_MIN)) {
        return INT_MIN;
    }
    return (sign == 1 ? result : result * sign);
}

int main() {
    // char s[] = "42"; int exp = 42;
    // char s[] = "   -42"; int exp = -42;
    // char s[] = "4193 with words"; int exp = 4193;
    // char s[] = "not-a-digit"; int exp = 0;
    // char s[] = "   "; int exp = 0;
    // char s[] = "000"; int exp = 0;
    // char s[] = "-91283472332"; int exp = -2147483648;
    // char s[] = "  0000000000012345678"; int exp = 12345678;
    // char s[] = "2147483648"; int exp = 2147483647;
    // char s[] = "-000001"; int exp = -1;
    char s[] = "-91283472332"; int exp = -2147483648;
    
    int r = myAtoi(s);
    printf("%d=myAtoi(\"%s\")\n", r, s);
    if (r == exp) {
        printf("OK\n");
    } else {
        printf("Fail. Expected %d\n", exp);
    }
    return 0;
}
