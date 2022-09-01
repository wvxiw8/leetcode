/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Best Time to Buy and Sell Stock
 * @id             121
 * @Difficulty     Easy
 * @Tags           array, dynamic programming
 * @Link           https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

Best Time to Buy and Sell Stock

    You are given an array prices where prices[i] is the price of a given stock on the ith day.

    You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

    Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.


Example 1:

    Input: prices = [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:

    Input: prices = [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transactions are done and the max profit = 0.


Constraints:

    1 <= prices.length <= 10^5
    0 <= prices[i] <= 10^4
*/

import java.util.Arrays;

class Solution {
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int maxprof = 0;

        for (int i = 0; i < prices.length; i++) {
            min = prices[i] < min ? prices[i] : min;
            int prof = prices[i] - min;
            maxprof = prof > maxprof ? prof : maxprof;
        }
        return maxprof;
    }

    public static void main(String[] args) {
        /* Prices, maxProfit */
        int [][][] data = {
            {{7,1,5,3,6,4}, {5}},
            {{7,6,4,3,1}, {0}},
            {{1,4,3}, {3}},
            {{3,2,6,5,0,3}, {4}},
            {{2,1}, {0}},
            {{2,1,2,1,0,0,1}, {1}},
            {{2,1,2,1,0,1,2}, {2}},
            {{4,7,1,2}, {3}},
        };

        Solution s = new Solution();
        for (int[][] d : data) {
            System.out.println(Arrays.toString(d[0])+ ":");
            int exp = d[1][0];
            int calc = s.maxProfit(d[0]);
            if ( calc == exp) {
                System.out.println("OK");
            } else {
                System.out.println("Fail. " +Arrays.toString(d[0])+ " calc=" +calc+ " exp=" +exp);
            }
            System.out.printf("\n");
            
        }
    }
}
