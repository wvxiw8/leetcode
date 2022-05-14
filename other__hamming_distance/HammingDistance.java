import java.util.List;
import java.util.ArrayList;

/*
https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/762/

Hamming Distance

    The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
    Given two integers x and y, return the Hamming distance between them.


Example 1:

    Input: x = 1, y = 4
    Output: 2
    Explanation:
    1   (0 0 0 1)
    4   (0 1 0 0)
        ↑   ↑
    The above arrows point to positions where the corresponding bits are different.

Example 2:

    Input: x = 3, y = 1
    Output: 1


Constraints:

    0 <= x, y <= 2^31 - 1

  */

class Solution {
    public int hammingDistance(int x, int y) {
        int d = 0;
        for (int i = 0; i < 32 || (x != 0 && y != 0); ++i) {
            if ((x&1 ^ y&1) == 1) {
                ++d;
            }
            x >>= 1;
            y >>= 1;
        }
        return d;
    }
}

class HammingDistance {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int x = 0; int y = 1; int exp = 1;
        // int x = 1; int y = 4; int exp = 2;
        // int x = 1; int y = 15; int exp = 3;
        int r = solution.hammingDistance(x, y);
        System.out.printf(r == exp ? "OK\n" : "Fail r=%d\n", r);
    }
}