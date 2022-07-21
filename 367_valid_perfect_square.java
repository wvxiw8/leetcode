/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Valid Perfect Square
 * @id             367
 * @Difficulty     Easy
 * @Tags           binary search
 * @Link           https://leetcode.com/problems/valid-perfect-square/

Valid Perfect Square

    Given a positive integer num, write a function which returns True if num is a perfect square else False.

    Follow up: Do not use any built-in library function such as sqrt.



Example 1:

    Input: num = 16
    Output: true

Example 2:

    Input: num = 14
    Output: false

 

Constraints:

    1 <= num <= 2^31 - 1

*/

import java.util.List;
import java.util.ArrayList;
import java.util.ArrayList;

class Solution {

    /* Again, binary search with exclusive upper bound */
    public boolean isPerfectSquare(int num) {
        int lower = 1;
        int mid = lower;
        int upper = num + 1;

        while (lower < upper) {
            mid = lower + (upper - lower) / 2;
            long square = (long)mid * mid; /* Pay attention here to casting the first <mid> to long */

            // System.out.printf(" l=%d m=%d u=%d s=%d\n", lower, mid, upper, square);
            if (square == num)
                return true;
            else if (square < num)
                lower = mid + 1;
            else 
                upper = mid;
        }
        return false;
    }

    public static void main(String[] args) {
        List <int[]> input = new ArrayList<>();
                        /* num, isquare*/
        input.add(new int[] {1, 1});
        input.add(new int[] {16, 1});
        input.add(new int[] {14, 0});
        input.add(new int[] {808201, 1});

        Solution solution = new Solution();
        for (int[] in : input) {
            System.out.print(in[0] + ": ");
            boolean result = solution.isPerfectSquare(in[0]);
            
            if (result == (in[1] != 0)) 
                System.out.println("Passed " + ((in[1] == 1) ? "(is square)" : "(isn't square)"));
            else
                System.out.println("FAIL");
        }
    }
}
