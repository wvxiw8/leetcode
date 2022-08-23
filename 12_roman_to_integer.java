/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Roman to Integer
 * @id             12
 * @Difficulty     Easy
 * @Tags           math
 * @Featured       Top interview collection
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/102/math/878/

Roman to Integer

    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000

    For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

    Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

        I can be placed before V (5) and X (10) to make 4 and 9. 
        X can be placed before L (50) and C (100) to make 40 and 90. 
        C can be placed before D (500) and M (1000) to make 400 and 900.

    Given a roman numeral, convert it to an integer.


Example 1:

    Input: s = "III"
    Output: 3
    Explanation: III = 3.

Example 2:

    Input: s = "LVIII"
    Output: 58
    Explanation: L = 50, V= 5, III = 3.

Example 3:

    Input: s = "MCMXCIV"
    Output: 1994
    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


Constraints:

    1 <= s.length <= 15
    s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
    It is guaranteed that s is a valid roman numeral in the range [1, 3999].

*/

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

class Solution {    
    private static Map<Character, Integer> m = new HashMap<Character, Integer>();
    {
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
    }

    private int getArabic(char c) {
        return m.get(c);
    }



    private boolean isSubtraction(char left, char current) {
        if (left == 'I' && current == 'V' ||
            left == 'I' && current == 'X' ||
            left == 'X' && current == 'L' ||
            left == 'X' && current == 'C' ||
            left == 'C' && current == 'D' ||
            left == 'C' && current == 'M' )
            return true;
        return false;
    }

    public int romanToInt(String s) {
        int sum = 0;
        char [] c = s.toCharArray();
        
        boolean debug = false; 
        if (debug) System.out.printf("  %s:\n", s);

        for (int i = c.length - 1; i >= 0; --i) {
            if (debug) System.out.printf("   [%d]=%c", i, c[i]);
        
            if (i > 0 && isSubtraction(c[i-1], c[i])) {
                sum += getArabic(c[i]) - getArabic(c[i-1]);
                --i;
            }
            else {
                sum += getArabic(c[i]);
            }
        
            if (debug) System.out.printf("  sum=%d\n", sum);
        }

        return sum;
    }

    @SuppressWarnings("unused")
    /* Don't use this method. The task says the input is always valid */
    private boolean isRepeatable (char c) {
        /* These symbols can be repeated, but not as many as 3 times */
        if (c == 'I' || c == 'X' || c == 'C' || c == 'M')
            return true;
        return false;
    }

    private static void test(Solution sol, String str, String exp) {
        int res = sol.romanToInt(str);
        if (exp.equals(String.valueOf(res))) 
            System.out.println("OK " + str + "=" + exp); 
        else
            System.out.println("Fail! " + str + "=" + res  + " expected " + exp); 
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String [][] data = {
            {"III", "3"},
            {"LVIII", "58"},
            {"MCMXCIV", "1994"},
        };
   
        for (String[] d : data)
            test(solution, d[0], d[1]);
    }
}
