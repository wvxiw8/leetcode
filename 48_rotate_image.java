/** 
 * @Author         wvxiw
 * @Title          Leetcode task
 * @Task           Rotate Image
 * @id             48
 * @Difficulty     Medium
 * @Tags           array, math, matrix
 * @Link           https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/770/
 
Rotate Image

    You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

    You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.


Example 1:

    [1,2,3]    [7,4,1]
    [4,5,6] -> [8,5,2]
    [7,8,9]    [9,6,3]

    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [[7,4,1],[8,5,2],[9,6,3]]

Example 2:

    [ 5,  1,  9, 11]    [15, 13,  2,  5]
    [ 2,  4,  8, 10] -> [14,  3,  4,  1]
    [13,  3,  6,  7]    [12,  6,  8,  9]
    [15, 14, 12, 16]    [16,  7, 10, 11]

    Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
    Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]


Constraints:

    n == matrix.length == matrix[i].length
    1 <= n <= 20
    -1000 <= matrix[i][j] <= 1000
*/
import java.util.Arrays;

class Solution {
    public void rotate(int[][] matrix) {
        int len = matrix[0].length - 1; /* Length of a row */
        for (int r = 0; r < matrix[0].length / 2; ++r) {
            for (int c = r; c < len-r; ++c) {
                int a = matrix[r][c];
                matrix[r][c] = matrix[len-c][r];
                matrix[len-c][r] = matrix[len-r][len-c];
                matrix[len-r][len-c] = matrix[c][len-r];
                matrix[c][len-r] = a;
            }
        }
    }

    private static void print3Matrix(int[][] m, String prefix) {
        System.out.println(prefix);
        for (int[] row : m)
            System.out.println("  " +Arrays.toString(row));
    }

    private static void print3Matrices(int[][] in, int[][] out, int[][] exp) {
        System.out.println("Failed");
        print3Matrix(in, " in: ");
        print3Matrix(out, " out: ");
        print3Matrix(exp, " exp: ");
        System.out.println("\n");
    }

    private static int[][] cloneMatrix(int[][] m) {
        int[][] copy = new int[m.length][];
        for (int i = 0; i < m.length; ++i)
            copy[i] = m[i].clone();
        return copy;
    }

    public static void main(String[] args) {
        /* [row] [col], [an input matrix & expected matrix], [input data sets] */
        int[][][][] data = {
            { {{1,2,3},{4,5,6},{7,8,9}},  {{7,4,1},{8,5,2},{9,6,3}} },
            { {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}},  {{15,13,2,5},{14,3,4,1},{12,6,8,9},{16,7,10,11}} }
        };
        
        
        Solution s = new Solution();
        for (int[][][] twoMatrices : data) {
            int[][] initial = cloneMatrix(twoMatrices[0]);

            s.rotate(twoMatrices[0]);
            if (Arrays.deepEquals(twoMatrices[0], twoMatrices[1]))
                System.out.println("OK");
            else
                print3Matrices(initial, twoMatrices[0], twoMatrices[1]);
        }
    }
}
