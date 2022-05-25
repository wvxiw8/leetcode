/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Binary Tree Preorder Traversal (NLR)
 * @id             144
 * @Difficulty     Easy
 * @Tags           tree, binary tree
 * @Featured       Learn
 * @Link           https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/928/
 
Rotate Array

    Given the root of a binary tree, return the preorder traversal of its nodes' values.

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

#include <stdio.h>
#include <string.h>
#include <stddef.h>
#include <stdlib.h> /* for realloc */



/* Definition for a binary tree node. */
struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};


/* This can be united in struct treeTraverser */
int* result = NULL;
int bufSize = 1; /* size of allocated <result> buffer (in ints) */
int resSize = 0; /* number of node values put to <result> */

void traverseNode(struct TreeNode* node) {
    if (!node) 
        return; 
        
    if (bufSize  <= (resSize + 1)) {
        bufSize <<= 1;
        result = realloc(result, bufSize * sizeof(int)); /* assume always ok */
    }
    result[resSize++] = node->val;
    traverseNode(node->left);
    traverseNode(node->right);
}
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* preorderTraversal(struct TreeNode* root, int* returnSize) {
    traverseNode(root);
    *returnSize = resSize;
    return result;
}


int main() {
    int returnSize;
    int* result;

#define TEST4
#if defined TEST1
/*
   1
    2
  3   4
    5   6
       7 
*/
    struct TreeNode n7 = {.val = 7, .left = NULL, .right = NULL};
    struct TreeNode n6 = {.val = 6, .left = &n7, .right = NULL};
    struct TreeNode n5 = {.val = 5, .left = NULL, .right = NULL};
    struct TreeNode n4 = {.val = 4, .left = &n5, .right = &n6};
    struct TreeNode n3 = {.val = 3, .left = NULL, .right = NULL};
    struct TreeNode n2 = {.val = 2, .left = &n3, .right = &n4};
    struct TreeNode root = {.val = 1, .left = NULL, .right = &n2};
    // int exp[] = {1,0,2,3};
#elif defined TEST2
    struct TreeNode root = {.val = 0, .left = NULL, .right = NULL};
    result = preorderTraversal(NULL, &returnSize);
    for (int i = 0; i < returnSize; i++) { printf("%d ", *result++); } printf("\n");
    return 0;
#elif defined TEST3
/*
   1
    2
  3
*/
    struct TreeNode n3 = {.val = 3, .left = NULL, .right = NULL};
    struct TreeNode n2 = {.val = 2, .left = &n3, .right = NULL};
    struct TreeNode root = {.val = 1, .left = NULL, .right = &n2};
#elif defined TEST4
/*
   1
*/
    struct TreeNode root = {.val = 1, .left = NULL, .right = NULL};
#endif




    result = preorderTraversal(&root, &returnSize);
    printf("[returnSize]=%d\n", returnSize); for (int i = 0; i < returnSize; i++) { printf("%d ", *result++); } printf("\n");
    
    // if (result) free(result); //* Note that get munmap_chunk(): invalid pointer Aborted (core dumped) here" */

    return 0;
}