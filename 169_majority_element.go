//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Majority Element
id             169
Difficulty     Easy
Tags           array, hash table, divide and conquer, sorting, counting
Link           https://leetcode.com/problems/majority-element
Link		   https://leetcode.com/problems/majority-element/description/?envType=study-plan-v2&envId=top-interview-150

Majority Element

	Given an array nums of size n, return the majority element.

	The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.


Example 1:

	Input: nums = [3,2,3]
	Output: 3

Example 2:

	Input: nums = [2,2,1,1,1,2,2]
	Output: 2


Constraints:

n == nums.length
1 <= n <= 5 * 10^4
-10^9 <= nums[i] <= 10^9


Follow-up: Could you solve the problem in linear time and in O(1) space?
*/

package main

import (
	"fmt"
)

type testData struct {
	input  []int
	output int
}

func main() {
	data := []testData{
		{[]int{3, 2, 3}, 3},
		{[]int{2, 2, 1, 1, 1, 2, 2}, 2},
	}

	for _, v := range data {
		exp := v.output
		ret := majorityElement(v.input)
		if exp == ret {
			fmt.Printf("OK   in=%v out=%v\n", v.input, ret)
		} else {
			fmt.Printf("FAIL in=%v out=%v exp=%v\n", v.input, ret, v.output)
		}
	}
	fmt.Println()
}

func majorityElement(nums []int) int {
	m := make(map[int]int)
	need := (len(nums) + 1) / 2
	for _, num := range nums {
		v := m[num]
		v++
		if v >= need {
			return num
		}
		m[num] = v
	}
	return 0xffffffff
}
