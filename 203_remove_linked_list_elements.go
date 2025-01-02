//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Remove Linked List Elements
id             203
Difficulty     Easy
Tags           linked list, recursion
Link           https://leetcode.com/problems/remove-linked-list-elements/

Remove Linked List Elements
	Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.

Example 1:
	Input: head = [1,2,6,3,4,5,6], val = 6
	Output: [1,2,3,4,5]

Example 2:
	Input: head = [], val = 1
	Output: []

Example 3:
	Input: head = [7,7,7,7], val = 7
	Output: []


Constraints:
	The number of nodes in the list is in the range [0, 10^4].
	1 <= Node.val <= 50
	0 <= val <= 50
*/

package main

import (
	"fmt"

	. "github.com/wvxiw8/leetcode/lib/go/list"
	. "github.com/wvxiw8/leetcode/lib/go/slice"
)

type testData struct {
	input  []int
	val    int
	output []int
}

func main() {
	data := []testData{
		{[]int{1, 2, 6, 3, 4, 5, 6}, 6, []int{1, 2, 3, 4, 5}},
		{[]int{}, 1, []int{}},
		{[]int{7, 7, 7, 7}, 7, []int{}},
		{[]int{5}, 5, []int{}},
		{[]int{6, 6}, 6, []int{}},
	}

	for _, v := range data {
		exp := v.output
		head := NewList(v.input)
		newList := removeElements(head, v.val)
		ret := ListToSlice(newList)
		if CompareEqual(ret, exp) {
			fmt.Printf("OK   in=%v val=%v out=%v\n", v.input, v.val, ret)
		} else {
			fmt.Printf("FAIL in=%v val=%v out=%v exp=%v\n", v.input, v.val, ret, v.output)
		}
	}
	fmt.Println()
}

func removeElements(head *ListNode, val int) *ListNode {
	if head == nil {
		return nil
	}

	n := head
	for n.Next != nil {
		if n.Next.Val == val {
			n.Next = n.Next.Next
		} else {
			n = n.Next
		}
	}

	if head.Val == val {
		return head.Next
	} else {
		return head
	}
}
