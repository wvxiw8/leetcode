//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Add two numbers
id             2
Difficulty     Medium
Tags           linked list, math, recursion
Link           https://leetcode.com/problems/add-two-numbers/

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.


Example 1:
	2 -> 4 -> 3
	5 -> 6 -> 4
	___________
	7 -> 0 -> 9
	Input: l1 = [2,4,3], l2 = [5,6,4]
	Output: [7,0,8]
	Explanation: 342 + 465 = 807.

Example 2:

	Input: l1 = [0], l2 = [0]
	Output: [0]

Example 3:

	Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
	Output: [8,9,9,9,0,0,0,1]


Constraints:

	The number of nodes in each linked list is in the range [1, 100].
	0 <= Node.val <= 9
	It is guaranteed that the list represents a number that does not have leading zeros.
*/

package main

import (
	"fmt"

	. "github.com/wvxiw8/leetcode/lib/go/list"
	. "github.com/wvxiw8/leetcode/lib/go/slice"
)

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

type testData struct {
	l1  []int
	l2  []int
	output []int
}

func main() {
	data := []testData{
		{[]int{2,4,3}, []int{5,6,4}, []int{7,0,8}},
		{[]int{0}, []int{0}, []int{0}},
		{[]int{9,9,9,9,9,9,9}, []int{9,9,9,9}, []int{8,9,9,9,0,0,0,1}},
	}
	for _, v := range data {
		exp := v.output
		l1 := NewList(v.l1)
		l2 := NewList(v.l2)
		ret := ListToSlice(addTwoNumbers(l1, l2))
		if CompareEqual(exp, ret) {
			fmt.Printf("OK    %v + %v -> %v\n", v.l1, v.l2, ret)
		} else {
			fmt.Printf("ERROR %v + %v -> %v exp=%v\n", v.l1, v.l2, ret, exp)
		}
	}
	fmt.Println()
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	var v1, v2, sum, carry int

	head := &ListNode{}
	curr := head
	var prev *ListNode

	for l1 != nil || l2 != nil {
		if l1 != nil {
			v1 = l1.Val
			l1 = l1.Next
		} else {
			v1 = 0
		}

		if l2 != nil {
			v2 = l2.Val
			l2 = l2.Next
		} else {
			v2 = 0
		}

		sum = v1 + v2 + carry
		next := &ListNode{}
		curr.Val = sum % 10
		curr.Next = next
		prev = curr
		curr = next

		//fmt.Printf("%d %d %d %d\n", v1, v2, carry, sum)

		if sum > 9 {
			carry = 1
		} else {
			carry = 0
		}
	}

	if prev != nil {
		if carry > 0 {
			prev.Next = &ListNode{Val: 1}
		} else {
			prev.Next = nil
		}
	}

	return head
}
