/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Valid Palindrome
 * @id             125
 * @Difficulty     Easy
 * @Tags           strings
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/883/

Valid Palindrome

    A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

    Given a string s, return true if it is a palindrome, or false otherwise.


Example 1:

    Input: s = "A man, a plan, a canal: Panama"
    Output: true
    Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:

    Input: s = "race a car"
    Output: false
    Explanation: "raceacar" is not a palindrome.

Example 3:

    Input: s = " "
    Output: true
    Explanation: s is an empty string "" after removing non-alphanumeric characters.
    Since an empty string reads the same forward and backward, it is a palindrome.

 

Constraints:

    1 <= s.length <= 2 * 10^5
    s consists only of printable ASCII characters.

*/

#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

bool isPalindrome(char * s) {
    char* b = s;
    char* e = &s[strlen(s)-1];

    while (1) {

        int bb = -1;
        int ee = -1;
        char c;
        while (*b) {
            c = *b++;
            if (isalnum(c)) {
                // printf("> pick \'%c\'\n", c);
                bb = tolower(c);
                break;
            } else {
                // printf("> skip \'%c\'\n", c);
            }
        }
        while (e >= s) { /* use '>=' not '>' to catch meet at a 1-letter string */
            c = *e--;
            if (isalnum(c)) {
                // printf("  pick \'%c\' <\n", c);
                ee = tolower(c);
                break;
            } else {
                // printf("  skip \'%c\' <\n", c);
            }
        }

        if (bb != ee) {
            // printf("%c != %c\n", bb, ee);
            return false;
        }

        if (b > e)
            break;
    }
    return true;
}

int main(int argc, char** argv) {

    // char* s = "A man, a plan, a canal: Panama"; int is = 1;
    // char* s = "race a car"; int is = 0;
    // char* s = " "; int is = 1;
    char* s = "ab"; int is = 0;
    // char* s = "a"; int is = 1;

    printf("%s \"%s\" %s\n", is == isPalindrome(s) ? "Correct." : "Wrong!  ", s, is ? "is a palindrome" : "is NOT a palindrome");
    return 0;
}
