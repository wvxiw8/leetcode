//go:build ignore
// +build ignore

/*
Author         wvxiw
Title          Leetcode task
Task           Linked List Cycle II
id             142
Difficulty     Medium
Tags           hash table,  linked list, two pointers
Link           https://leetcode.com/problems/linked-list-cycle-ii


Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.


Example 1:

	Input: head = [3,2,0,-4], pos = 1
	Output: tail connects to node index 1
	Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:

	Input: head = [1,2], pos = 0
	Output: tail connects to node index 0
	Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:

	Input: head = [1], pos = -1
	Output: no cycle
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
		{[]int{-1, -7, 7, -4, 19, 6, -9, -5, -2, -5}, 9},
		{[]int{1, 2, 3, 4}, 0},
	}
	for _, v := range data {
		arr := v.data
		pos := v.pos

		head, expCycle := NewListCycled(arr, pos)
		fmt.Printf("cycle is %v\n", expCycle)

		head.PrintNoCycle(len(arr) + 3)
		ret := detectCycle(head)
		if ret == expCycle {
			fmt.Printf("OK    arr=%v cycle=%p:%v\n", arr, ret, ret)
		} else {
			fmt.Printf("ERROR arr=%v cycle=%p%v exp=%p%v\n", arr, ret, ret, expCycle, expCycle)
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
func detectCycle(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return nil
	}

	slow, fast := head, head

	for fast != nil && fast.Next != nil {
		fast = fast.Next.Next
		slow = slow.Next
		//fmt.Printf("s=%p.%v f=%p.%v\n", slow, slow.Val, fast, fast.Val)

		// Ok, now the two pointers met somewhere within the cycled region
		if fast == slow {
			break
		}

	}

	if fast != slow {
		return nil
	}

	// Leave the slow where it is and start another slow from the head until they meet. You may check for formulas somewhere how it's working exactly, lets just stik to the algorithm.
	slow2 := head
	for slow2 != slow {
		slow = slow.Next
		slow2 = slow2.Next
	}
	return slow2
}
