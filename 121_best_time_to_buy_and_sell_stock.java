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
// 3 31 2 8
class Solution {
    public int maxProfit4(int[] prices) {
        int buy = prices[0], max_profit = 0;
        for (int i = 1; i < prices.length; i++) {
     
            // Checking for lower buy value
            if (buy > prices[i])
                buy = prices[i];
     
            // Checking for higher profit
            else if (prices[i] - buy > max_profit)
                max_profit = prices[i] - buy;
        }
        return max_profit;
    }

    public int maxProfit5(int[] prices) {
        final int MAX_LEN = 100000;
        int[] sorted = new int[prices.length];
        int[] pos = new int [MAX_LEN];
        int[] posMax = new int [MAX_LEN];
        Arrays.fill(pos, -1);

        sorted = prices.clone();
        Arrays.sort(sorted);

        for (int i = 0; i < prices.length; i++) {
            if (pos[prices[i]] == -1) {
                pos[prices[i]] = i;
                posMax[prices[i]] = i;
            } else {
                posMax[prices[i]] = i;
            }

        }
        System.out.println(Arrays.toString(prices));
        System.out.println(Arrays.toString(sorted));
        // System.out.println(Arrays.toString(pos));

        int end = sorted.length - 1;
        for (int i = 0; i < sorted.length/7; i++) {
            
            if (pos[sorted[i]] < posMax[sorted[end-i]]) {
                System.out.printf( "1: %d %d\n", sorted[end-i], sorted[i]);
                return sorted[end-i] - sorted[i];
                
            }else if (pos[sorted[i+1]] < posMax[sorted[end-i]]) {
                System.out.printf( "2: %d %d\n", sorted[end-i], sorted[i+1]);
                return sorted[end-i] - sorted[i+1];

            } else if (pos[sorted[i]] < posMax[sorted[end-i-1]]) {
                System.out.printf( "3: %d %d\n", sorted[end-i-1], sorted[i]);
                return sorted[end-i-1] - sorted[i];
            }
        }
        return 0;
    }

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

    public int maxProfit1(int[] prices) {
        int min = 0; /* index of min */
        int max = prices.length-1; /* index of max */
        int l = min; /* left scan index */
        int r = max; /* right scan index */
        int minVal = prices[min];
        int maxVal = prices[max];
        int q = 7;
        int quit;

        while (r > min || l < max) {
            System.out.printf(" %d-%d  %d-%d   %d-%d  %d-%d\n", min, prices[min], l, prices[l], r, prices[r], max, prices[max]);

            if (prices[l] < minVal) {
                System.out.printf("  %d now min\n", prices[l]);
                min = l;
                minVal = prices[min];
            } else 
                System.out.printf("  %d skipped for min\n", prices[l]);


            if (prices[r] > maxVal) {
                System.out.printf("  %d now max\n", prices[r]);
                max = r;
                maxVal = prices[max];
            } else 
                System.out.printf("  %d skipped for max\n", prices[r]);

            quit = 2;
            if (r > min)
                --r;
            else 
                --quit;

                
            if (l < r) 
                ++l;
            else 
                --quit;

                
            if (quit == 0) break;

            System.out.printf("\n");
            if (q-- == 0) { System.out.printf("emegrency exiting\n"); break; }
            
            // System.out.printf(" :[%d]%d [%d]%d [%d]%d [%d]%d\n\n", min, prices[min], l, prices[l], r, prices[r], max, prices[max]);

        }

        return maxVal-minVal >= 0 ? maxVal-minVal : 0;
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
