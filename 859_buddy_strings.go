//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Buddy Strings
id             859
Difficulty     Easy
Tags           hash table, string
Link           https://leetcode.com/problems/buddy-strings/

Buddy Strings
	Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.
	Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].
	For example, swapping at indices 0 and 2 in "abcd" results in "cbad".


Example 1:
	Input: s = "ab", goal = "ba"
	Output: true
	Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.

Example 2:
	Input: s = "ab", goal = "ab"
	Output: false
	Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.

Example 3:
	Input: s = "aa", goal = "aa"
	Output: true
	Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.

Constraints:
	1 <= s.length, goal.length <= 2 * 10^4
	s and goal consist of lowercase letters.
*/

package main

import (
	"fmt"
)

type testData struct {
	s      string
	goal   string
	output bool
}

func main() {
	data := []testData{
		{s: "ab", goal: "ba", output: true},
		{s: "ab", goal: "ab", output: false},
		{s: "aa", goal: "aa", output: true},
		{s: "abcd", goal: "cbad", output: true},
		{s: "abcdefy", goal: "abcdfez", output: false},
		{s: "abc", goal: "acd", output: false},
	}

	for _, v := range data {
		exp := v.output
		ret := buddyStrings(v.s, v.goal)
		if ret == exp {
			fmt.Printf("OK   s=%v goal=%v out=%v\n", v.s, v.goal, ret)
		} else {
			fmt.Printf("FAIL s=%v goal=%v out=%v exp=%v\n", v.s, v.goal, ret, v.output)
		}
	}
	fmt.Println()
}

func buddyStrings(s string, goal string) bool {
	if len(s) != len(goal) {
		return false
	}

	var m [26 + 'a']int // Store the number of repeats for each letter
	mostRepeated := 0   // The most repeated letter
	diffNum := 0        // Quantity of pairs which don't match
	var diff [2][2]int  // Map of not-matching pairs {{s[i], goal[i]}, {s[j], goal[j]}}

	for i := 0; i < len(s); i++ {
		v := s[i]
		w := goal[i]

		if v == w {
			m[v]++
			if m[v] > mostRepeated {
				mostRepeated = m[v]
			}
		} else {
			diffNum++
			if diffNum > 2 {
				return false
			}
			diff[diffNum-1][0] = int(v)
			diff[diffNum-1][1] = int(w)
		}
	}

	if diffNum == 0 && mostRepeated >= 2 {
		return true
	} else if diffNum == 2 && diff[0][0] == diff[1][1] && diff[0][1] == diff[1][0] {
		return true
	}
	return false // diffNum is 1
}
