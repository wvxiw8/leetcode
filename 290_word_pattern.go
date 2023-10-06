//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Word Pattern
id             290
Difficulty     Easy
Tags           hash table, string
Featured       Top Interview 150
Link           https://leetcode.com/problems/word-pattern
Link           https://leetcode.com/problems/word-pattern/description/?envType=study-plan-v2&envId=top-interview-150

Word Pattern

	Given a pattern and a string s, find if s follows the same pattern.

	Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.


Example 1:

	Input: pattern = "abba", s = "dog cat cat dog"
	Output: true

Example 2:

	Input: pattern = "abba", s = "dog cat cat fish"
	Output: false

Example 3:

	Input: pattern = "aaaa", s = "dog cat cat dog"
	Output: false


Constraints:

1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lowercase English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.
*/

package main

import (
	"fmt"
	"strings"
)

type testData struct {
	pattern string
	s       string
	output  bool
}

func main() {
	data := []testData{
		{"abba", "dog cat cat dog", true},
		{"abba", "dog cat cat fish", false},
		{"aaaa", "dog cat cat dog", false},
		{"abba", "dog dog dog dog", false},
		{"aaa", "aa aa aa aa", false},
	}
	for _, v := range data {
		exp := v.output
		ret := wordPattern(v.pattern, v.s)
		if exp == ret {
			const ifOk, ifNot = "matches", "doesn't"
			var result string
			if ret == true {
				result = ifOk
			} else {
				result = ifNot
			}
			fmt.Printf("OK (%s) %s:%s\n", result, v.pattern, v.s)
		} else {
			fmt.Printf("ERROR    %s:%s exp=%v ret=%v\n", v.pattern, v.s, exp, ret)
		}
	}
	fmt.Println()
}

func wordPattern(pattern string, s string) bool {
	forward := make(map[byte]string)
	backward := make(map[string]byte)
	values := strings.Fields(s)
	if len(pattern) != len(values) {
		return false
	}
	for i := 0; i < len(pattern); i++ {
		k := pattern[i]
		v := values[i]
		existedVal, exists := forward[k]
		if exists && existedVal != v {
			return false
		} else {
			existedVal, exists := backward[v]
			if exists && existedVal != k {
				return false
			}
			forward[k] = v
			backward[v] = k
		}
	}
	return true
}
