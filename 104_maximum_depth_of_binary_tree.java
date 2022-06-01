import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Maximum Depth of Binary Tree
 * @id             104
 * @Difficulty     Easy
 * @Tags           tree, binary tree
 * @Featured       Learn
 * @Link           https://leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/535/
 
Maximum Depth of Binary Tree

    Given the root of a binary tree, return its maximum depth.

    A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:
   3
  / \
9   20
   /  \
  15  7

    Input: root = [3,9,20,null,null,15,7]
    Output: 3

Example 2:

    Input: root = [1,null,2]
    Output: 2

Constraints:

    The number of nodes in the tree is in the range [0, 10^4].
    -100 <= Node.val <= 100
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
 

class Solution {

    public int maxDepth(TreeNode root) {
        int depth = 0;

        if (root == null) 
            return depth;
        
        List<TreeNode> cur = new ArrayList<>(Arrays.asList(root));
        List<TreeNode> next;
      
        do {
            next = new ArrayList<>();
            for (TreeNode node : cur) {
                if(node.left != null)
                    next.add(node.left);
                if(node.right != null)
                    next.add(node.right);
            }
            cur = next;
            ++depth;
        } while (next.size() != 0);

        return depth;
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