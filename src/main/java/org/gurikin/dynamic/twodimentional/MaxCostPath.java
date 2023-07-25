package org.gurikin.dynamic.twodimentional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MaxCostPath {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/max_cost_path_input.txt"));
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
        Map<Integer, LinkedList<String>> result = maxCostPath(source, rowsNum, columnsNum);
        System.out.println(result.keySet().iterator().next());
        result.values().iterator().next().forEach(p -> System.out.print(p + " "));
    }

    public static Map<Integer, LinkedList<String>> maxCostPath(int[][] source, int rowsNum, int columnsNum) {
        Integer result = 0;
        int[][] dp = new int[rowsNum + 1][columnsNum + 1];
        LinkedList<String> path = new LinkedList<>();
        for (int i = 0; i <= rowsNum; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= columnsNum; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= rowsNum; i++) {
            for (int j = 1; j <= columnsNum; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + source[i - 1][j - 1];
                result = dp[i][j];
            }
        }
        int rp = rowsNum;
        int cp = columnsNum;
        while (rp >= 1) {
            while (cp >= 1) {
                if (dp[rp-1][cp] >= dp[rp][cp - 1] && rp != 1) {
                    path.push("D");
                    break;
                } else if (cp != 1) {
                    path.push("R");
                    cp--;
                } else {
                    cp--;
                }
            }
            rp--;
        }
        // System.out.println(path);
        // for (int i = 1; i <= rowsNum; i++) {
        //     for (int j = 1; j <= columnsNum; j++) {
        //         System.out.printf("%10d", dp[i][j]);
        //     }
        //     System.out.println();
        // }
        return Map.of(result, path);
    }
}
