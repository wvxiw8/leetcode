import java.util.List;
import java.util.Stack;
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

            cur = next;
        } while (next.size() != 0);

        return res;
    } 



    static void checkResult(List<Integer> result, List<Integer> expected) {
        if (result.equals(expected)) {
            System.out.println("OK");
        } else {
            System.out.println("Fail!");
            System.out.println(" Result:   " + result); 
            System.out.println(" Expected: " + expected); 
        }

    }

/*
  3
9   20
  15  7   */
    static void Test1() {
        TreeNode n5 = new TreeNode(5, null, null);
        TreeNode n4 = new TreeNode(4, n5, n6);
        TreeNode n3 = new TreeNode(3, null, null);
        TreeNode n2 = new TreeNode(2, n3, n4);
        TreeNode n1 = new TreeNode(n3, null, n2); // root
        List<Integer> expected = new ArrayList<>(Arrays.asList(3,5,7,6,4,2,1));
        Solution solution = new Solution();
        List<Integer> res = solution.levelOrder(n1);
        checkResult(res, expected);
    }

/*
   1
 4   3
2         */
    static void Test2() {
        TreeNode n2 = new TreeNode(2, null, null);
        TreeNode n4 = new TreeNode(4, n2, null);
        TreeNode n3 = new TreeNode(3, null, null);
        TreeNode n1 = new TreeNode(1, n4, n3); // root
        Solution solution = new Solution();
        List<Integer> res = solution.levelOrder(n1);
        checkResult(res, Arrays.asList(2,4,3,1));
    }

/*
   1
     2
   3      */
   static void Test3() {
        TreeNode n3 = new TreeNode(3, null, null);
        TreeNode n2 = new TreeNode(2, n3, null);
        TreeNode n1 = new TreeNode(1, null, n2); // root
        Solution solution = new Solution();
        List<Integer> res = solution.levelOrder(n1);
        checkResult(res, Arrays.asList(3,2,1));
    }

/*
   1
 2      */
   static void Test4() {
        TreeNode n2 = new TreeNode(2, null, null);
        TreeNode n1 = new TreeNode(1, n2, null); // root
        Solution solution = new Solution();
        List<Integer> res = solution.levelOrder(n1);
        checkResult(res, Arrays.asList(2,1));
    }

    public static void main(String[] args) {
        Test1();
        Test2();
        Test3();
        Test4();
    }
}
