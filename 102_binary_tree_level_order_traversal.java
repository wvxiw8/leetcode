import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Binary Tree Level Order Traversal
 * @id             102
 * @Difficulty     Medium
 * @Tags           tree, binary tree
 * @Featured       Learn
 * @Link           https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/931/
 
Binary Tree Level Order Traversal

    Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).


Example 1:
   3
  / \
9   20
   /  \
  15  7

    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]

Example 2:

    Input: root = [1]
    Output: [[1]]

Example 3:

    Input: root = []
    Output: []

 

Constraints:

    The number of nodes in the tree is in the range [0, 2000].
    -1000 <= Node.val <= 1000

 */




/**
 * Definition for a binary tree node. 
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
 }
 

/**
 *  Level Order traversal.
 */
class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) 
            return res;
        
        List<TreeNode> cur = new ArrayList<>(Arrays.asList(root));
        List<TreeNode> next;
      
        do {
            List<Integer> integers = new ArrayList<>();
            next = new ArrayList<>();
            for (TreeNode node : cur) {
                integers.add(node.val);

                if(node.left != null)
                    next.add(node.left);
                if(node.right != null)
                    next.add(node.right);
            }
            res.add(integers);

            cur = next;
        } while (next.size() != 0);

        return res;
    } 

/*
  3
9   20
  15  7   */
    static void Test() {
        TreeNode n15 = new TreeNode(15, null, null);
        TreeNode n7 = new TreeNode(7, null, null);
        TreeNode n9 = new TreeNode(9, null, null);
        TreeNode n20 = new TreeNode(20, n15, n7);
        TreeNode n3 = new TreeNode(3, n9, n20); // root
        Solution solution = new Solution();
        List<List<Integer>> res = solution.levelOrder(n3);
        System.out.println(" Result: " + res); 
    }

    public static void main(String[] args) {
        Test();
    }
}
