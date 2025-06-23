package list

import "fmt"

// Definition for singly-linked list.
type ListNode struct {
	Val  int
	Next *ListNode
}

func NewList(data []int) *ListNode {
	var node *ListNode = nil
	var next *ListNode = nil
	for i := len(data) - 1; i >= 0; i-- {
		node = &ListNode{data[i], next}
		next = node
	}
	return node
}

func ListToSlice(head *ListNode) []int {
	if head == nil {
		return []int{}
	}

	len := 0
	n := head
	for n != nil {
		n = n.Next
		len++
	}

	a := make([]int, len)
	n = head
	for i := range a {
		a[i] = n.Val
		n = n.Next
	}

	return a
}

// NewListCycled creates a linked list with the tail element pointing to the [cycle]. Uncycled if cycle=-1
func NewListCycled(data []int, cycle int) (head *ListNode, cycleBegins *ListNode) {
	var node *ListNode = nil
	var next *ListNode = nil
	var cycling *ListNode = nil
	var last *ListNode = nil
	n := len(data) - 1
	for i := n; i >= 0; i-- {
		node = &ListNode{data[i], next}
		if i == cycle {
			cycling = node
		} else if i == n {
			last = node
		}
		next = node
	}
	if cycle != -1 {
		last.Next = cycling
		cycleBegins = cycling
	} else {
		cycleBegins = nil
	}
	head = node
	return
}

func (l *ListNode) Print(max int) {

	if l == nil {
		fmt.Println("[nil node]")
		return
	}
	for i := 0; i < max; i++ {
		fmt.Printf("[%d] %p %d\n", i, l, l.Val)
		if l.Next == nil || i > max {
			break
		}
		l = l.Next
	}
}
func (l *ListNode) PrintNoCycle(max int) {
	m := make(map[*ListNode]*ListNode)

	if l == nil {
		fmt.Println("[nil node]")
		return
	}
	for i := 0; i < max; i++ {
		if _, exist := m[l]; exist {
			fmt.Printf("[%d] cycle detected; prev node points to %p (value = %v)\n", i, l, l.Val)
			return
		}
		m[l] = l
		fmt.Printf("[%d] %p %d\n", i, l, l.Val)
		if l.Next == nil || i > max {
			break
		}
		l = l.Next
	}
}
