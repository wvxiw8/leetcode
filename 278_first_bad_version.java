/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           First Bad Version
 * @id             278
 * @Difficulty     Easy
 * @Tags           binary search
 * @Link           https://leetcode.com/problems/first-bad-version/

First Bad Version

    You are a product manager and currently leading a team to develop a new product. 
    Unfortunately, the latest version of your product fails the quality check. 
    Since each version is developed based on the previous version, 
    all the versions after a bad version are also bad.

    Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, 
    which causes all the following ones to be bad.

    You are given an API bool isBadVersion(version) which returns whether version is bad. 
    Implement a function to find the first bad version. 
    You should minimize the number of calls to the API.



Example 1:

    Input: n = 5, bad = 4
    Output: 4
    Explanation:
    call isBadVersion(3) -> false
    call isBadVersion(5) -> true
    call isBadVersion(4) -> true
    Then 4 is the first bad version.

Example 2:

    Input: n = 1, bad = 1
    Output: 1

 

Constraints:

    1 <= bad <= n <= 2^31 - 1

*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class VersionControl {
    private int firstBadVersion;
    VersionControl (int firstBadVersion) {
        this.firstBadVersion = firstBadVersion;
    }
    boolean isBadVersion(int version) {
        if (version >= firstBadVersion)
            return true;
        return false;
    }
}

/* The isBadVersion API is defined in the parent class VersionControl.
   boolean isBadVersion(int version); */
class Solution extends VersionControl {

    Solution(int firstBadVersion) {
        super(firstBadVersion);
    }

    /* Implement typical binary search with exclusive upper bound */
    public int firstBadVersion(int n) {
        int lower = 0;
        int upper = n;
        int mid = lower;
        while (lower < upper) {
            mid = lower + ((upper - lower) /2);
            if (isBadVersion(mid))
                upper = mid;
            else 
                lower = mid + 1;
        }
        return upper;
    }

    public static void main(String[] args) {
        List <int[]> input = new ArrayList<>();
                          /* n, bad*/
        input.add(new int[] {5, 4});
        input.add(new int[] {1, 1});

        for (int[] in : input) {
            int n = in[0];
            int bad = in[1];
            Solution solution = new Solution(bad);
            int result = solution.firstBadVersion(n);
            System.out.print(Arrays.toString(in));

            if (result == bad) 
                System.out.println(" OK");
            else
                System.out.println("    Fail");
        }
    }
}
