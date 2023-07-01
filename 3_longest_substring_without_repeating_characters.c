/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Longest Substring Without Repeating Characters
 * @id             3
 * @Difficulty     Medium
 * @Tags           hash table, string, sliding window
 * @Link           https://leetcode.com/problems/longest-substring-without-repeating-characters/

Longest Substring Without Repeating Characters

    Given a string s, find the length of the longest substring without repeating characters.


Example 1:

    Input: s = "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.

Example 2:

    Input: s = "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.

Example 3:

    Input: s = "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

Constraints:

0 <= s.length <= 5 * 10^4
s consists of English letters, digits, symbols and spaces.

*/

#include <stdio.h>

int lengthOfLongestSubstring(char * s) { 
    char table[128] = {0};
    char* p = s;
    int max = 0;
    // char* base = s; int i = 0;  printf("\n %s\n", s ? s : ""); 
    while (*s) {
        // printf(" %d p[%ld]=%c  s=[%ld]=%c\n", i++, p-base, *p, s-base, *s);
        if (table[*s] == 0) {
            table[*s++] = 1;
        } else {
            int curr = s - p;
            if (curr > max) {
                // printf(" max=%d curr=%d \n", max, curr);
                max = curr;
            }
            table[*p++] = 0;
        }
    }

    return max > s - p ? max : s - p;
}

int main(int argc, char** argv) {
    char* input[]  = {"abcabcbb", "bbbbb", "bbb", "pwwkew", "aab", "dvdf", "abbb", "abcdefg"};
    int exp[]      = {         3,       1,     1,        3,     2,      3,      2,         7};

    for(int i = 0; i < sizeof(input)/ sizeof(input[0]); i++) {
        int res = lengthOfLongestSubstring(input[i]);
        int isSuccess = res == exp[i];
        const char* msg =  isSuccess ? "OK" : "Failed";
        printf("%s  %s", input[i], msg);
        if (isSuccess)
             printf("\n");
        else
             printf(" res=%d exp=%d\n", res, exp[i]);        
    }
}