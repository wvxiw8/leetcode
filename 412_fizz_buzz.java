/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Fizz Buzz
 * @id             412
 * @Difficulty     Easy
 * @Tags           math
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/102/math/743/

Fizz Buzz

    Given an integer n, return a string array answer (1-indexed) where:

        answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
        answer[i] == "Fizz" if i is divisible by 3.
        answer[i] == "Buzz" if i is divisible by 5.
        answer[i] == i (as a string) if none of the above conditions are true.


Example 1:

    Input: n = 3
    Output: ["1","2","Fizz"]

Example 2:

    Input: n = 5
    Output: ["1","2","Fizz","4","Buzz"]

Example 3:

    Input: n = 15
    Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]

 

Constraints:

    1 <= n <= 10^4
*/

import java.util.List;
import java.util.ArrayList;

class Solution {
    public List<String> fizzBuzz(int n) {
        List <String> list = new ArrayList<String>();
        int limit = n + 1;
        for (int i = 1; i < limit; i++) {
            String s;
            boolean isMul3 = i % 3 == 0;
            boolean isMul5 = i % 5 == 0;
            if (isMul3 && isMul5)
                s = "FizzBuzz";
            else if (isMul3)
                s = "Fizz";
            else if (isMul5)
                s = "Buzz";
            else 
                s = String.valueOf(i);
            list.add(s);
        }
        return list;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
         System.out.print(s.fizzBuzz(3));
         System.out.print(s.fizzBuzz(5));
         System.out.print(s.fizzBuzz(15));
    }
}
