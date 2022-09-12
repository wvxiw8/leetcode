/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Is Subsequence
 * @id             392
 * @Difficulty     Easy
 * @Tags           two pointers, string, dynamic programming
 * @Link           https://leetcode.com/problems/is-subsequence/

Is Subsequence

    Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

    A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).


Example 1:

    Input: s = "abc", t = "ahbgdc"
    Output: true

Example 2:

    Input: s = "axc", t = "ahbgdc"
    Output: false


Constraints:

    0 <= s.length <= 100
    0 <= t.length <= 10^4
    s and t consist only of lowercase English letters.


Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 10^9, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code? */

#include <stdio.h>
#include <stdbool.h>

bool isSubsequence(char * s, char * t) {
    while (*s && *t) {
    // printf("s=%c t=%c\n", *s, *t);
        if (*s == *t)
            ++s;
        ++t;
    }
    return !*s;
}

int main() {
    char* s = "abc"; char* t = "ahbgdc"; int exp = 1;
    // char* s = "axc"; char* t = "ahbgdc"; int exp = 0;
    // char* s = ""; char* t = "dfadf"; int exp = 1;
    // char* s = "a"; char* t = ""; int exp = 0;

    int ret = isSubsequence(s, t);
    if (exp == ret) 
        printf("OK\n");
    else 
        printf("Failed. s=%s t=%s ret=%d exp=%d\n", s, t, ret, exp);

    return 0;
}
