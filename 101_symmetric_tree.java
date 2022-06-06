import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Symmetric Tree
 * @id             101
 * @Difficulty     Easy
 * @Tags           tree, binary tree
 * @Featured       Learn
 * @Link           https://leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/536/
 
Symmetric Tree

    Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 

Example 1:

    1
  2   2
 3 4 4 3
    Input: root = [1,2,2,3,4,4,3]
    Output: true

Example 2:

    1
  2   2
   3   3
    Input: root = [1,2,2,null,3,null,3]
    Output: false

 

Constraints:

    The number of nodes in the tree is in the range [1, 1000].
    -100 <= Node.val <= 100

 
Follow up: Could you solve it both recursively and iteratively?

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

    /* #1: my solution */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) 
            return false;

        final int PSEUDO_NULL = Integer.MAX_VALUE;
        TreeNode dummyNode = new TreeNode(PSEUDO_NULL, null, null);
        List<TreeNode> curr = new ArrayList<>(Arrays.asList(root));
        List<TreeNode> next;
        
        int dummies;
        do {
            next = new ArrayList<>();
            dummies = 0;

            for (TreeNode node : curr) {
                if (node.val == PSEUDO_NULL)
                    continue;

                if (node.left != null)
                    next.add(node.left);
                else {
                    next.add(dummyNode);
                    ++dummies;
                }

                if (node.right != null)
                    next.add(node.right);
                else {
                    next.add(dummyNode);
                    ++dummies;
                }
            }

            int beg = 0;
            int end = curr.size() - 1;
            int lim = curr.size() >> 1;
            for (int i = 0; i < lim; ++i) {
                if (curr.get(beg++).val != curr.get(end--).val)
                    return false;
            }
            
            curr = next;
        } while (dummies != next.size());

        return true;
    }
    
    
    /* #2: peeked solution */
    /*     
    private boolean peekedSolution(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null)
            return true;
        
        if (n1 != null && n2 != null && n1.val == n2.val)
            return traverse(n1.left, n2.right) && traverse(n2.left, n1.right);
        return false;
    }
    public boolean isSymmetric(TreeNode root) {
        return peekedSolution (root, root); 
    } 
    */


/*
    1
  2   2
 3 4 4 3   */
static void Test1() {
    TreeNode n7 = new TreeNode(3, null, null);
    TreeNode n6 = new TreeNode(4, null, null);
    TreeNode n5 = new TreeNode(4, null, null);
    TreeNode n4 = new TreeNode(3, null, null);
    TreeNode n3 = new TreeNode(2, n6, n7);
    TreeNode n2 = new TreeNode(2, n4, n5);
    TreeNode n1 = new TreeNode(1, n2, n3); // root
    Solution solution = new Solution();
    boolean res = solution.isSymmetric(n1);
    System.out.println(res);
    }


/*
         1
     2          2
 null 3     null 3   */
 static void Test2() {
    TreeNode n7 = new TreeNode(3, null, null);
    TreeNode n5 = new TreeNode(3, null, null);
    TreeNode n3 = new TreeNode(2, null, n7);
    TreeNode n2 = new TreeNode(2, null, n5);
    TreeNode n1 = new TreeNode(1, n2, n3); // root
    Solution solution = new Solution();
    boolean res = solution.isSymmetric(n1);
    System.out.println(res);
    }


/*
         1
     2        2
 null  3    3   null */
 static void Test3() {
    TreeNode n6 = new TreeNode(3, null, null);
    TreeNode n5 = new TreeNode(3, null, null);
    TreeNode n3 = new TreeNode(2, n6, null);
    TreeNode n2 = new TreeNode(2, null, n5);
    TreeNode n1 = new TreeNode(1, n2, n3); // root
    Solution solution = new Solution();
    boolean res = solution.isSymmetric(n1);
    System.out.println(res);
    }


/*
               9
       -42          -42
    null  76      76    null
      null 13   null 13   */
 static void Test4() {
    TreeNode n13 = new TreeNode(13, null, null);
    TreeNode n11 = new TreeNode(13, null, null);
    TreeNode n6 = new TreeNode(76, null, n13);
    TreeNode n5 = new TreeNode(76, null, n11);
    TreeNode n3 = new TreeNode(-42, n6, null);
    TreeNode n2 = new TreeNode(-42, null, n5);
    TreeNode n1 = new TreeNode(9, n2, n3); // root
    Solution solution = new Solution();
    boolean res = solution.isSymmetric(n1);
    System.out.println(res);
    }

    public static void main(String[] args) {
        Test1(); // true 
        Test2(); // false
        Test3(); // true
        Test4(); // false
    }
}
