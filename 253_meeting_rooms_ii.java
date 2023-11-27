/**
 * @formatter:off
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Meeting Rooms II
 * @id             253
 * @Difficulty     Medium
 * @Access         Premium
 * @Tags           array, two pointers, greedy, sorting, heap (priority queue), prefix sum
 * @Link           https://leetcode.com/problems/meeting-rooms-ii/

Meeting Rooms II
    Given an array of meeting time intervals `intervals` where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

Example 1:
    Input: intervals = [[0,30],[5,10],[15,20]]
    Output: 2

Example 2:
    Input: intervals = [[7,10],[2,4]]
    Output: 1

Constraints:
    1 <= intervals.length <= 10^4
    0 <= starti < endi <= 10^6
 @formatter:on
 */

import java.util.Arrays;

class Solution {
    /* The simplest obvious solution */
    public int minMeetingRooms1(int[][] intervals) {
        int[] a = new int[1000000];
        for (int[] interval : intervals) {
            for (int i = interval[0]; i < interval[1]; i++)
                a[i]++;
        }
        int max = 0;
        for (int v : a) {
            if (v > max)
                max = v;
        }
        return max;
    }

    public int minMeetingRooms(int[][] intervals) {
        return minMeetingRooms1(intervals);
    }

    public static void main(String[] args) {
        class TestData {
            int[][] intervals;
            int exp;

            TestData(int exp, int[][] intervals) {
                this.exp = exp;
                this.intervals = intervals;
            }
        }

        TestData[] testData = {
                new TestData(2, new int[][]{{0, 30}, {5, 10}, {15, 20}}),
                new TestData(1, new int[][]{{7, 10}, {2, 4}}),
        };

        Solution s = new Solution();
        for (TestData t : testData) {
            int result = s.minMeetingRooms(t.intervals);
            if (result == t.exp) {
                System.out.println("OK");
            } else {
                System.out.printf("FAILED res=%d ext=%d data=", result, t.exp);
                Arrays.stream(t.intervals).forEach(nums -> System.out.printf("[%d %d],", nums[0], nums[1]));
                System.out.println();
            }
        }
    }
}


