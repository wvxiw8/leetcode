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
	"slices"
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
		ret := maximumPopulation3(v.input)
		if exp == ret {
			fmt.Printf("OK    %v->%v\n", v.input, ret)
		} else {
			fmt.Printf("ERROR %v->%v exp=%v\n", v.input, ret, exp)
		}
	}
	fmt.Println()
}

// 1. Direct solution
func maximumPopulation1(logs [][]int) int {
	const size = 2050 - 1950 + 1
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

// 2. Efficient solution using line sweep algorithm
func maximumPopulation2(logs [][]int) int {
	m := make(map[int]int) // year[population]

	// Put chagning years to a map
	for _, span := range logs {
		m[span[0]]++ // Birth in span[0] year
		m[span[1]]-- // Death in span[1] year
	}

	// Sort the years
	years := make([]int, 0, len(m))
	for y := range m {
		years = append(years, y)
	}
	slices.Sort(years)

	// Walk the map in order(i.e. sweep the line) and find max
	pop, maxPop, yearMaxPop := 0, 0, 0
	//fmt.Println("\nYear | Diff | Population")
	for _, y := range years {
		pop += m[y]
		//fmt.Printf("%4d    %+d        %d\n", y, m[y], pop)
		if pop > maxPop {
			maxPop = pop
			yearMaxPop = y
		}
	}

	return yearMaxPop
}

// 3. Efficient solution using prefix sum algorithm
func maximumPopulation3(logs [][]int) int {
	const size = 2050 - 1950 + 1
	offset := 1950
	n := [size]int{}

	for _, span := range logs {
		n[span[0]-offset]++ // Birth in span[0] year
		n[span[1]-offset]-- // Death in span[1] year
	}

	pop, maxPop, yearMaxPop := 0, 0, 0
	//fmt.Println("\nFor ", logs, "\nYear | Diff | Population")
	for i := range n {
		pop += n[i] // `pop` is a prefix sum, but we do not store it for every value of the input array, just use do determine max value
		//fmt.Printf("%4d    %+d        %d\n", i+offset, n[i], pop)
		if pop > maxPop {
			maxPop = pop
			yearMaxPop = i + offset
		}
	}

	return yearMaxPop
}
