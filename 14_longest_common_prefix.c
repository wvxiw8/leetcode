/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Longest Common Prefix
 * @id             14
 * @Difficulty     Easy
 * @Tags           strings
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/887/

Longest Common Prefix

    Write a function to find the longest common prefix string amongst an array of strings.

    If there is no common prefix, return an empty string "".


Example 1:

    Input: strs = ["flower","flow","flight"]
    Output: "fl"

Example 2:

    Input: strs = ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.


Constraints:

    1 <= strs.length <= 200
    0 <= strs[i].length <= 200
    strs[i] consists of only lowercase English letters.

*/


#include <stdio.h>
#include <string.h>

char* longestCommonPrefix(char** strs, int strsSize) {
    static char buf[201];
    int max = strlen(*strs);
    int c;
    strcpy(buf, *strs++);
    // printf("%s:\n", buf);

    for (int i = 1; i < strsSize; i++) {
        char* b = buf;
        char* s = *strs++;
        // printf("%s:\n", s);
        c = 0;
        while (*b || *s) {
            // printf(" \'%c\' ", *s);
            if (*s++ != *b++) {
                // printf(" max=%d\n", c);
                break;
            }
            ++c;
            // printf(" c=%d\n", c);
        }
        max = c < max ? c : max;
    }
    buf[max] = '\0';
    return buf;
}

int main(int argc, char** argv) {
    char* a[] = {"flower","flow","flight"}; // "fl"
    // char* a[] = {"ab","a"}; // "a"
    // char* a[] = {"aaa","aa","aaa"}; // "aa"
    // char* a[] = {"dog","racecar","car"}; // ""

    printf("%s\n", longestCommonPrefix(a,sizeof(a)/sizeof(a[0])));
    return 0;
}
