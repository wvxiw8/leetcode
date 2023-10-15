//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Summary Ranges
id             228
Difficulty     Easy
Tags           array
Featured       Top Interview 150
Link           https://leetcode.com/problems/summary-ranges/
Link           https://leetcode.com/problems/summary-ranges/description/?envType=study-plan-v2&envId=top-interview-150

Summary Ranges
    You are given a sorted unique integer array nums.
	A range [a,b] is the set of all integers from a to b (inclusive).
	Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
	Each range [a,b] in the list should be output as:
	"a->b" if a != b
	"a" if a == b

Example 1:
	Input: nums = [0,1,2,4,5,7]
	Output: ["0->2","4->5","7"]
	Explanation: The ranges are:
	[0,2] --> "0->2"
	[4,5] --> "4->5"
	[7,7] --> "7"

Example 2:
	Input: nums = [0,2,3,4,6,8,9]
	Output: ["0","2->4","6","8->9"]
	Explanation: The ranges are:
	[0,0] --> "0"
	[2,4] --> "2->4"
	[6,6] --> "6"
	[8,9] --> "8->9"

Constraints:
	0 <= nums.length <= 20
	-2^31 <= nums[i] <= 2^31 - 1
	All the values of nums are unique.
	nums is sorted in ascending order.
*/

package main

import (
	"fmt"
	"strconv"
)

type testData struct {
	input    []int
	expected []string
}

func main() {
	data := []testData{
		// {[]int{0, 1, 2, 4, 5, 7}, []string{"0->2", "4->5", "7"}},
		{[]int{0, 2, 3, 4, 6, 8, 9}, []string{"0", "2->4", "6", "8->9"}},
		{[]int{}, nil},
		{[]int{0}, []string{"0"}},
		{[]int{0, 1}, []string{"0->1"}},
		{[]int{0, 1, 2}, []string{"0->2"}},
		{[]int{0, 1, 2, 5}, []string{"0->2", "5"}},
	}

	for _, v := range data {
		err := false
		ret := summaryRanges(v.input)
		for i, str := range ret {
			if str != v.expected[i] {
				fmt.Printf("ERROR i=%d exp=%v ret=%v input=%v\n", i, v.expected, ret, v.input)
				err = true
				break
			}
		}
		if !err {
			fmt.Printf("OK    input=%v output=%v\n", v.input, ret)
		}
	}
}

func summaryRanges(nums []int) []string {
	if len(nums) == 0 {
		return nil
	} else if len(nums) == 1 {
		return []string{strconv.Itoa(nums[0])}
	}

	out := make([]string, 0, 20)
	beg, prev := nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		n := nums[i]
		if n != prev+1 {
			if beg == prev {
				out = append(out, strconv.Itoa(prev))
			} else {
				out = append(out, fmt.Sprintf("%s->%s", strconv.Itoa(beg), strconv.Itoa(prev)))
			}
			beg = n
		}
		prev = n
	}

	last := nums[len(nums)-1]
	if beg == prev {
		out = append(out, strconv.Itoa(last))
	} else {
		out = append(out, fmt.Sprintf("%s->%s", strconv.Itoa(beg), strconv.Itoa(last)))
	}

	return out
}
