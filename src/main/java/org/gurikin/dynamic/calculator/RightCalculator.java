package org.gurikin.dynamic.calculator;

import java.util.Scanner;

public class RightCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int target = sc.nextInt();
        System.out.println(findMinCalc(target));
    }

    public static String findMinCalc(int target) {
        int[] dp = new int[target + 1];
        // Map<Integer, Integer> dp = new LinkedHashMap<>();
        // dp.put(1, 0);
        dp[1] = 0;
        int min;
        for (int i = 2; i <= target; i++) {
            min = dp[i - 1] + 1;
            if (i % 2 == 0) {
                min = Math.min(min, dp[i / 2] + 1);
            }
            if (i % 3 == 0) {
                min = Math.min(min, dp[i / 3] + 1);
            }
            dp[i] = min;
        }

        StringBuilder sb = new StringBuilder();
        int pointer = target;
        while (pointer > 1) {
            sb.insert(0, pointer + " ");
            if (dp[pointer] == dp[pointer - 1] + 1) {
                pointer--;
                continue;
            }

            if ((pointer % 2 == 0) && (dp[pointer] == dp[pointer / 2] + 1)) {
                pointer = pointer / 2;
                continue;
            }

            pointer = pointer / 3;
        }
        sb.insert(0, 1 + " ");
        sb.insert(0, dp[target] + "\n");
        return sb.toString();
    }
}
