/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Valid Sudoku
 * @id             36
 * @Difficulty     Medium
 * @Tags           array, hash table, matrix
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/769/

Valid Sudoku

    Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

        Each row must contain the digits 1-9 without repetition.
        Each column must contain the digits 1-9 without repetition.
        Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.

    Note:

        A Sudoku board (partially filled) could be valid but is not necessarily solvable.
        Only the filled cells need to be validated according to the mentioned rules.


Example 1:

    Input: board = 
    [["5","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    Output: true

Example 2:

    Input: board = 
    [["8","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    Output: false
    Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


Constraints:

    board.length == 9
    board[i].length == 9
    board[i][j] is a digit 1-9 or '.'.

*/
import java.util.Arrays;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        int [] a = new int [9];
        /* t - type of scan: 0-row, 1-column, 2-ub-box */
        for (int t = 0; t < 3; ++t) {

            for (int r = 0; r < 9; ++r) {
                Arrays.fill(a, 0);
                // System.out.printf("%s %d: ", t == 0 ? "Row" : t == 1 ? "Column" : "Sub-box", r);

                for (int c = 0; c < 9; ++c) {
                    int i = t == 0 ? board[r][c] : t == 1 ? board[c][r] : board[c/3 + 3*(r/3)][c%3 + 3*(r%3)]; 
                    if (i == '.')
                        continue;
                    
                    // System.out.printf(" %c", i);
                    i -= '0';
                    if (a[i-1] == 0) 
                        a[i-1] = 1;
                    else {
                        // System.out.println(" !");
                        return false;
                    }
                }
                // System.out.printf("\n");
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][][] boards = {
            /* Valid */
            {{'5','3','.','.','7','.','.','.','.'} /* 00 01 02 10 11 12 20 21 22 */
            ,{'6','.','.','1','9','5','.','.','.'} /* 03 04 05 13 14 15 23 24 25 */
            ,{'.','9','8','.','.','.','.','6','.'} /* 06 07 08 16 17 18 26 27 28 */
            ,{'8','.','.','.','6','.','.','.','3'} /* 30 31 32 40 41 42 ..       */
            ,{'4','.','.','8','.','3','.','.','1'} /* 33 34 35 ..                */
            ,{'7','.','.','.','2','.','.','.','6'} /* 36 37 38                   */
            ,{'.','6','.','.','.','.','2','8','.'} /* ..                         */
            ,{'.','.','.','4','1','9','.','.','5'}
            ,{'.','.','.','.','8','.','.','7','9'}},

            // /* Not valid */
            {{'8','3','.','.','7','.','.','.','.'}
            ,{'6','.','.','1','9','5','.','.','.'}
            ,{'.','9','8','.','.','.','.','6','.'}
            ,{'8','.','.','.','6','.','.','.','3'}
            ,{'4','.','.','8','.','3','.','.','1'}
            ,{'7','.','.','.','2','.','.','.','6'}
            ,{'.','6','.','.','.','.','2','8','.'}
            ,{'.','.','.','4','1','9','.','.','5'}
            ,{'.','.','.','.','8','.','.','7','9'}}
        };


        Solution s = new Solution();
        for (char[][] board : boards) {
            if(s.isValidSudoku(board))
                System.out.println("OK");
        }
    }
}
