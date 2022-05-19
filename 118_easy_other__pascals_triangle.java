import java.util.List;
import java.util.ArrayList;


/**
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Pascal's Triangle
 * @id             118
 * @Difficulty     Easy
 * @Tags           other
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/601/

Pascal's Triangle

    Given an integer numRows, return the first numRows of Pascal's triangle.
    In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

 
Example 1:

    Input: numRows = 5
    Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
    
    1
   1 1
  1 2 1
 1 3 3 1
1 4 6 4 1 

Example 2:

    Input: numRows = 1
    Output: [[1]]


Constraints:

    1 <= numRows <= 30  */

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>(numRows);
        List<Integer> prev = null;
        for (int i = 0; i < numRows; ++i) {
            List<Integer> sublist = new ArrayList<>(i+1);
            for (int j = 0; j < i+1; ++j) {
                if (j == 0 || j == i) {
                    sublist.add(1);
                } else {
                    if (i != 0) {
                        sublist.add(prev.get(j-1) + prev.get(j));
                    }
                }
            }
            list.add(sublist);
            prev = sublist;

        }
        return list;
    }
}

class PascalsTriangle {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> result = solution.generate(5);
        System.out.println(result);
    }
}