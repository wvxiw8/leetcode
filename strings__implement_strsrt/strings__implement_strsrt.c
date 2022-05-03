/* 

https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/885/

Implement strStr().

    Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Clarification:

    What should we return when needle is an empty string? This is a great question to ask during an interview.
    For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 

Example 1:

    Input: haystack = "hello", needle = "ll"
    Output: 2

Example 2:

    Input: haystack = "aaaaa", needle = "bba"
    Output: -1

Constraints:

    1 <= haystack.length, needle.length <= 104
    haystack and needle consist of only lowercase English characters.

 */

#include <stdio.h>
#include <limits.h>

int strStr(char* haystack, char* needle) {
    char *h, *n, *p = haystack;
    while (*haystack) {
        h = haystack;
        n = needle;
        while (*n) {
            if (*n++ != *h++) {
                break;
            }
        }
        if (!*n) {
            if (*(--n) == *(--h)) {
                return haystack - p;
            }
        }
        ++haystack;
    }
    return -1;
}

int main() {
    char haystack[] = "hello"; char needle[] = "ll"; int output = 2;
    // char haystack[] = "aaaaa"; char needle[] = "bba"; int output = -1;
    // char haystack[] = "abc"; char needle[] = "c"; int output = 2;
    
    int r = strStr(haystack, needle);
    printf("%d=strStr(\"%s\",\"%s\")\n", r, haystack, needle);
    if (r == output) {
        printf("OK\n");
    } else {
        printf("Fail. Expected %d\n", output);
    }
    return 0;
}
