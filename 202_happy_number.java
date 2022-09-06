/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Happy Number
 * @id             202
 * @Difficulty     Easy
 * @Tags           hash table, math
 * @Link           https://leetcode.com/problems/happy-number/

 Happy Number

    Write an algorithm to determine if a number n is happy.

    A happy number is a number defined by the following process:

        Starting with any positive integer, replace the number by the sum of the squares of its digits.
        Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
        Those numbers for which this process ends in 1 are happy.

    Return true if n is a happy number, and false if not.


Example 1:

    Input: n = 19
    Output: true
    Explanation:
    1^2 + 9^2 = 82
    8^2 + 2^2 = 68
    6^2 + 8^2 = 100
    1^2 + 0^2 + 0^2 = 1


Example 2:

    Input: n = 2
    Output: false


Constraints:

    1 <= n <= 2^31 - 1  */


import java.util.HashSet;
import java.util.Set;

class Solution {
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        while (n > 1) {
            int i = n;
            n = 0;
            while (i > 0) {
                int rem = i % 10;
                i /= 10;
                n += rem * rem;
            }
            // System.out.println(n);
            if (!set.add(n)) 
                return false;
        }
        return n == 1;
    }
    
    public static void main(String[] args) {
        isHappy(2);
    }
}
