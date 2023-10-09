//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Excel Sheet Column Number
id             171
Difficulty     Easy
Tags           math, string
Link           https://leetcode.com/problems/excel-sheet-column-number

Excel Sheet Column Number

	Given a string columnTitle that represents the column title as appears in an Excel sheet, return its corresponding column number.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...


Example 1:

	Input: columnTitle = "A"
	Output: 1

Example 2:

	Input: columnTitle = "AB"
	Output: 28

Example 3:

	Input: columnTitle = "ZY"
	Output: 701


Constraints:

	1 <= columnTitle.length <= 7
	columnTitle consists only of uppercase English letters.
	columnTitle is in the range ["A", "FXSHRXW"].
*/

package main

import (
	"fmt"
)

type testData struct {
	input  string
	output int
}

func main() {
	data := []testData{
		{"A", 1},
		{"B", 2},
		{"Y", 25},
		{"Z", 26},
		{"AA", 27},
		{"AB", 28},
		{"ZY", 701},
	}

	for _, v := range data {
		exp := v.output
		ret := titleToNumber(v.input)
		if exp == ret {
			fmt.Printf("OK   in=%v out=%v\n", v.input, ret)
		} else {
			fmt.Printf("FAIL in=%v out=%v exp=%v\n", v.input, ret, v.output)
		}
	}
	fmt.Println()
}

func titleToNumber(columnTitle string) int {
	var res int = 0
	var pow int = 1
	for i, n := 0, len(columnTitle)-1; n >= 0; n-- {
		res += int(columnTitle[n]-'A'+1) * pow
		pow *= 26
		i++
	}
	return res
}
