package org.gurikin.dynamic.twodimentional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
// import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// import static org.gurikin.util.PrintUtil.printArr;

public class Nop {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/nop_input.txt"));
        int n = Integer.parseInt(input.get(0).trim());
        String[] nSource = input.get(1).split(" ");
        int[] nArr = new int[n];
        for (int i = 0; i < n; i++) {
            nArr[i] = Integer.parseInt(nSource[i]);
        }
        int m = Integer.parseInt(input.get(2).trim());
        String[] mSource = input.get(3).split(" ");
        int[] mArr = new int[m];
        for (int i = 0; i < m; i++) {
            mArr[i] = Integer.parseInt(mSource[i]);
        }
        for (Integer i:
                maxNop(nArr, mArr)) {
            System.out.print(i + " ");
        }
    }

    /**
     * if (a[i] != b[j])
     *   dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) * (a[i] == b[j])
     * else
     *   dp[i][j] = dp[i - 1][j - 1] + 1
     * @return list of max nop
     */
    private static List<Integer> maxNop(int[] nArr, int[] mArr) {
        LinkedList<Integer> result = new LinkedList<>();
        int[][] dp = new int[nArr.length + 1][mArr.length + 1];
        // printArr(dp, nArr.length + 1, mArr.length + 1);
        int maxIIndex = 0;
        int maxJIndex = 0;
        int maxNop = 0;
        for (int i = 1; i < nArr.length + 1; i++) {
            for (int j = 1; j < mArr.length + 1; j++) {
                if (mArr[j - 1] == nArr[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] >= maxNop) {
                        maxNop = dp[i][j];
                        maxIIndex = i;
                        maxJIndex = j;
                    }
                } else {
                    dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // System.out.println("====");
        // printArr(dp, nArr.length + 1, mArr.length + 1);
        while (maxIIndex > 0 && maxJIndex > 0) {
            if (nArr[maxIIndex - 1] == mArr[maxJIndex - 1]) {
                result.push(nArr[maxIIndex - 1]);
                maxIIndex--;
                maxJIndex--;
            } else if (dp[maxIIndex - 1][maxJIndex] >= dp[maxIIndex][maxJIndex - 1]) {
                maxIIndex--;
            } else {
                maxJIndex--;
            }
        }
        return result;
    }
}
