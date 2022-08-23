/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Valid Anagram
 * @id             242
 * @Difficulty     Easy
 * @Tags           strings
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/882/

Valid Anagram

    Given two strings s and t, return true if t is an anagram of s, and false otherwise.

    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.


Example 1:

    Input: s = "anagram", t = "nagaram"
    Output: true

Example 2:

    Input: s = "rat", t = "car"
    Output: false


Constraints:

    1 <= s.length, t.length <= 5 * 10^4
    s and t consist of lowercase English letters.


Follow up: 
    What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */

#include <stdio.h>
#include <stdbool.h>

#define SIZE 26 /* Number of lower case Latin letters */

void processString(short* storage, char* s) {
    while(*s) {
        storage[*s - 'a'] += 1;
        ++s;
    }
}

bool isAnagram(char* s, char* t) {
    /* Lets count the number of letters in each string. 
    16-bit storage is enought to hold 50000 line of a single value letter */
    short ss[SIZE] = {0};  
    short tt[SIZE] = {0};

    processString((short*)ss, s);
    processString((short*)tt, t);

    for (int i = 0; i < SIZE; ++i) {
        if (ss[i] != tt[i])
            return false;
    }

    return true;
}


int main() {

    char* isTrue = "1";
    char* isFalse = "0";

    /* s, t, true/false */
    char* data[][3] = {
        {"anagram", "nagaram", isTrue },
        {"rat", "car", isFalse},
        {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaab", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", isFalse },
        {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaab", "aaaaaaaaaaaabaaaaaaaaaaaaaaaaa", isTrue },
    }; 

    
    for (int i = 0; i < sizeof(data)/sizeof(data[0]); ++i) {
        bool expected = (bool) (*data[i][2]-'0');
        if (isAnagram(data[i][0], data[i][1]) == expected) {
            printf("Correct. \"%s\" and \"%s\" %s\n", data[i][0], data[i][1], expected == false ? "are NOT anagramms" : "are anagramms");
        } else {
            printf("WRONG!   \"%s\" and \"%s\" %s\n", data[i][0], data[i][1], expected == false ? "considered anagramms, but they are NOT" :"considered NOT anagramms, but they are ARE");

        }
    }
}
