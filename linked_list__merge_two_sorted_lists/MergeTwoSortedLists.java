/* 

https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/771/

Merge Two Sorted Lists

    You are given the heads of two sorted linked lists list1 and list2.
    Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
    Return the head of the merged linked list.

 

Example 1:

    Input: list1 = [1,2,4], list2 = [1,3,4]
    Output: [1,1,2,3,4,4]

Example 2:

    Input: list1 = [], list2 = []
    Output: []

Example 3:

    Input: list1 = [], list2 = [0]
    Output: [0]

 

Constraints:

    The number of nodes in both lists is in the range [0, 50].
    -100 <= Node.val <= 100
    Both list1 and list2 are sorted in non-decreasing order.

*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class ListNode {
    public int val;
    public ListNode next;

    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    


    ListNode(int[] a) { 
        ListNode node = this;
        ListNode after = null;
        for (int i = a.length - 1; i >= 0; --i) {
            node = new ListNode(a[i]);
            node.next = after;
            after = node;
        }
        this.val = node.val;
        this.next = node.next;
    }
    
    public void dump() {
        ListNode node = this;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        } 
        System.out.println();
    }
}


class Solution {
    private ListNode out;
    private ListNode prev;

    private ListNode add(int value) {
        ListNode node = new ListNode(value);
        if (out == null) {
            out = node;
            prev = node;
        } else {
            prev.next = node;
        }
        return node;
    }
    
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode c;
        int v1, v2;
        final int MAX = Integer.MAX_VALUE;
        for (;;) {
            v1 = list1 == null ? MAX : list1.val;
            v2 = list2 == null ? MAX : list2.val;
            if (v1 == v2) {
                if (v1 != MAX) {
                    c = add(v1);
                    prev = c;
                    c = add(v2);
                    list1 = list1.next;
                    list2 = list2.next;
                } else {
                    break;
                }
            } else if (v1 > v2) {
                c = add(v2);
                list2 = list2.next;
            } else {
                c = add(v1);
                list1 = list1.next;
            }
            prev = c;
        }
        return out;
    }
}


public class MergeTwoSortedLists {
    public static void main(String[] args) {
        int[] list1 = {1,2,3}; int[] list2 = {1,3,4};
        
        Solution solution = new Solution();
        
        ListNode l1 = new ListNode(list1);
        ListNode l2 = new ListNode(list2);
        ListNode l3 = solution.mergeTwoLists(l1, l2);
        l3.dump();
    }
}