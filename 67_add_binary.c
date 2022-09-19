/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Add Binary
 * @id             67
 * @Difficulty     Easy
 * @Tags           math, string, bit manipulation, simulation
 * @Link           https://leetcode.com/problems/add-binary/

Add Binary

    Given two binary strings a and b, return their sum as a binary string.


Example 1:

    Input: a = "11", b = "1"
    Output: "100"

Example 2:

    Input: a = "1010", b = "1011"
    Output: "10101"


Constraints:

    1 <= a.length, b.length <= 10^4
    a and b consist only of '0' or '1' characters.
    Each string does not contain leading zeros except for the zero itself. */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* addBinary(char * a, char * b) {
    int lenA = strlen(a);
    int lenB = strlen(b);
    int lenO = (lenA > lenB ? lenA: lenB) + 1;

    char* output = malloc(lenO + 1);
    char* pO = output+lenO;
    *pO-- = '\0';

    char* pA = &a[lenA-1];
    char* pB = &b[lenB-1];
    char c = 0; /* Carry */

    // memset(output, '_', lenO);
    // printf(" output=%p pO=%p\n", output, pO);
    // printf(" lenA=%d lenB=%d lenO=%d\n", lenA, lenB, lenO);

    while (pA >= a || pB >= b) {
        char a1 = pA >= a ? *pA-- : 0;
        char b1 = pB >= b ? *pB-- : 0;

        // printf(" a=%d b=%d c=%d\n", a1&1 , b1&1, c);
        char sum = a1 + b1 + c;
        *pO = (sum & 1) + '0';
        c = (sum >> 1) & 1;
        // printf(" s=%c     c=%d p=%p\n\n", *pO, c, pO);
        --pO;
    }

    // printf(" c=%d output=%s\n", c, output);

    if (c)
        *pO-- = '1';
    else
        memmove(output, &output[1], lenO );

    return output;
}

int main() {
    // char* a = "11"; char* b = "1"; char* exp = "100";
    // char* a = "1010"; char* b = "1011"; char* exp = "10101";
    char* a = "1010010100"; char* b = "11101101110"; char* exp = "101000000010";
    // char* a = "0"; char* b = "0"; char* exp = "0";
    

    char* output = addBinary(a, b);
    int lenA = strlen(a); 
    int lenB = strlen(b); 
    int width = (lenA > lenB ? lenA: lenB) + 1;
    printf("%*s +\n", width, a); 
    printf("%*s =\n", width, b);
    printf("%*s =\n", width, output);
    if (!strcmp(output, exp))
        printf("OK\n");
    else 
        printf("Failed. expected\n%*s\n", width, exp);

    free(output);

    return 0;
}
