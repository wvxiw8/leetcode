/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Best Time to Buy and Sell Stock II
 * @id             122
 * @Difficulty     Medium
 * @Tags           array, dynamic programming
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/564/

Best Time to Buy and Sell Stock II

    You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

    On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

    Find and return the maximum profit you can achieve.


Example 1:

    Input: prices = [7,1,5,3,6,4]
    Output: 7
    Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
    Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
    Total profit is 4 + 3 = 7.

Example 2:

    Input: prices = [1,2,3,4,5]
    Output: 4
    Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
    Total profit is 4.

Example 3:

    Input: prices = [7,6,4,3,1]
    Output: 0
    Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.


Constraints:

    1 <= prices.length <= 3 * 10^4
    0 <= prices[i] <= 10^4
*/

import java.util.Arrays;

class Solution {
    public int maxProfit(int[] prices) {
        int curr = prices[0];
        int next = 0;
        int min = prices[0];
        int maxprof = 0;
        int prof = 0;

        for (int i = 0; i < prices.length; ++i) {
            curr = prices[i];
            next = i > prices.length - 2 ? 0 : prices[i+1];
            min = curr < min ? curr : min;
            
            prof = curr - min;
            System.out.printf(" min=%d curr=%d next=%d prof=%d\n", min, curr, next, prof);
            if (prof > 0) {
                if (next < curr) {
                    maxprof += prof;
                    prof = 0;
                    min = curr;
                    System.out.printf(" Sell\n");
                }
            }
        }
        return maxprof;
    }

    public static void main(String[] args) {
        /* {{Prices}, {maxProfit}} */
        int [][][] data = {
            {{7,1,5,3,6,4}, {7}},
            {{1,2,3,4,5}, {4}},
            {{7,6,4,3,1}, {0}},
        };

        Solution s = new Solution();
        for (int[][] d : data) {
            System.out.println(Arrays.toString(d[0])+ ":");
            int exp = d[1][0];
            int calc = s.maxProfit(d[0]);
            if (calc == exp) {
                System.out.println("OK");
            } else {
                System.out.println("Fail. " +Arrays.toString(d[0])+ " calc=" +calc+ " exp=" +exp);
            }
            System.out.printf("\n");
        }
    }
}
