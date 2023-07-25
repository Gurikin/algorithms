package org.gurikin.dynamic.twodimentional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CafeCost {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/cafe_cost_input.txt"));
        int days = Integer.parseInt(input.get(0));
        int[] dayCost = new int[days + 1];
        dayCost[0] = 0;
        for (int i = 1; i < dayCost.length; i++) {
            dayCost[i] = Integer.parseInt(input.get(i));
        }
        minCafeCost(dayCost);
    }

    private static void minCafeCost(int[] dayCost) {
        int[][] dp = new int[dayCost.length][dayCost.length];
        dp[0][0] = 0;
        int infinity = 400 * (dayCost.length - 1) * (dayCost.length - 1);
        for (int j = 1; j < dayCost.length; j++) {
            dp[0][j] = infinity;
        }

        for (int i = 1; i < dayCost.length; i++) {
            for (int j = 0; j < dayCost.length; j++) {
                if (j == dayCost.length - 1) {
                    dp[i][j] = Integer.min(dp[i - 1][j - 1] + dayCost[i], dp[i - 1][j]);
                }/* else if (j == 0 && dayCost[i] > 100) {
                    dp[i][j] = infinity;
                } */else if (dayCost[i] <= 100 || j == 0) {
                    dp[i][j] = Integer.min(dp[i - 1][j] + dayCost[i], dp[i - 1][j + 1]);
                } else {
                    dp[i][j] = Integer.min(dp[i - 1][j - 1] + dayCost[i], dp[i - 1][j + 1]);
                }
            }
        }
        printArr(dp, dayCost);
        int leastCoupons = dayCost.length - 1;
        for (int j = leastCoupons; j > 0; j--) {
            if (dp[dayCost.length - 1][j] > dp[dayCost.length - 1][j - 1]) {
                leastCoupons = j - 1;
            }
        }
        int minCost = dp[dayCost.length - 1][leastCoupons];
        int spentCoupons = -1;
        int j = leastCoupons;
        List<Integer> daysWithCoupon = new ArrayList<>();
        for (int i = dayCost.length - 1; i > 0; i--) {
            if (dayCost[i] <= 100 || j == 0) {
                if (dp[i][j] == dp[i - 1][j + 1]) {
                    daysWithCoupon.add(i);
                    j++;
                }
            } else if (j == dayCost.length - 1) {
                continue;
            } else {
                if (dp[i][j] == dp[i - 1][j + 1]) {
                    daysWithCoupon.add(i);
                    j++;
                } else {
                    j--;
                }
            }
        }

        spentCoupons = daysWithCoupon.size();
        System.out.println(minCost);
        System.out.println(leastCoupons + " " + spentCoupons);
        daysWithCoupon.stream().distinct().sorted().forEach(System.out::println);
        System.out.println(Arrays.toString(dayCost));
    }

    private static void printArr(int[][] dp, int[] dayCost) {
        for (int i = 0; i < dayCost.length; i++) {
            for (int j = 0; j < dayCost.length; j++) {
                System.out.printf("%10d", dp[i][j]);
            }
            System.out.println();
        }
    }
}
