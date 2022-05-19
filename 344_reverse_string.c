/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Reverse String
 * @id             344
 * @Difficulty     Easy
 * @Tags           string
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/879/

Reverse String

    Write a function that reverses a string. The input string is given as an array of characters s.
    You must do this by modifying the input array in-place with O(1) extra memory.

 
Example 1:

    Input: s = ["h","e","l","l","o"]
    Output: ["o","l","l","e","h"]

Example 2:

    Input: s = ["H","a","n","n","a","h"]
    Output: ["h","a","n","n","a","H"]

 

Constraints:

    1 <= s.length <= 105
    s[i] is a printable ascii character.

 */

#include <stdio.h>

void reverseString(char* s, int sSize) {
    char* b = s;
    char* e = s + sSize - 1;
    sSize >>= 1;
    while(sSize--) {
        *b ^= *e;
        *e ^= *b;
        *b++ ^= *e--;
    }
}

void reverseString2(char* s, int sSize) {
    char* b = s;
    char* e = s + sSize - 1;
    sSize >>= 1;
    char t;
    while(sSize--) {
        t = *b;
        *b++ = *e;
        *e-- = t;
    }
}

void reverseString3(char* s, int sSize) {
    char t;
    int j;
    int limit = sSize >> 1;
    for (int i = 0; i < limit; ++i) {
        t = s[i];
        j = sSize - i - 1;
        s[i] = s[j];
        s[j] = t;
    }
}

int main() {
    char in[] = "Hello"; char* exp = "olleH";
    // char in[] = "Good"; char* exp = "dooG";
    int i;
    int n = strlen(in);
    printf("%s\n", in); 
    reverseString(in, n);
    printf("%s\n", in); 
}
