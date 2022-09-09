/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Convert Sorted Array to Binary Search Tree
 * @id             108
 * @Difficulty     Easy
 * @Tags           tree, binary tree, array, binary search tree, divide and conquer
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/631/
 
Convert Sorted Array to Binary Search Tree
    
    Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

    A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.


Example 1:

         0
        / \
      -3   9
      /    /
    -10   5
    Input: nums = [-10,-3,0,5,9]
    Output: [0,-3,9,-10,null,5]
    Explanation: [0,-10,5,null,-3,null,9] is also accepted:
         0
        / \
      -3   9
       \    \
       -10   5


Example 2:

    3       1
   /         \
 1            3
    Input: nums = [1,3]
    Output: [3,1]
    Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.


Constraints:

    1 <= nums.length <= 10^4
    -10^4 <= nums[i] <= 10^4
    nums is sorted in a strictly increasing order. */

#include <stdio.h>
#include <stdlib.h> /* malloc */
#include <stddef.h> /* NULL */


/* Definition for a binary tree node. */
struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

struct TreeNode* sortedArrayToBST(int* nums, int numsSize) {
    if (numsSize < 1) 
        return NULL;

    struct TreeNode* node = malloc(sizeof(struct TreeNode));
    int i = numsSize/2;
    node->val = nums[i];
    node->left = sortedArrayToBST(nums, i);
    node->right = sortedArrayToBST(&nums[numsSize/2 + 1], numsSize-i-1);
    return node;
}

int main() {
    int a[] = {-10,-3,0,5,9};  int exp[] = {0,-3,9,-10,0,5};
    // int a[] = {-10,-3,-1,0,1,5,9};  int exp[] = {0,-1,1,-10,-3,5,9};
    sortedArrayToBST(a, sizeof(a)/sizeof(a[0]));
    return 0;
}
