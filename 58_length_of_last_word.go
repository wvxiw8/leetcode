//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Length of Last Word
id             58
Difficulty     Easy
Tags           string
Featured       Top Interview 150
Link           https://leetcode.com/problems/length-of-last-word/
Link           https://leetcode.com/problems/length-of-last-word/description/?envType=study-plan-v2&envId=top-interview-150

Length of Last Word

    Given a string s consisting of words and spaces, return the length of the last word in the string.

    A word is a maximal substring (a substring is a contiguous non-empty sequence of characters within a string) consisting of non-space characters only.

Example 1:

    Input: s = "Hello World"
    Output: 5
    Explanation: The last word is "World" with length 5.

Example 2:

    Input: s = "   fly me   to   the moon  "
    Output: 4
    Explanation: The last word is "moon" with length 4.

Example 3:

    Input: s = "luffy is still joyboy"
    Output: 6
    Explanation: The last word is "joyboy" with length 6.

Constraints:

1 <= s.length <= 10^4
s consists of only English letters and spaces ' '.
There will be at least one word in s.
*/

package main

import (
	"fmt"
	"strings"
)

type testData struct {
	input  string
	output int
}

func main() {
	data := []testData{
		{"Hello World", 5},
		{"   fly me   to   the moon  ", 4},
		{"luffy is still joyboy", 6},
	}

	for _, v := range data {
		exp := v.output
		ret := lengthOfLastWord(v.input)
		if exp == ret {
			fmt.Printf("OK    %s\n", v.input)
		} else {
			fmt.Printf("ERROR exp=%d ret=%d %s\n", exp, ret, v.input)
		}
	}

}

func lengthOfLastWord(s string) int {
	fields := strings.Fields(s)
	i := len(fields) - 1
	return len(fields[i])
}

func lengthOfLastWord2(s string) int {
	b := []byte(s)
	var beg, end int = 0, 0 // Begin and end of the last word
	var prev byte = 0       // Previous byte
	for i := 0; i < len(b); i++ {
		if b[i] == ' ' {
			if prev != ' ' {
				end = i
			}
		} else {
			if prev == ' ' {
				beg = i
			}
		}
		prev = b[i]
	}
	if beg >= end {
		end = len(b)
	}
	//fmt.Printf("%d:%d %s\n", beg, end, s[beg:end])
	return end - beg
}
