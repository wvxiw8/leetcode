/* 
https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/721/

Valid Parentheses

    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:

        Open brackets must be closed by the same type of brackets.
        Open brackets must be closed in the correct order.


Example 1:

    Input: s = "()"
    Output: true

Example 2:

    Input: s = "()[]{}"
    Output: true

Example 3:

    Input: s = "(]"
    Output: false

 

Constraints:

    1 <= s.length <= 10^4
    s consists of parentheses only '()[]{}'.   */

#include <stdio.h>
#include <stdbool.h>


#define MAX_SIZE 100000

int isValid(char * s) {
    char buf [MAX_SIZE/2];
    char* p = buf;
    char a[3][2] = {{'(', ')'}, {'{', '}'}, {'[', ']'}};
    int goodchar;
    while(*s) {
        goodchar = 0;
        for (int i = 0; i < 3; ++i) {
            if (*s == a[i][0]) {
                if (*(s+1) == a[i][1]) {
                    s += 2;
                } else {
                    *p++ = *s++;
                }
                goodchar = 1;
                break;
            } else if (*s == a[i][1] && p > buf && *(p-1) == a[i][0]) {
                --p;
                ++s;
                goodchar = 1;
                break;
            }
        }
        if (!goodchar) {
            return 0;
        }
    }
    if (p != buf) {
        return 0;
    }
    return 1;
}

int main(int argc, char** argv) {
    // char s [] = "()" ; int expect = 1; 
    // char s [] = "{{}[][[[]]]}" ; int expect = 1; 
    char s [] = "()[]{}" ; int expect = 1; 
    // char s [] = "(]" ; int expect = 0; 
    // char s [] = "}})]" ; int expect = 0; 
    
    int valid = isValid(s);
    printf("%s %svalid\n", s, valid ? "" :  "NOT ");
    return !valid;
}