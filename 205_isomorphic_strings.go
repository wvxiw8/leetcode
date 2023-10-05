//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Isomorphic Strings
id             205
Difficulty     Easy
Tags           hash table, string
Featured       Top Interview 150
Link           https://leetcode.com/problems/isomorphic-strings/
Link           https://leetcode.com/problems/isomorphic-strings/?envType=study-plan-v2&envId=top-interview-150

Isomorphic Strings

	Given two strings s and t, determine if they are isomorphic.

	Two strings s and t are isomorphic if the characters in s can be replaced to get t.

	All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.


Example 1:

	Input: s = "egg", t = "add"
	Output: true


Example 2:

	Input: s = "foo", t = "bar"
	Output: false


Example 3:

	Input: s = "paper", t = "title"
	Output: true


Constraints:

1 <= s.length <= 5 * 10^4
t.length == s.length
s and t consist of any valid ascii character.
*/

package main

import (
	"fmt"
)

type testData struct {
	s      string
	t      string
	output bool
}
type functor struct {
	function func(string, string) bool
	name     string
}

func main() {
	data := []testData{
		{"egg", "add", true},
		{"foo", "bar", false},
		{"paper", "title", true},
		{"baba", "badc", false},
		{"badc", "baba", false},
		{"egcd", "adfd", false},
		{"abcdefghijklmnopqrstuvwxyz", "nopqrstuvwxyzabcdefghijklm", true},
	}

	functors := []functor{{isIsomorphicHashmap, "isIsomorphicHashmap"}, {isIsomorphicArray, "isIsomorphicArray"}}
	for _, isIsomorphic := range functors {
		fmt.Println("function = " + isIsomorphic.name)
		for _, v := range data {
			exp := v.output
			res := isIsomorphic.function(v.s, v.t)

			if exp == res {
				const strIso, strNotIso = "iso", "not"
				var kind string
				if res == true {
					kind = strIso
				} else {
					kind = strNotIso
				}

				fmt.Printf("OK (%s) %s:%s\n", kind, v.s, v.t)
			} else {
				fmt.Printf("ERROR    %s:%s exp=%v res=%v\n", v.s, v.t, exp, res)
			}
		}
		fmt.Println()
	}
}

// Using hashmap
func isIsomorphicHashmap(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}

	s2t := make(map[byte]byte)
	t2s := make(map[byte]byte)
	for i := 0; i < len(s); i++ {
		charS := s[i]
		charT := t[i]

		if s2t[charS] == 0 {
			if t2s[charT] != charS {
				if t2s[charT] != 0 {
					return false
				}
				t2s[charT] = charS
			}
			s2t[charS] = charT

		} else if s2t[charS] != charT {
			return false
		}
	}
	return true
}

// Using arrays
func isIsomorphicArray(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}

	const size int = 128
	var s2t []byte = make([]byte, size, size)
	var t2s []byte = make([]byte, size, size)
	for i := 0; i < len(s); i++ {
		charS := s[i]
		charT := t[i]
		if s2t[charS] == 0 {
			if t2s[charT] != charS {
				if t2s[charT] != 0 {
					return false
				}
				t2s[charT] = charS
			}
			s2t[charS] = charT

		} else if s2t[charS] != charT {
			return false
		}
	}
	return true
}
