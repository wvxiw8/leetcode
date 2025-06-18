//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           My Calendar II
id             731
Difficulty     Meduim
Tags           array, binary search, design, segment tree, ordered set, prefix sum, (sweep line)
Link           https://leetcode.com/problems/my-calendar-ii/description/


You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.

A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).

The event can be represented as a pair of integers startTime and endTime that represents a booking on the half-open interval [startTime, endTime), the range of real numbers x such that startTime <= x < endTime.

Implement the MyCalendarTwo class:
	- MyCalendarTwo() Initializes the calendar object.
	- boolean book(int startTime, int endTime) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.


Example 1:
	Input
		["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
		[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
	Output
		[null, true, true, true, false, true, true]


Explanation:
	MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
	myCalendarTwo.book(10, 20); // return True, The event can be booked.
	myCalendarTwo.book(50, 60); // return True, The event can be booked.
	myCalendarTwo.book(10, 40); // return True, The event can be double booked.
	myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
	myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
	myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.


Constraints:
	0 <= start < end <= 10^9
	At most 1000 calls will be made to book.
*/

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Book(startTime,endTime);
 */

package main

import (
	"fmt"
	"slices"
)

type testData struct {
	startTime int
	endTime   int
	exp       bool
}

func main() {
	data := [][]testData{
		{
		//	{10, 20, true}, {50, 60, true}, {10, 40, true}, {5, 15, false}, {5, 10, true}, {25, 55, true},
		//}, {
		//	{47, 50, true}, {1, 10, true}, {27, 36, true}, {40, 47, true},
		//	{20, 27, true}, {15, 23, true}, {10, 18, true}, {27, 36, true},
		//	{17, 25, false}, {8, 17, false}, {24, 33, false}, {23, 28, false},
		//	{21, 27, false}, {47, 50, true}, {14, 21, false}, {26, 32, false},
		//	{16, 21, false}, {2, 7, true}, {24, 33, false}, {6, 13, false},
		//	{44, 50, false}, {33, 39, false}, {30, 36, false}, {6, 15, false},
		//	{21, 27, false}, {49, 50, false}, {38, 45, true}, {4, 12, false},
		//	{46, 50, false}, {13, 21, false},
		//}, {
		//	{8726, 10000, true}, {2276, 3902, true}, {9125, 10000, true},
		//	{7518, 8996, true},  {4840, 6800, true}, {8419, 9681, false},
		//	{248, 1480, true},   {2401, 3886, true}, {9946, 10000, false},
		//	{4761, 6648, true},  {3074, 4910, false}, {1543, 2720, false},
		//	{4827, 6363, false}, {4805, 5911, false}, {7380, 8440, true},
		//	{1614, 2719, false}, {9503, 10000, false}, {1303, 3114, false},
		//	{2675, 4299, false}, {2756, 4549, false}, {897, 2819, false},
		//	{7151, 8415, false}, {3227, 4706, false}, {6290, 8072, false},
		//	{320, 1512, true},
		//}, {
		//	{0, 109, true}, {1, 108, true},
		//}, {
			// Testcase #64 (of leetcode)
			{5, 12, true},   {42, 50, true},  {4, 9, true},    {33, 41, true},
			{2, 7, false},   {16, 25, true},  {7, 16, false},  {6, 11, false},
			{13, 18, true},  {38, 43, true},  {49, 50, true},  {6, 15, false},
			{5, 13, false},  {35, 42, false}, {19, 24, true},  {46, 50, false},
			{39, 44, false}, {28, 36, true},  {28, 37, false}, {20, 29, false},
			{41, 49, false}, {11, 19, false}, {41, 46, false}, {28, 37, false},
			{17, 23, false}, {22, 31, false}, {4, 10, false},  {31, 40, false},
			{4, 12, false},  {19, 26, false},
		},
	}

	for i, d := range data {
		fmt.Printf("\n----------- TEST %d ------------\n\n", i)
		obj := Constructor()
		for _, v := range d {
			exp := v.exp
			ret := obj.Book(v.startTime, v.endTime)
			if exp == ret {
				fmt.Printf("OK    %2d-%2d %t\n", v.startTime, v.endTime, exp)
			} else {
				fmt.Printf("ERROR %2d-%2d exp=%t ret=%t\n", v.startTime, v.endTime, exp, ret)
			}
			fmt.Println()
		}
		fmt.Println("__________________________")
	}
	fmt.Println()
}

// 1. Use map (sort it by keys)
type MyCalendarTwo struct {
	m map[int]int
	keys []int // I have to put keys here to keep them more or less sorted that if they were calculated in Book() call every time, because the usecase with 800 pairs didn't meet the Leetcode performance limit
}

func Constructor() MyCalendarTwo {
	return MyCalendarTwo{
		m: make(map[int]int),
		keys: make([]int, 0),
	}
}

// Leverage sweep line / prefix sum algorithm
func (this *MyCalendarTwo) Book(startTime int, endTime int) bool {
	fmt.Printf(" Start Book(%2d,%2d) keys %v %v\n", startTime, endTime, this.keys, this.m)
	defer func() {
			fmt.Printf(" Exit Book(%2d,%2d) keys %v %v\n", startTime, endTime, this.keys, this.m)
		fmt.Println(". . .")

	}()

	_, begExisted := this.m[startTime]
	_, endExisted := this.m[endTime]

	// NOTE, that the key thing here is to optimistically add times to the map and then remove it if exceed.
	// I also tried to keem another map for the prefix sum only, or have a struct for the value like {line int, sum int}, but it makes no sense, just tangled all the code.
	this.m[startTime]++
	this.m[endTime]--

	// Deep copy the slice with keys if going to add new values there (or we need manually search the value in the slice and remove them)
	var backup []int
	if !begExisted || !endExisted {
		backup = make([]int, len(this.keys))
		copy(backup, this.keys)
	}

	// Add keys if new
	if !begExisted {
		fmt.Printf(" add key %d \n", startTime)
		this.keys = append(this.keys, startTime)
	}
	if !endExisted {
		fmt.Printf(" add key %d \n", endTime)
		this.keys = append(this.keys, endTime)
	}

	if !begExisted || !endExisted {
		fmt.Printf(" resort keys slice \n", )
		slices.Sort(this.keys)
	}

	fmt.Printf(" Before loop  keys %v %v\n", this.keys, this.m)

	bookings := 0
	// Walk orderly to be able oto leverage prefix sum
	for _, k := range this.keys {
		bookings += this.m[k] // `bookings` - is a prefix sum for k-th element
		fmt.Printf("  k=%d v=%d, bookNum=%d\n", k, this.m[k], bookings)
		if bookings >= 3 {
			// Restore the state of the map back
			if begExisted {
				this.m[startTime]--
			} else {
				delete(this.m, startTime)
			}
			if endExisted {
				this.m[endTime]++
			} else {
				delete(this.m, endTime)
			}
			// Restore slice with keys
			fmt.Printf("  current %v\n", this.keys)
			fmt.Printf("  backed  %v\n", backup)
			this.keys = backup
			// And say no
			return false
		}
	}
	return true
}
