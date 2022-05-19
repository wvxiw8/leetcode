/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Reverse Linked List
 * @id             206
 * @Difficulty     Easy
 * @Tags           linked list
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/560/

Reverse Linked List

    Given the head of a singly linked list, reverse the list, and return the reversed list.


Example 1:

    Input: head = [1,2,3,4,5]
    Output: [5,4,3,2,1]

Example 2:

    Input: head = [1,2]
    Output: [2,1]

Example 3:

    Input: head = []
    Output: []

 

Constraints:

    The number of nodes in the list is the range [0, 5000].
    -5000 <= Node.val <= 5000


Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?

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
    int val;
    ListNode next;

    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }


    ListNode(int[] a) { 
        ListNode next = null;
        ListNode node = this;
        for (int i = a.length - 1; i >= 0; --i) {
            node = new ListNode(a[i], next);
            next = node;
        }
        this.val = node.val;
        this.next = node.next;
    }
    
    public ListNode next() { 
        return this.next; 
    }
    
    public int size() {
        int i = 0;
        ListNode node = this;
        do {
            ++i;
        } while ((node = node.next()) != null);
        return i;
    }

    public void dump() {
        ListNode node = this;
        while (node != null) {
            System.out.printf("%s = %d\n", node.toString(), node.val);
            node = node.next();
        } 
        System.out.println();
    }
}


class Solution {
    public ListNode reverseList(ListNode head) {
        int i;
        int size = 0;        
        ListNode node = head;

        while (node != null) {
            ++size;
            node = node.next; // Yeah, no getter given for ListNode.next
        } 

        int[] a = new int[size];
        node = head;
        for (i = size-1; i >= 0; --i) {
            a[i] = node.val;
            node = node.next;
        }
        
        ListNode reversed = size > 0 ? new ListNode() : null;
        ListNode c = reversed; 
        for (i = 0; i < size; ++i) {
            ListNode n = new ListNode();
            c.val = a[i];
            if (i == size-1) {
                c.next = null;
                break;
            }
            c.next = n;
            c = n;
        }
        
        return reversed;
    }
}


class ReverseLinkedList {
    public static void main(String[] args) {
        int[] in = {2,3,4,5,6}; int[] out = {6,5,4,3,2};
        // int[] in = {1,2}; int[] out = {2,1};
        // int[] in = {}; int[] out = {};
        
        Solution solution = new Solution();
        
        ListNode list = new ListNode(in);
        list.dump();
        ListNode reversed = solution.reverseList(list);
        if (reversed != null) {
            reversed.dump();
        }
    }
}