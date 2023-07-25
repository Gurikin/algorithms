package org.gurikin.dynamic.twodimentional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class MinCostPath {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/min_cost_path_input.txt"));
        int rowsNum = Integer.parseInt(input.get(0).split(" ")[0]);
        int columnsNum = Integer.parseInt(input.get(0).split(" ")[1]);
        int[][] source = new int[rowsNum][columnsNum];
        for (int i = 1; i <= rowsNum; i++) {
            String[] row = input.get(i).split(" ");
            for (int j = 0; j < columnsNum; j++) {
                source[i - 1][j] = Integer.parseInt(row[j]);
            }
        }
        // System.out.println(Arrays.deepToString(source));
        System.out.println(minCostPath(source, rowsNum, columnsNum));
    }

    public static Integer minCostPath(int[][] source, int rowsNum, int columnsNum) {
        Integer result = 0;
        int[][] dp = new int[rowsNum + 1][columnsNum + 1];
        for (int i = 0; i <= rowsNum; i++) {
            dp[i][0] = 1000000;
        }
        for (int i = 0; i <= columnsNum; i++) {
            dp[0][i] = 1000000;
        }
        for (int i = 1; i <= rowsNum; i++) {
            for (int j = 1; j <= columnsNum; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + source[i - 1][j - 1] - (((i - 1) == 0 && (j - 1) == 0) ? 1000000 : 0);
                result = dp[i][j];
            }
        }
        // for (int i = 1; i <= rowsNum; i++) {
        //     for (int j = 1; j <= columnsNum; j++) {
        //         System.out.printf("%10d", dp[i][j]);
        //     }
        //     System.out.println();
        // }
        return result;
    }
}
