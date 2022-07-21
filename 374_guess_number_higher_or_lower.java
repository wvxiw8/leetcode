/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Guess Number Higher or Lower
 * @id             374
 * @Difficulty     Easy
 * @Tags           binary search
 * @Link           https://leetcode.com/problems/guess-number-higher-or-lower/

Guess Number Higher or Lower

    We are playing the Guess Game. The game is as follows:

    I pick a number from 1 to n. You have to guess which number I picked.

    Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.

    You call a pre-defined API int guess(int num), which returns three possible results:

       -1: Your guess is higher than the number I picked (i.e. num > pick).
        1: Your guess is lower than the number I picked (i.e. num < pick).
        0: your guess is equal to the number I picked (i.e. num == pick).

    Return the number that I picked.


Example 1:

    Input: n = 10, pick = 6
    Output: 6

Example 2:

    Input: n = 1, pick = 1
    Output: 1

Example 3:

    Input: n = 2, pick = 1
    Output: 1


Constraints:

    1 <= n <= 2^31 - 1
    1 <= pick <= n


*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return       -1 if num is higher than the picked number
 *                1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */
class GuessGame {
    private int pick;
    GuessGame(int pick) { 
        this.pick = pick;
    }
    int guess(int num) {
        if (num > pick)
            return -1;
        else if (num < pick)
            return 1;
        else
            return 0;
    }
}

class Solution extends GuessGame {
    Solution(int n) {
        super(n);
    }

    /* Again, binary search with exclusive upper bound. 
       The task resembles the 278_first_bad_version task */
    public int guessNumber(int n) {
        int l = 1; /* 1 is done by the task*/
        int m = l;
        int u = n; /* Start with n, not n+1, because at INT_MAX it'd be an overflow */

        while (l < u) {
            m = l + (u - l) / 2;
            int r = guess(m);

            // System.out.printf(" l=%d m=%d u=%d r=%d\n", l, m, u, r);
            if (r == 0) 
                return m;
            else if (r == -1)
                u = m;
            else /* r == 1 */
                l = m + 1;
        }
        return u;
    }

    public static void main(String[] args) {
        List <int[]> input = new ArrayList<>();
                          /* n, pick*/
        input.add(new int[] {20, 6});
        input.add(new int[] {4, 2}); 
        input.add(new int[] {2147483647, 2147483647}); 
        input.add(new int[] {111, 111}); 
        input.add(new int[] {1, 1}); 

        for (int[] in : input) {
            int n = in[0];
            int pick = in[1];
            Solution solution = new Solution(pick);
            int result = solution.guessNumber(n);
            System.out.print(Arrays.toString(in));

            if (result == pick) 
                System.out.println(" OK");
            else
                System.out.println("    Fail");
        }
    }
}
