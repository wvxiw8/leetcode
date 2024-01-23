/**
 * @formatter:off
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           ---
 * @id             ---
 * @Difficulty     Easy Medium Hard
 * @Access         Premium
 * @Tags           array, ---, linked list, sorting
 * @Featured       --- Top interview collection ---
 * @Link           https://leetcode.com/problems/---/

--- Name --

    desctiption


Example 1:

    Input: ---
    Output: ---

Example 2:

    Input: ---
    Output: ---


Constraints:

    ---


Follow up: ---

 * @formatter:on
*/


import java.util.Arrays;
import java.util.HashMap;

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
    public int[] method(int[] nums, int k) {
        return new int[] {0,1};
        }


    public static void main(String[] args) {
        /* {{nums}, {k}, {output/expected}} */
        int [][][] data = {
                {{1,1,1,2,2,3}, {2}, {1,2}},
                {{1}, {1}, {1}},
        };

        Solution solution = new Solution();
        for (int[][] d : data) {
            int[] nums = d[0];
            int k = d[1][0];
            int[] exp = d[2];
            int[] result = solution.topKFrequent(nums, k);
            System.out.print("k=" + k + " nums=" + Arrays.toString(nums));

            if (Arrays.equals(exp, result)) {
                System.out.println("   OK");
            } else {
                System.out.println("   Failed ");
                System.out.println(" expected=" + Arrays.toString(exp));
                System.out.println("   result=" + Arrays.toString(result));
            }
        }
    }
}
