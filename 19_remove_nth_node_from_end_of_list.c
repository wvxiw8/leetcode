/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Remove Nth Node From End of List
 * @id             19
 * @Difficulty     Medium
 * @Tags           linked list
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/603/

Remove Nth Node From End of List

   Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:

    Input: head = [1,2,3,4,5], n = 2
    Output: [1,2,3,5]

Example 2:

    Input: head = [1], n = 1
    Output: []

Example 3:

    Input: head = [1,2], n = 1
    Output: [1]


Constraints:

    The number of nodes in the list is sz.
    1 <= sz <= 30
    0 <= Node.val <= 100
    1 <= n <= sz


Follow up: Could you do this in one pass?
*/

#include <stdio.h>
#include <stdlib.h>

/** Definition for singly-linked list. */
 struct ListNode {
    int val;
    struct ListNode *next;
};

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
    if(!head)
        printf("Empty\n");

    while(head) {
        printf("%d  ", head->val);
        head = head->next;
    }
    printf("\n");
}

void deleteNode(struct ListNode* node) {
    while (node->next) {
        node->val = node->next->val;
        if (!node->next->next)
            node->next = NULL;
        else 
            node = node->next;
    }
}

struct ListNode* removeNthFromEnd(struct ListNode* head, int n) {
    int k = n + 1;
    struct ListNode* a[k];
    struct ListNode* node = head;
    int i = 0;

    printf("n=%d k=%d \n", n, k);
    while (node) {
        printf("%p (%d) save to i=%d\n", node, node->val, i%k);
        a[i%k] = node;
        ++i;
        node = node->next;
    }

    if (i == n) {
        struct ListNode* ret = head->next;
        free(head);
        return ret;
    }

    struct ListNode* prev = a[i%k];
    node = prev->next;
    printf("Deleting stored in a[%d] the %p (%d)\n", i%k, node, node->val);
    prev->next = node->next;
    free(node);
    return head;
}

int main() {
    int data [] = {1,2,3,4,5}; int n = 2;
    // int data [] = {1}; int n = 1;
    // int data [] = {1,2}; int n = 2;
    struct ListNode* head = createList(data, sizeof(data)/sizeof(data[0]));
    printList(head);
    head = removeNthFromEnd(head, n);
    printList(head);
}
