package org.gurikin.dynamic.knapsack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Knapsack {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/knapsack_input.txt"));
        int n = Integer.parseInt(input.get(0).trim());
        String[] nSource = input.get(1).split(" ");
        int[] items = new int[n];
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(nSource[i]);
        }
        int wMax = Integer.parseInt(input.get(2).trim());
        int[] dp = new int[wMax + 1];
        int[] dpPrev = new int[wMax + 1];
        dp[0] = 1;
        dpPrev[0] = 1;
        for (int w = 0; w < n; w++) {
            for (int i = 1; i <= wMax; i++) {
                if (dp[i] == 0 && (i >= items[w]) && (dpPrev[i - items[w]] == 1))
                    dp[i] = dpPrev[i - items[w]];
            }
            dpPrev = Arrays.copyOf(dp, dp.length);
        }
        System.out.println(Arrays.toString(dp));
        for (int i = wMax; i >= 1; i--) {
            if (dp[i] == 1) {
                System.out.printf("dp[%d] = %d", i, i);
                return;
            }
        }
    }
}
