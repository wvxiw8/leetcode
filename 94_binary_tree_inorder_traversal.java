/**
 * @formatter:off
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Binary Tree Inorder Traversal (LNR)
 * @id             94
 * @Difficulty     Easy
 * @Tags           tree, binary tree
 * @Featured       Learn
 * @Link           https://leetcode.com/problems/binary-tree-inorder-traversal/
 * @Link           https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/929/
 
Binary Tree Inorder Traversal
    Given the root of a binary tree, return the inorder traversal of its nodes' values.
   1
  / \
    2
   /
  3

Example 1:
    Input: root = [1,null,2,3]
    Output: [1,3,2]

Example 2:
    Input: root = []
    Output: []

Example 3:
    Input: root = [1]
    Output: [1]

Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

Follow up: Recursive solution is trivial, could you do it iteratively?
 @formatter:on
 */


import com.wvxiw.leetcode.datastructures.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


class Solution {
    private void traverseLnr(TreeNode n, List<Integer> output) {
        if (n == null)
            return;
        traverseLnr(n.left, output);
        output.add(n.val);
        traverseLnr(n.right, output);
    }

    // Using recursion
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traverseLnr(root, list);
        return list;
    }

    // Without recursion
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        stack.push(curr);
        curr = curr.left;

        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode n = stack.pop();
            list.add(n.val);
            curr = n.right;
        }
        return list;
    }


    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3, null, null);
        TreeNode n2 = new TreeNode(2, n3, null);
        TreeNode n1 = new TreeNode(1, null, n2);

        List<Integer> exp = List.of(1, 3, 2);
//        List<Integer> result = new Solution().inorderTraversal(n1);
        List<Integer> result = new Solution().inorderTraversal2(n1);
        if (result.equals(exp))
            System.out.println("OK");
        else
            System.out.println("Fail");
    }
}
