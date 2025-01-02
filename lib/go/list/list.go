package list

// Definition for singly-linked list.
type ListNode struct {
	Val  int
	Next *ListNode
}

func NewListNode(data []int) *ListNode {
	var node *ListNode = nil
	var next *ListNode = nil
	for i := len(data) - 1; i >= 0; i-- {
		node = &ListNode{data[i], next}
		next = node
	}
	return node
}

func ListToArray(head *ListNode) []int {
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
