/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Best Time to Buy and Sell Stock III
 * @id             123
 * @Difficulty     Hard
 * @Tags           array, dynamic programming
 * @Link           https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

Best Time to Buy and Sell Stock III

 You are given an array prices where prices[i] is the price of a given stock on the ith day.

    Find the maximum profit you can achieve. You may complete at most two transactions.

    Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).


Example 1:

    Input: prices = [3,3,5,0,0,3,1,4]
    Output: 6
    Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
    Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

Example 2:

    Input: prices = [1,2,3,4,5]
    Output: 4
    Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
    Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

Example 3:

    Input: prices = [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transaction is done, i.e. max profit = 0.

Constraints:

    1 <= prices.length <= 10^5
    0 <= prices[i] <= 10^5
*/

import java.util.Arrays;


/* Take the maxProfit() method from the 121_best_time_to_buy_and_sell_stock.java 
and rework it to a class, which is able to return max profit for every iteration. */
class ProfitIterativeCalculator {
    
    private int maxprof = 0;
    private int i = 0;
    private int extrem; /* min for froward / max for backward scanning */ 
    private boolean forward; 
    private int[] prices;
    
    public ProfitIterativeCalculator(int[] prices, boolean forward) {
        this.forward = forward; 
        this.prices = prices; 
        this.extrem = forward ? prices[0] : prices[prices.length - 1];
    }

    public int step() {
        if (forward) {
            int j = i;
            extrem = prices[j] < extrem ? prices[j] : extrem;
            int prof = prices[j] - extrem;
            maxprof = prof > maxprof ? prof : maxprof;
        } 
        else {
            int j = prices.length - 1 - i;
            extrem = prices[j] > extrem ? prices[j] : extrem;
            int prof = extrem - prices[j];
            maxprof = prof > maxprof ? prof : maxprof;
        }
        ++i;
        return maxprof;
    }
}


class Solution {

    public int maxProfit(int[] prices) {
        int max = 0;
        int[][] a = new int[2][prices.length];
        ProfitIterativeCalculator forwarder = new ProfitIterativeCalculator(prices, true);
        ProfitIterativeCalculator backwarder = new ProfitIterativeCalculator(prices, false);


        for (int i = 0; i < prices.length; ++i) {
            a[0][i] = forwarder.step();
            a[1][prices.length - 1 - i] = backwarder.step();
        }

        // System.out.println(Arrays.toString(a[0])); // Forward profits for every step
        // System.out.println(Arrays.toString(a[1])); // Backward profits for every step

        for (int i = 0; i < prices.length; ++i) {
            int sum = a[0][i] + a[1][i];
            max = sum > max ? sum : max;
        }
        return max;
    }

    public static void main(String[] args) {
        /* {{Prices}, {maxProfit}} */
        int [][][] data = {
            {{3,3,5,0,0,3,1,4}, {6}},
            {{1,2,3,4,5}, {4}},
            {{7,6,4,3,1}, {0}},
            {{1,2,4,2,5,7,2,4,9,0}, {13}},
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
