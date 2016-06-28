package main.java.Misc;

import static org.junit.Assert.*;

/**
 * Created by Tanner on 6/26/2016.
 */
public class ZeroMatrix {

    public static void main(String[] args) {
        ZeroMatrix zeroMatrix = new ZeroMatrix();
        int[][] test1 = new int[][]
                {{0, 0, 0},
                 {0, 0, 1},
                 {1, 1, 1}};
        int[][] test2 = new int[][]
                {{0, 0, 0}};
        int[][] test3 = new int[][]
                {{0},
                 {0},
                 {1}};
        int[][] test4 = new int[][] {{0}};
        int[][] test5 = new int[][] {{1}};
        assertEquals(5, zeroMatrix.numZeroes(test1));
        assertEquals(3, zeroMatrix.numZeroes(test2));
        assertEquals(2, zeroMatrix.numZeroes(test3));
        assertEquals(1, zeroMatrix.numZeroes(test4));
        assertEquals(0, zeroMatrix.numZeroes(test5));
    }

    int numZeroes(int[][] M) {
        int row = M.length - 1;
        int numZeroes = 0;
        for (int col = 0; col < M[0].length && row >= 0; col++) {
            while (M[row][col] != 0) {
                row--;
                if (row < 0) {
                    break;
                }
            }
            numZeroes += row + 1;
        }
        return numZeroes;
    }
}
