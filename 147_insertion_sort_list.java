/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Insertion Sort List
 * @id             147
 * @Difficulty     Medium
 * @Tags           linked list, sorting
 * @Link           https://leetcode.com/problems/insertion-sort-list/

Insertion Sort List

    Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.


    The steps of the insertion sort algorithm:

    Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.

    At each iteration, insertion sort removes one element from the input data, finds the location it belongs within
    the sorted list and inserts it there.

    It repeats until no input elements remain.
    The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially
    contains only the first element in the list. One element (red) is removed from the input data and inserted in-place
    into the sorted list with each iteration.


Example 1:

    Input: head = [4,2,1,3]
    Output: [1,2,3,4]

Example 2:

    Input: head = [-1,5,3,4,0]
    Output: [-1,0,3,4,5]


Constraints:

    The number of nodes in the list is the range [1, 5000].
    -5000 <= Node.val <= 5000

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

    public int size() {
        int i = 0;
        ListNode node = this;
        do {
            ++i;
        } while ((node = node.next) != null);
        return i;
    }

    public void dump(/* Num of nodes to print of negative to print all nodes*/ int num) {
        ListNode node = this;
        System.out.print("[");
        while (node != null && num != 0) {
            System.out.print(node.val);
            node = node.next;
            if (node != null)
                System.out.print(" ");
            if (num > 0)
                num--;
        }
        System.out.print("]");
    }

    public void dumpln(int num) {
        this.dump(num);
        System.out.println();
    }
}

class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode beg = head; // Beginning of sorted sub-array.
        ListNode end = head; // End of sorted sub-array.
        ListNode analyzable = head.next; // Current analyzable node. Ritht after the end of the sorted sub-array before being moved.

        while(analyzable != null) {
            ListNode curr = beg;
            ListNode postanalyzable = analyzable.next; // Keep the next to the analyzable node.

            do {
                System.out.printf(" curr=%d analyze=%d... ", curr.val, analyzable.val);
                // Leave at end
                if (curr == end && analyzable.val >= curr.val) {
                    System.out.println(" to END");
                    curr.next = analyzable;
                    end = analyzable;
                    break;
                }
                // Insert to beginning
                else if (curr == beg && analyzable.val <= curr.val) {
                    System.out.println(" to BEG");
                    analyzable.next = curr;
                    beg = analyzable;
                    end.next = postanalyzable;
                    break;
                }
                // Insert to middle
                else if (analyzable.val > curr.val && analyzable.val <= curr.next.val) {
                    System.out.println(" after " + curr.val);
                    ListNode postcurr = curr.next;
                    curr.next = analyzable;
                    analyzable.next = postcurr;
                    break;
                }
                else {
                    System.out.println(" continue");
                    curr = curr.next;
                    continue;
                }

            } while (curr != end.next);
            
            end.next = null;
            analyzable = postanalyzable;
        }
        beg.dumpln(-1);
        return beg;
    }

    public static void main(String[] args) {
        int [][][] data = {
                {{4,2,1,3}, {1,2,3,4}},
                {{-1,5,3,4,0}, {-1,0,3,4,5}},
                {{1,1}, {1,1}},
                {{3,2,4}, {2,3,4}},
                {{4,19,14,5,-3,1,8,5,11,15}, {-3,1,4,5,5,8,11,14,15,19}},
        };
        
        Solution solution = new Solution();
        for (int[][] d : data) {
            int[] input = d[0];
            int[] expect = d[1];
            ListNode list = new ListNode(input);
            list.dumpln(-1);

            solution.insertionSortList(list);
            System.out.println(" --- ");
        }
    }
}
