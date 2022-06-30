import java.util.List;
import java.util.ArrayList;

/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Serialize and Deserialize Binary Tree
 * @id             297
 * @Difficulty     Hard
 * @Tags           tree, binary tree
 * @Featured       Learn
 * @Link           https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/995/

Serialize and Deserialize Binary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.


Example 1:

       1
   2       3
        4     5

Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]

Example 2:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 10^4].
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
    TreeNode(int x) { val = x; }
}


/**
 *  Serialize and Deserialize Binary Tree.
 */
class Codec {

    private String levelOrderTraversal(List<TreeNode> currLevel, StringBuilder sb) {
        final String NULL_STRING = "null,";
        List<TreeNode> nextLevel;

        do {
            nextLevel = new ArrayList<>();

            for (TreeNode node : currLevel) {
                if (node != null) {
                    sb.append(node.val + ",");
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                }
                else
                    sb.append("null,");
            }
            currLevel = nextLevel;
        } while (nextLevel.size() != 0);


        /* Trim all "null," at the end of the string */
        String s = sb.toString();
        int i = s.length();
        while (i > NULL_STRING.length()) {
            if (s.substring(i - NULL_STRING.length(), i).equals(NULL_STRING))
                i -= NULL_STRING.length();
            else
                break;
        }
        return s.substring(0, i - 1/* comma */);

    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        List<TreeNode> rootLevel = new ArrayList<>();
        rootLevel.add(root);
        return levelOrderTraversal(rootLevel, sb);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty())
            return null;

        final int PSEUDO_NULL = Integer.MAX_VALUE;
        String[] strings = data.split(",");
        int[] input = new int[strings.length];
        for (int i = 0; i < strings.length; ++i) {
            String s = strings[i];
            s = s.trim(); /* Not neeed actually. For debug only */
            if ("null".equals(s))
                input[i] = PSEUDO_NULL;
            else
                input[i] = Integer.parseInt(s); /* catch NumberFormatException might require */
        }

        if (input.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(input[0]);
        if (input.length == 1) {
            return root;
        }

        List<TreeNode> curr; /* Current row */
        List<TreeNode> prev = new ArrayList<TreeNode>(); /* Previous row */
        prev.add(root);

        int numPrev = 1; /* Not nulls elements in previous row */
        int numCurr = 0; /* Not nulls elements in current row */
        int inputIndex = numPrev; /* Index in input array */
        int inputLeft = input.length - 1;

        while (inputLeft > 0) {
            /* Prepare the input data - get by a block of (numPrev*2) from the input array */
            int inputBlockSize = numPrev * 2; /* Amount of both good numbers and nulls */
            int [] currInputBlock = new int[inputBlockSize];
            int numAvailable = Integer.min(inputLeft, inputBlockSize);
            System.arraycopy(input, inputIndex, currInputBlock,0, numAvailable);
            int numToAppend = inputBlockSize - numAvailable; /* The last block doesn't have its last null items */
            if (numToAppend != 0) {
                for (int j = 0; j < numToAppend; ++j)
                    currInputBlock[numAvailable + j] = PSEUDO_NULL;
            }
            inputLeft -= numAvailable;
            inputIndex += numAvailable;


            /* Create a list of current not-null items */
            curr = new ArrayList<TreeNode>();
            numCurr = 0;
            int c = 0; /* Iterator for current output row */
            int p = 0; /* Iterator for previous output row */

            for (int j = 0; j < currInputBlock.length ; ++j, ++c, ++p) {
                if (currInputBlock[j] == PSEUDO_NULL) {
                    curr.add(null);
                } else {
                    curr.add(new TreeNode(currInputBlock[j]));
                    ++numCurr;
                }

                /* Add children to parent */
                TreeNode parent;
                while ((parent = prev.get(p/2)) == null) {
                    ++p;
                }
                if ((c & 1) ==  1) {
                    parent.left = curr.get(c - 1);
                    parent.right = curr.get(c);
                }
            }
            prev = curr;
            numPrev = numCurr;
        }
        return root;
    }


    static void test(String testSting) {
        Codec ser = new Codec();
        Codec deser = new Codec();
        System.out.println("Input: " + testSting);
        TreeNode deserialized = deser.deserialize(testSting);
        String serialized = ser.serialize(deserialized);
        System.out.println("Ser/deser: " + serialized);
        if (serialized.equals(testSting.replaceAll("\\s",""))) {
            System.out.println("OK\n");
        } else {
            System.out.println("Fail\n");
        }
    }


    public static void main(String[] args) {
        test( "1,2,3,null,null,4,5");
        test( "");

        /*
                       4
            -7                   -3
                         -9              -3
                     9      -7        -4
                 6     x   6   -6
               0    6    5  x  9  x
              x-1 -4 x  x x  -2 x
        * */
        test("4,  -7,-3,   null,null,-9,-3,   9,-7,-4,null,   6,null,-6,-6,null,null,   0,6,5,null,9,null,  null,-1,-4,null,null,null,-2");  /*  Pay attention: the last value (-2) doesn't have a pair */
        test( "1");



        /* I checked a few other solutions at leetcode - the fastest ones use DFS (Depth First Search).
           That's what I had to implement here */
    }
}
