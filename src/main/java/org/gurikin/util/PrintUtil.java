package org.gurikin.util;

public class PrintUtil {
    public static void printArr(int[][] dp, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%10d", dp[i][j]);
            }
            System.out.println();
        }
    }
}
