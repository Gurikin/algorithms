package org.gurikin.dynamic.twodimentional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class KnightMove {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/knight_move_input.txt"));
        int rows = Integer.parseInt(input.get(0).split(" ")[0]);
        int columns = Integer.parseInt(input.get(0).split(" ")[1]);
        System.out.println(maxKnightMoves(rows, columns));
    }

    private static Integer maxKnightMoves(int rows, int columns) {
        Integer result = 0;
        int[][] dp = new int[rows + 2][columns + 2];
        dp[2][2] = 1;

        for (int i = 2; i < rows + 2; i++) {
            for (int j = 2; j < columns + 2; j++) {
                dp[i][j] = Integer.sum(dp[i - 1][j - 2], dp[i - 2][j - 1]) + dp[i][j];
                result = dp[i][j];
            }
        }
        return result;
    }
}
