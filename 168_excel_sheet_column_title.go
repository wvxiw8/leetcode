//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Excel Sheet Column Title
id             168
Difficulty     Easy
Tags           math, string
Link           https://leetcode.com/problems/excel-sheet-column-title/

Excel Sheet Column Title

Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

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

	Input: columnNumber = 1
	Output: "A"

Example 2:

	Input: columnNumber = 28
	Output: "AB"

Example 3:

	Input: columnNumber = 701
	Output: "ZY"


Constraints:

	1 <= columnNumber <= 2^31 - 1
*/

package main

import (
	"fmt"
)

type testData struct {
	input  int
	output string
}

func main() {
	data := []testData{
		{1, "A"},
		{2, "B"},
		{25, "Y"},
		{26, "Z"},
		{27, "AA"},
		{28, "AB"},
		{701, "ZY"},
	}

	for _, v := range data {
		exp := v.output
		ret := convertToTitle(v.input)
		if exp == ret {
			fmt.Printf("OK   in=%v out=%v\n", v.input, ret)
		} else {
			fmt.Printf("FAIL in=%v out=%v exp=%v\n", v.input, ret, v.output)
		}
	}
	fmt.Println()
}

func convertToTitle(columnNumber int) string {
	const SIZE = 8
	a := make([]byte, SIZE)
	i := 0
	for ; columnNumber > 0; i++ {
		r := columnNumber % 26
		columnNumber /= 26
		if r == 0 {
			r = 'Z'
			columnNumber--
		} else {
			r += 'A' - 1
		}
		a[SIZE-i-1] = byte(r)
	}

	return string(a[SIZE-i:])
}
