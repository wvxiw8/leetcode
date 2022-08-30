/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Delete Node in a Linked List
 * @id             237
 * @Difficulty     Easy
 * @Tags           linked list
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/553/

Delete Node in a Linked List

    There is a singly-linked list head and we want to delete a node node in it.

    You are given the node to be deleted node. You will not be given access to the first node of head.

    All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.

    Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:

        The value of the given node should not exist in the linked list.
        The number of nodes in the linked list should decrease by one.
        All the values before node should be in the same order.
        All the values after node should be in the same order.


Example 1:

    Input: head = [4,5,1,9], node = 5
    Output: [4,1,9]
    Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.


Example 2:

    Input: head = [4,5,1,9], node = 1
    Output: [4,5,9]
    Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.


Constraints:

    The number of the nodes in the given list is in the range [2, 1000].
    -1000 <= Node.val <= 1000
    The value of each node in the list is unique.
    The node to be deleted is in the list and is not a tail node
*/

#include <stdio.h>
#include <stdlib.h>


/** Definition for singly-linked list. */
 struct ListNode {
    int val;
    struct ListNode *next;
};

void deleteNode(struct ListNode* node) {
    while (node->next) {
        node->val = node->next->val;
        if (!node->next->next)
            node->next = NULL;
        else 
            node = node->next;
    }
}

struct ListNode* createList(int* data, int size) {
    struct ListNode* curr = NULL;
    struct ListNode* next = NULL;
    while (size) {
        curr = malloc(sizeof(struct ListNode));
        curr->val = data[size-1];
        curr->next = next;
        next = curr;
        --size;
    }
    return curr;
}

void printList(struct ListNode* head) {
    while(head) {
        printf("%d  ", head->val);
        head = head->next;
    }
    printf("\n");
}


int main() {
    int data [] = {4,5,1,9}; 
    struct ListNode* head = createList(data, sizeof(data)/sizeof(data[0]));
    printList(head);
    deleteNode(head->next);
    printList(head);
}
