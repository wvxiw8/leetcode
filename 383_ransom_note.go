//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Ransom Note
id             383
Difficulty     Easy
Tags           hash table, string, counting
Featured       Top Interview 150
Link           https://leetcode.com/problems/ransom-note/
Link           https://leetcode.com/problems/ransom-note/description/?envType=study-plan-v2&envId=top-interview-150

Ransom Note

	Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

	Each letter in magazine can only be used once in ransomNote.


Example 1:

	Input: ransomNote = "a", magazine = "b"
	Output: false


Example 2:

	Input: ransomNote = "aa", magazine = "ab"
	Output: false

Example 3:

	Input: ransomNote = "aa", magazine = "aab"
	Output: true


Constraints:

1 <= ransomNote.length, magazine.length <= 10^5
ransomNote and magazine consist of lowercase English letters.
*/

package main

import (
	"fmt"
)

type testData struct {
	ransomNote string
	magazine   string
	output     bool
}

func main() {
	data := []testData{
		{"a", "b", false},
		{"aa", "ab", false},
		{"aa", "aab", true},
	}

	for _, v := range data {
		exp := v.output
		ret := canConstruct(v.ransomNote, v.magazine)
		if exp == ret {
			fmt.Printf("OK    %s:%s\n", v.ransomNote, v.magazine)
		} else {
			fmt.Printf("ERROR exp=%v ret=%v ransomNote=%s magazine=%s\n", exp, ret, v.ransomNote, v.magazine)
		}
		fmt.Println()
	}
}

// Using array
func canConstruct(ransomNote string, magazine string) bool {
	var quantity [26 + int('a')]int
	for _, v := range magazine {
		quantity[v]++
	}

	for _, v := range ransomNote {
		if quantity[v] == 0 {
			return false
		} else {
			quantity[v]--
		}
	}

	return true
}

// Using hashmap
func canConstruct2(ransomNote string, magazine string) bool {
	magazineLetters := make(map[byte]int)

	for i := 0; i < len(magazine); i++ {
		b := magazine[i]
		magazineLetters[b] = magazineLetters[b] + 1
	}
	// fmt.Println(magazineLetters)

	for i := 0; i < len(ransomNote); i++ {
		letter := ransomNote[i]
		num, presents := magazineLetters[letter]
		if presents == true && num > 0 {
			magazineLetters[letter] = num - 1
		} else {
			return false
		}
	}

	return true
}
