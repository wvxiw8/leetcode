/**
 * @Author wvxiw
 * @Title TreeNode data structure definition for leetcode
 */
package com.wvxiw.leetcode.datastructures;

// According to leetcode tasks
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}