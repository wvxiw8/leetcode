/**
 * @formatter:off
 * @Author          wvxiw
 * @Title           Leetcode task
 * @Task            Power of Two
 * @id              231
 * @Difficulty      Easy
 * @Tags            math, bit manipulation, recursion
 * @Link            https://leetcode.com/problems/power-of-two/

Power of Two
    Given an integer n, return true if it is a power of two. Otherwise, return false.
    An integer n is a power of two, if there exists an integer x such that n == 2x.

Example 1:
    Input: n = 1
    Output: true
    Explanation: 2^0 = 1
Example 2:
    Input: n = 16
    Output: true
    Explanation: 2^4 = 16
Example 3:
    Input: n = 3
    Output: false

Constraints:
-2^31 <= n <= 2^31 - 1

Follow up: Could you solve it without loops/recursion?
 * @formatter:on
 */


class Solution {
    public boolean isPowerOfTwo(int n) {
        int ones = 0;
        if (n <= 0)
            return false;

        for (int i = 0; i < 31; i++) {
            if ((n & 1) == 1)
                ones++;
            n >>>= 1;
        }
        return ones == 1;
    }

    public boolean isPowerOfTwo1(int n) {
        return n <= 0 ? false : (n & (n - 1)) == 0;
    }

    private static class TestData {
        public int n;
        public boolean expected;

        public TestData(int n, boolean output) {
            this.n = n;
            this.expected = output;
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TestData[] testData = {
                new TestData(1, true),
                new TestData(16, true),
                new TestData(3, false),
                new TestData(-2147483646, false),
        };


        for (TestData data : testData) {
            boolean result = solution.isPowerOfTwo(data.n);
            if (result == data.expected)
                System.out.printf("OK.   %d %s power of 2\n", data.n, data.expected == true ? "is" : "isn't");
            else
                System.out.printf("FAIL. %d %s power of 2, however  the answer was %s\n", data.n, data.expected == true ? "is" : "isn't", result == true ? "is" : "isn't");
        }
    }
}