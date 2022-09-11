/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Climbing Stairs
 * @id             70
 * @Difficulty     Easy
 * @Tags           math, dynamic programming, memorization
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/97/dynamic-programming/569/

Climbing Stairs

    You are climbing a staircase. It takes n steps to reach the top.

    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?


Example 1:

    Input: n = 2
    Output: 2
    Explanation: There are two ways to climb to the top.
    1. 1 step + 1 step
    2. 2 steps

Example 2:

    Input: n = 3
    Output: 3
    Explanation: There are three ways to climb to the top.
    1. 1 step + 1 step + 1 step
    2. 1 step + 2 steps
    3. 2 steps + 1 step


Constraints:

    1 <= n <= 45
*/

#include <stdio.h>

/* Use a Fibonacci sequence to solve */
int climbStairs(int n) {
    int a[n > 2 ? n : 2];
    a[0] = 1;
    a[1] = 2;

    for (int i = 2; i < n; ++i)
        a[i] = a[i-1] + a[i-2];
    return a[n-1];
}

int main() {
    /* { n, out} */
    int data [][2] = {
        {2,2},
        {3,3},
        {4,5},
        {5,8},
        {6,13},
        {7,21},
        {8,34},
        {9,55},
    };
    
    for (int i = 0; i < sizeof(data)/sizeof(data[0]); ++i) {
        int ret = climbStairs(data[i][0]);
        char *s;
        if (data[i][1] == ret)
            s = "OK     ";
        else 
            s = "Failed ";

        printf("%s n=%d ret=%d exp=%d\n", s, data[i][0], ret, data[i][1]);
    }
    return 0;
}
