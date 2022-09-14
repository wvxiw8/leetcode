/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Reverse Vowels of a String
 * @id             345
 * @Difficulty     Easy
 * @Tags           two pointers, string
 * @Link           https://leetcode.com/problems/reverse-vowels-of-a-string/

Reverse Vowels of a String

    Given a string s, reverse only all the vowels in the string and return it.

    The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.


Example 1:

    Input: s = "hello"
    Output: "holle"

Example 2:

    Input: s = "leetcode"
    Output: "leotcede"


Constraints:

    1 <= s.length <= 3 * 10^5
    s consist of printable ASCII characters. */

#include <stdio.h>
#include <string.h>
#include <ctype.h>

char* reverseVowels(char* s) {
    char* b = s;
    char* e = &s[strlen(s)];
    int flagb = 0, flage = 0;

    while (e > b) {
        char cb = tolower(*b);
        char ce = tolower(*e);
        if (cb == 'a' || cb == 'e' || cb == 'i' || cb == 'o' || cb == 'u') 
            flagb = 1;
        else 
            ++b;

        if (ce == 'a' || ce == 'e' || ce == 'i' || ce == 'o' || ce == 'u')
            flage = 1;
        else 
            --e;
            
        if (flagb && flage) {
            char c = *e;
            *e-- = *b;
            *b++ = c;
            flagb = flage = 0;
        }
    }
    return s;
}

int main() {
    /* Note, that to be able to edit the string in-place the char[] is required, not the char* */
    char input[] = "hello"; char exp[] = "holle";
    // char input[] = "leetcode"; char exp[] = "leotcede";
    // char input[] = "aA"; char exp[] = "Aa";

    printf("%s: ", input);
    char* output = reverseVowels(input);
    if (!strcmp(output, exp))
        printf("OK\n");
    else 
        printf("Failed. out=%s exp=%s\n", output, exp);

    return 0;
}
