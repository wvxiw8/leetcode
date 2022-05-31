import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Binary Tree Postorder Traversal (LRN)
 * @id             145
 * @Difficulty     Easy
 * @Tags           tree, binary tree
 * @Featured       Learn
 * @Link           https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/930/
 
Binary Tree Postorder Traversal

    Given the root of a binary tree, return the postorder traversal of its nodes' values.

   1
  / \
    2
   /
  3
Example 1:

    Input: root = [1,null,2,3]
    Output: [1,2,3]

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
 

enum State {
    TO_LEFT,
    TO_RIGHT,
    FROM_LEFT,
    FROM_RIGHT
}


/**
 * Postorder(LRN) traversal. Iterative implementation.
 */
class Solution {
    Stack <TreeNode> ancestors = new Stack<TreeNode>();
    State state = State.TO_LEFT;

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        ancestors.push(null); /* Avoid messing around with EmptyStackException */
        TreeNode n = root; /* Current node */

        if (root == null)
            return res;
        
        do {
            // TreeNode temp = ancestors.pop(); System.out.printf("cur=%d anc=%d state=%s \n", n.val, temp == null ? 0 : temp.val, state.toString()); ancestors.push(temp);
            if (state == State.TO_LEFT || state == State.TO_RIGHT) {
                if (n.left != null) {
                    ancestors.push(n);
                    n = n.left;
                    state = State.TO_LEFT;
                } else if (n.right != null) {
                    ancestors.push(n);
                    n = n.right;
                    state = State.TO_RIGHT;
                } else {
                    res.add(n.val);
                    n = ancestors.pop();
                    state = state == State.TO_LEFT ? State.FROM_LEFT: State.FROM_RIGHT;
                }
            } else /* FROM_LEFT || FROM_RIGHT */ {
                if (state == State.FROM_LEFT && n.right != null) {
                    ancestors.push(n);
                    n = n.right;
                    state = State.TO_RIGHT;
                } else {
                    res.add(n.val);
                    TreeNode ancestor = ancestors.pop();
                    if (ancestor == null) 
                        break;
                    state = ancestor.left == n ? State.FROM_LEFT: State.FROM_RIGHT;
                    n = ancestor;
                }
            }
  
        } while (n != null);
        
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
   1
    2
  3   4
    5   6
       7    */
    static void Test1() {
        TreeNode n7 = new TreeNode(7, null, null);
        TreeNode n6 = new TreeNode(6, n7, null);
        TreeNode n5 = new TreeNode(5, null, null);
        TreeNode n4 = new TreeNode(4, n5, n6);
        TreeNode n3 = new TreeNode(3, null, null);
        TreeNode n2 = new TreeNode(2, n3, n4);
        TreeNode n1 = new TreeNode(1, null, n2); // root
        List<Integer> expected = new ArrayList<>(Arrays.asList(3,5,7,6,4,2,1));
        Solution solution = new Solution();
        List<Integer> res = solution.postorderTraversal(n1);
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
        List<Integer> res = solution.postorderTraversal(n1);
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
        List<Integer> res = solution.postorderTraversal(n1);
        checkResult(res, Arrays.asList(3,2,1));
    }

/*
   1
 2      */
   static void Test4() {
        TreeNode n2 = new TreeNode(2, null, null);
        TreeNode n1 = new TreeNode(1, n2, null); // root
        Solution solution = new Solution();
        List<Integer> res = solution.postorderTraversal(n1);
        checkResult(res, Arrays.asList(2,1));
    }

    public static void main(String[] args) {
        Test1();
        Test2();
        Test3();
        Test4();
    }
}
