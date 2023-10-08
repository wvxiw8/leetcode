//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Contains Duplicate II
id             219
Difficulty     Easy
Tags           array, hash table, sliding window
Featured       Top Interview 150
Link           https://leetcode.com/problems/contains-duplicate-ii/
Link           https://leetcode.com/problems/contains-duplicate-ii/description/?envType=study-plan-v2&envId=top-interview-150

Contains Duplicate II

	Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.


Example 1:

	Input: nums = [1,2,3,1], k = 3
	Output: true


Example 2:

	Input: nums = [1,0,1,1], k = 1
	Output: true


Example 3:

	Input: nums = [1,2,3,1,2,3], k = 2
	Output: false


Constraints:

1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
0 <= k <= 10^5
*/

package main

import (
	"fmt"
)

type testData struct {
	nums   []int
	k      int
	output bool
}

func main() {
	data := []testData{
		{[]int{1, 2, 3, 1}, 3, true},
		{[]int{1, 0, 1, 1}, 1, true},
		{[]int{1, 2, 3, 1, 2, 3}, 2, false},
	}

	for _, v := range data {
		exp := v.output
		ret := containsNearbyDuplicate(v.nums, v.k)
		if exp == ret {
			const ifOk, ifNot = "var", "yok"
			var result string
			if ret == true {
				result = ifOk
			} else {
				result = ifNot
			}
			fmt.Printf("OK (%s) nums=%v k=%v out=%v\n", result, v.nums, v.k, v.output)
		} else {
			fmt.Printf("ERROR    nums=%v k=%v out=%v exp=%v ret=%v\n", v.nums, v.k, v.output, exp, ret)
		}
	}
	fmt.Println()
}

func containsNearbyDuplicate(nums []int, k int) bool {
	m := make(map[int]bool)
	a := make([]int, k+1) // I could do better and avoid using this array at all.

	for i, w, window := 0, 0, k+1; i < len(nums); i++ {
		_, exists := m[nums[i]]
		if exists {
			return true
		}

		m[nums[i]] = true
		a[i%window] = nums[i]

		if w == k {
			delete(m, a[(i+1)%window])
		} else {
			w++
		}

	}
	return false
}
