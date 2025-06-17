//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Points That Intersect With Cars
id             2848
Difficulty     Easy
Tags           array, hash table, prefix sum, (sweep line)
Link           https://leetcode.com/problems/points-that-intersect-with-cars/


You are given a 0-indexed 2D integer array nums representing the coordinates of the cars parking on a number line. For any index i, nums[i] = [starti, endi] where starti is the starting point of the ith car and endi is the ending point of the ith car.

Return the number of integer points on the line that are covered with any part of a car.


Example 1:
	Input: nums = [[3,6],[1,5],[4,7]]
	Output: 7
	Explanation: All the points from 1 to 7 intersect at least one car, therefore the answer would be 7.


Example 2:
	Input: nums = [[1,3],[5,8]]
	Output: 7
	Explanation: Points intersecting at least one car are 1, 2, 3, 5, 6, 7, 8. There are a total of 7 points, therefore the answer would be 7.


Constraints:
	1 <= nums.length <= 100
	nums[i].length == 2
	1 <= starti <= endi <= 100
*/

package main

import (
	"fmt"
)

type testData struct {
	input  [][]int
	output int
}

func main() {
	data := []testData{
		{[][]int{{3, 6}, {1, 5}, {4, 7}}, 7},
		{[][]int{{1, 3}, {5, 8}}, 7},
		{[][]int{{28, 88}, {9, 88}, {52, 100}, {4, 86}, {26, 70}, {43, 81}, {66, 94}, {15, 74}, {10, 15}, {17, 54}, {65, 73}, {70, 100}, {4, 61}, {78, 93}, {78, 95}, {42, 59}, {99, 100}, {78, 97}, {39, 72}, {2, 83}, {23, 54}, {14, 94}, {50, 59}, {43, 56}, {50, 80}, {57, 75}, {59, 75}, {50, 59}, {83, 85}, {82, 99}, {38, 75}, {49, 51}, {17, 27}, {69, 96}, {1, 13}, {1, 84}, {14, 68}, {23, 36}, {7, 41}, {72, 80}, {2, 4}, {17, 53}, {54, 82}, {10, 83}}, 100},
	}
	for _, v := range data {
		exp := v.output
		ret := numberOfPoints(v.input)
		if exp == ret {
			fmt.Printf("OK    %v->%v\n", v.input, ret)
		} else {
			fmt.Printf("ERROR %v->%v exp=%v\n", v.input, ret, exp)
		}
	}
	fmt.Println()
}

func numberOfPoints(nums [][]int) int {
	const size = 101
	a := [size + 1]int{}
	for _, section := range nums {
		beg := section[0] // Beginning of a section
		end := section[1] // End of a section (inclusive)
		a[beg]++
		a[end+1]--
	}

	prefixSum := 0
	result := 0
	for i := range a {
		prefixSum += a[i] // prefixSum shows number of overlapped sections (cars)
		if prefixSum > 0 {
			result++ // Count not zero elements
		}
	}
	return result
}
