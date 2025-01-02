//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Remove Duplicates from Sorted List
id             83
Difficulty     Easy
Tags           linked list
Link           https://leetcode.com/problems/remove-duplicates-from-sorted-list/

Remove Duplicates from Sorted List

	Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.


Example 1:

	Input: head = [1,1,2]
	Output: [1,2]

Example 2:

	Input: head = [1,1,2,3,3]
	Output: [1,2,3]


Constraints:

	The number of nodes in the list is in the range [0, 300].
	-100 <= Node.val <= 100
	The list is guaranteed to be sorted in ascending order.
*/

package main

import (
	"fmt"

	. "github.com/wvxiw8/leetcode/lib/go/list"
	. "github.com/wvxiw8/leetcode/lib/go/slice"
)

type testData struct {
	input  []int
	output []int
}

func main() {
	data := []testData{
		{[]int{1, 1, 2}, []int{1, 2}},
		{[]int{1, 1, 2, 3, 3}, []int{1, 2, 3}},
		{[]int{}, []int{}},
	}

	for _, v := range data {
		exp := v.output
		head := NewList(v.input)
		newList := deleteDuplicates(head)
		ret := ListToSlice(newList)
		if CompareEqual(ret, exp) {
			fmt.Printf("OK   in=%v out=%v\n", v.input, ret)
		} else {
			fmt.Printf("FAIL in=%v out=%v exp=%v\n", v.input, ret, v.output)
		}
	}
	fmt.Println()
}

func deleteDuplicates(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}

	n := head
	for n.Next != nil {
		if n.Val == n.Next.Val {
			n.Next = n.Next.Next
		} else {
			n = n.Next
		}
	}

	return head
}
