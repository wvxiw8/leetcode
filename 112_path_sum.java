import java.util.List;
import java.util.ArrayList;

/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Path Sum
 * @id             112
 * @Difficulty     Easy
 * @Tags           tree, binary tree
 * @Featured       Learn
 * @Link           https://leetcode.com/explore/learn/card/data-structure-tree/17/solve-problems-recursively/537/
 
Path Sum

    Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

Example 1:

            [5]
       [4]       8
    [11]      13    4
  7     [2]            1

 
    Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
    Output: true
    Explanation: The root-to-leaf path with the target sum is shown.


Example 2:

    1
  2   3
    Input: root = [1,2,3], targetSum = 5
    Output: false
    Explanation: There two root-to-leaf paths in the tree:
    (1 --> 2): The sum is 3.
    (1 --> 3): The sum is 4.
    There is no root-to-leaf path with sum = 5.

 
Example 3:

    Input: root = [], targetSum = 0
    Output: false
    Explanation: Since the tree is empty, there are no root-to-leaf paths.

Constraints:

    The number of nodes in the tree is in the range [0, 5000].
    -1000 <= Node.val <= 1000
    -1000 <= targetSum <= 1000

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

    private static final int PSEUDO_NULL = Integer.MAX_VALUE;

    private boolean processNode(TreeNode node, int currSum, int targetSum) {
        if ((node.val + currSum == targetSum) && (node.left == null && node.right == null)) {
            return true;
        }
        return levelOrderTraversalSum(node.left, node.right, node.val + currSum, targetSum); 
    }

    private boolean levelOrderTraversalSum(TreeNode l, TreeNode r, int currSum, int targetSum) {
        // System.out.printf("l=%s r=%s\n", l != null ? l.val : "null" , r != null ? r.val : "null");
        if (l != null)
             if (processNode(l, currSum, targetSum)) 
                return true;
        if (r != null)
            return processNode(r, currSum, targetSum);
        return false;
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        return levelOrderTraversalSum(root, root, 0, targetSum);
    }


    static private TreeNode constructTree(int[] nodes /* by level */) {
        if (nodes.length == 0) {
            return null;
        } 

        TreeNode root = new TreeNode(nodes[0], null, null);
        if (nodes.length == 1) {
            return root;
        }  

        List<TreeNode> curr; /* Current row */
        List<TreeNode> prev = new ArrayList<TreeNode>(); /* Previous row */
        prev.add(root);

        int numPrev = 1; /* Not nulls elements in previous row */
        int numCurr = 0; /* Not nulls elements in current row */

        int i; /* Index in input array */
        for (i = 1; i < nodes.length; ) {
            curr = new ArrayList<TreeNode>();
            numCurr = 0; 

            int p; /* Iterator for previous row */
            int c; /* Iterator for current row*/
            for (p = c = 0; c < numPrev*2; ++c, ++p) {
                TreeNode parent;

                while ((parent = prev.get(p/2)) == null) {
                    ++p;
                    if ((i + c) > nodes.length) {
                        System.out.println("Bad initialization array");
                        return null;
                    }
                }
              
                int val = nodes[i++];
                if (val == PSEUDO_NULL) {
                    curr.add(null);
                } else {
                    curr.add(new TreeNode(val, null, null));
                    ++numCurr;
                }

                /* Add children to parent */
                if ((c&1) ==  1) {
                    parent.left = curr.get(c-1);
                    parent.right = curr.get(c);
                }
            }
            
            prev = curr;
            numPrev = numCurr;
        }
        return root;
    }

/*
              [5]
         [4]        8
    [11]    N    13    4
   7   [2] N N  N N   N 1   */
    static void Test1() {

        /* Init way 1 */
        /* 
        TreeNode n14 = new TreeNode(1, null, null);
        TreeNode n8 = new TreeNode(2, null, null);
        TreeNode n7 = new TreeNode(7, null, null);
        TreeNode n6 = new TreeNode(4, null, n14);
        TreeNode n5 = new TreeNode(13, null, null);
        TreeNode n3 = new TreeNode(11, n7, n8);
        TreeNode n2 = new TreeNode(8, n5, n6);
        TreeNode n1 = new TreeNode(4, n3, null);
        TreeNode n0 = new TreeNode(5, n1, n2);
        TreeNode root = n0; */
        
        /* Init way 2 */
        int[] nodes = {5, 4,8, 11,PSEUDO_NULL,13,4, 7,2,PSEUDO_NULL,PSEUDO_NULL,PSEUDO_NULL,1};
        TreeNode root = constructTree(nodes);


        Solution solution = new Solution();
        boolean res = solution.hasPathSum(root, 22);
        System.out.println(res);
    }


    public static void main(String[] args) {     
        Test1();
    }
}
