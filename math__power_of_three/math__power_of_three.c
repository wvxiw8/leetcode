/* https://leetcode.com/explore/interview/card/top-interview-questions-easy/102/math/745/

Power of Three

    Given an integer n, return true if it is a power of three. Otherwise, return false.

    An integer n is a power of three, if there exists an integer x such that n == 3^x.

 

Example 1:

    Input: n = 27
    Output: true

Example 2:

    Input: n = 0
    Output: false

Example 3:

    Input: n = 9
    Output: true

 

Constraints:

    -2^31 <= n <= 2^31 - 1

 
Follow up: Could you solve it without loops/recursion?
 */

#include <stdio.h>

int table[20] = {1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 1594323, 4782969, 14348907, 43046721, 129140163, 387420489, 1162261467};


int isPowerOfThree(int n) {
    if ((n&1) == 0 || n < 0) {
        return 0;
    }
    if (n ==1) {
        return 1;
    }
 
    long int t = 1;
    for (int i = 0; t < n ; ++i) {
        t *= 3;
        if (t == n) {
            return 1;
        }
        if (n < t) {
            return 0;
        }
    }
    return 0;
}


int isPowerOfThree2(int n) {
    int* b = table;
    int* e = &table[19];

    if ((n&1) == 0 || n < 0) {
        return 0;
    }
        
    for (int i = 0; i < sizeof(table)/sizeof(table[0])/2; ++i) {
        if (n < *b) {
            return 0;
        }
        if (n == *b++ || n == *e--) {
            return 1; 
        }
    }
    return 0;
}


int isPowerOfThree3(int n) {
    int* p = table;

    if ((n&1) == 0 || n < 0) {
        return 0;
    }
        
    for (int i = 0; i < sizeof(table)/sizeof(table[0]); ++i) {
        if (n < *p) {
            return 0;
        }
        if (n == *p++) {
            return 1; 
        }
    }
    return 0;
}


int main() {
    int n[] =  {27, 0, 9, 1, 19684, -3, 11, 45, -9};
    int exp[] = {1, 0, 1, 1,     0,  0,  0, 0,   0};

    for (int i = 0; i < sizeof(n)/sizeof(n[0]); ++i) {
        int r = isPowerOfThree1(n[i]);
        printf("%s %d=isPowerOfThree(%d) exp=%d\n", r == exp[i] ? "Pass" : "Fail", r,  n[i], exp[i]); 
    }
}