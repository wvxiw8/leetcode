//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Linked List Cycle
id             141
Difficulty     Easy
Tags           hash table,  linked list, two pointers
Link           https://leetcode.com/problems/linked-list-cycle


Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.


Example 1:

	Input: head = [3,2,0,-4], pos = 1
	Output: true
	Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Example 2:

	Input: head = [1,2], pos = 0
	Output: true
	Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Example 3:

	Input: head = [1], pos = -1
	Output: false
	Explanation: There is no cycle in the linked list.


Constraints:

	The number of the nodes in the list is in the range [0, 10^4].
	-10^5 <= Node.val <= 10^5
	pos is -1 or a valid index in the linked-list.

Follow up:

	Can you solve it using O(1) (i.e. constant) memory?
*/

package main

import (
	"fmt"

	. "github.com/wvxiw8/leetcode/lib/go/list"
)

type Data struct {
	data []int
	pos  int
}

func main() {

	data := []Data{
		{[]int{3, 2, 0, -4}, 1},
		{[]int{1, 2}, 0},
		{[]int{1}, -1},
		{[]int{}, -1},
		{[]int{1, 2}, -1},
	}
	for _, v := range data {
		arr := v.data
		pos := v.pos

		head := NewListCycled(arr, pos)
		head.PrintNoCycle(len(arr) + 3)
		ret := hasCycle(head)
		exp := pos != -1
		if exp == ret {
			fmt.Printf("OK    %v %v %t\n", arr, pos, ret)
		} else {
			fmt.Printf("ERROR %v %v %t exp=%t\n", arr, pos, ret, exp)
		}
		fmt.Println()

	}
	fmt.Println()
}

/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func hasCycle(head *ListNode) bool {
	if head == nil || head.Next == nil {
		return false
	}

	slow, fast := head, head.Next

	for fast != nil && fast.Next != nil {
		fmt.Printf("s=%p/%v f=%p/%v\n", slow, slow.Val, fast, fast.Val)
		if fast == slow {
			return true
		}

		fast = fast.Next.Next
		slow = slow.Next
	}
	return false
}
