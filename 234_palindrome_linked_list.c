/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Palindrome Linked List
 * @id             234
 * @Difficulty     Easy
 * @Tags           linked list, two pointers, stack, recursion
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/93/linked-list/772/

Palindrome Linked List

    Given the head of a singly linked list, return true if it is a palindrome or false otherwise.


Example 1:

    Input: head = [1,2,2,1]
    Output: true


Example 2:

    Input: head = [1,2]
    Output: false


Constraints:

    The number of nodes in the list is in the range [1, 10^5].
    0 <= Node.val <= 9


Follow up: Could you do it in O(n) time and O(1) space?

*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

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

#if 0
/* Naive solution */
bool isPalindrome(struct ListNode* head) {
    char a[100000];
    char* e = a;
    struct ListNode* node = head;

    while (node) {
        *e++ = node->val;
        node = node->next;
    }

    --e;
    char* b = a;
    while (e > b) {
        if (*e-- != *b++)
            return 0;
    }
    return 1;
}
#else 
struct ListNode* reverseList(struct ListNode* head) {
    struct ListNode* prev = NULL; /* prev - the previous in the current list, the next in the furure list */
    struct ListNode* curr = head;
    while (curr) {
        struct ListNode* tmp = curr->next; 
        curr->next = prev;
        prev = curr;
        curr = tmp;
    }
    return prev;
}

/* Solution with using 2 pointers */
bool isPalindrome(struct ListNode* head) {

    /* Use two pointers to find the middle/middle+1 and also calculate the size of the list*/
    struct ListNode* fast = head;
    struct ListNode* slow = head;
    int size = 0;
    while (fast && fast->next) {
        fast = fast->next->next;
        slow = slow->next;
        size += 2;
    }
    if (fast)
        ++size;

    /* Reverse a higher half of the list */
    struct ListNode* half = reverseList(slow);

    /* Compare */
    int lim = size / 2;
    for (int i = 0; i < lim; ++i) {
        if (head->val != half->val) 
            return 0;
        head = head->next;
        half = half->next;
    }
    return 1;
}
#endif


int main() {
    // int data [] = {1,1}; int is = 1;
    // int data [] = {1,2}; int is = 0;
    // int data [] = {1,2,2,1}; int is = 1;
    // int data [] = {1,2,4,7}; int is = 0;
    // int data [] = {1,2,3,2,1}; int is = 1;
    // int data [] = {1,2,3,4,5,6}; int is = 0;
    // int data [] = {1,2,3,4,5,6,7}; int is = 0;
    int data [] = {1,2,3,4,3,2,1}; int is = 1;
    struct ListNode* head = createList(data, sizeof(data)/sizeof(data[0]));
    printList(head);

    if (is == isPalindrome(head)) {
        printf("OK\n");
    } else {
        printf("Fail. The list %s a palindrome\n", is ? "is" : "in NOT");
    }

    return 0;
}
