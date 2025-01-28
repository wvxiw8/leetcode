//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Maximum Population Year
id             1854
Difficulty     Easy
Tags           array, couting, prefix sum, (sweep line)
Link           https://leetcode.com/problems/maximum-population-year


You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.
The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.
Return the earliest year with the maximum population.


Example 1:
	Input: logs = [[1993,1999],[2000,2010]]
	Output: 1993
	Explanation: The maximum population is 1, and 1993 is the earliest year with this population.


Example 2:
	Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
	Output: 1960
	Explanation:
	The maximum population is 2, and it had happened in years 1960 and 1970.
	The earlier year between them is 1960.


Constraints:
	1 <= logs.length <= 100
	1950 <= birthi < deathi <= 2050
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
		{[][]int{{1993, 1999}, {2000, 2010}}, 1993},
		{[][]int{{1950, 1961}, {1960, 1971}, {1970, 1981}}, 1960},
	}
	for _, v := range data {
		exp := v.output
		ret := maximumPopulation(v.input)
		if exp == ret {
			fmt.Printf("OK    %v->%v\n", v.input, ret)
		} else {
			fmt.Printf("ERROR %v->%v exp=%v\n", v.input, ret, exp)
		}
	}
	fmt.Println()
}

// Direct solution
func maximumPopulation(logs [][]int) int {
	const size = 2050 - 1950
	offset := 1950
	n := [size]int{}

	for _, span := range logs {
		begin := span[0] - offset
		end := span[1] - offset
		//fmt.Printf("Process [%d %d]\n", begin+offset, end+offset)
		for i := begin; i < end; i++ {
			n[i]++
		}
	}
	//fmt.Printf("%v\n", n)

	maxValue := 0
	maxIndex := 0
	for i, k := range n {
		if k > maxValue {
			maxValue = k
			maxIndex = i
		}
	}

	return maxIndex + offset
}
