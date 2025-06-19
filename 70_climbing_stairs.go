//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Climbing Stairs
id             70
Difficulty     Easy
Tags           math, dynamic programming, memoization
Link           https://leetcode.com/problems/climbing-stairs


You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?


Example 1:

	Input: n = 2
	Output: 2
	Explanation: There are two ways to climb to the top.
	1. 1 step + 1 step
	2. 2 steps

Example 2:

	Input: n = 3
	Output: 3
	Explanation: There are three ways to climb to the top.
	1. 1 step + 1 step + 1 step
	2. 1 step + 2 steps
	3. 2 steps + 1 step


Constraints:

	1 <= n <= 45
*/

package main

import (
	"fmt"
)

func main() {
	data := [][]int{
		{0, 1},
		{1, 1}, // 1 way
		{2, 2}, // 1+1 or 2
		{3, 3}, // 1+1+1, 1+2, 2+1
		{4, 5}, // 1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 2+2
		{5, 8}, // Fibonacci(6)
		{6, 13},
		{7, 21},
		{8, 34},
		{9, 55},
		{10, 89},
		{20, 10946},
		{30, 1346269},
	}
	for _, v := range data {
		n := v[0]
		exp := v[1]
		ret := climbStairs(n)
		if exp == ret {
			fmt.Printf("OK    %v->%v\n", n, ret)
		} else {
			fmt.Printf("ERROR %v->%v exp=%v\n", n, ret, exp)
		}
	}
	fmt.Println()
}

// Dynamic programming bottom-up solution
func climbStairs(n int) int {
	var mem [46]int
	mem[0] = 0 // Won't use this value
	mem[1] = 1
	mem[2] = 2

	if n < 3 {
		return mem[n]
	}

	for i := 3; i <= n; i++ {
		mem[i] = mem[i-1] + mem[i-2]
	}

	return mem[n]
}

// Recursion. Will cause stack overflow on large numbers
func climbStairs2(n int) int {
	if n == 1 {
		return 1
	} else if n == 2 {
		return 2
	}

	return climbStairs2(n-2) + climbStairs2(n-1)
}
