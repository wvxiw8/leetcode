//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Sort Array By Parity
id             905
Difficulty     Easy
Tags           array, two pointers, sorting
Link           https://leetcode.com/problems/sort-array-by-parity/

Sort Array By Parity
	Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
	Return any array that satisfies this condition.

Example 1:
	Input: nums = [3,1,2,4]
	Output: [2,4,3,1]
	Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

Example 2:
	Input: nums = [0]
	Output: [0]

Constraints:
	1 <= nums.length <= 5000
	0 <= nums[i] <= 5000
*/

package main

import (
	"fmt"
)

type testData struct {
	input  []int
	output []int
}

func main() {
	data := []testData{
		{[]int{3, 1, 2, 4}, []int{2, 4, 1, 3}},
		{[]int{0}, []int{0}},
	}
	for _, v := range data {
		exp := v.output
		ret := sortArrayByParity(v.input)
		if compareEqual(exp, ret) {
			fmt.Printf("OK    %v->%v\n", v.input, ret)
		} else {
			fmt.Printf("ERROR %v->%v exp=%v\n", v.input, ret, exp)
		}
	}
	fmt.Println()
}

func compareEqual(a []int, b []int) bool {
	if len(a) != len(b) {
		return false
	}
	for i, v := range a {
		if v != b[i] {
			return false
		}
	}
	return true
}

func sortArrayByParity(nums []int) []int {
	a := make([]int, len(nums))
	b, e := 0, len(nums)-1
	for i := 0; i < len(nums); i++ {
		if nums[i]&1 == 0 {
			a[b] = nums[i]
			b++
		} else {
			a[e] = nums[i]
			e--
		}
	}
	return a
}
