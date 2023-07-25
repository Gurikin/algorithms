package org.gurikin.dynamic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ThreeInRow {
    public static void main(String[] args) throws IOException {
        // threeInRowPowOfTwo();
        long startAt = System.currentTimeMillis();
        threeInRowLine();
        System.out.println(System.currentTimeMillis() - startAt);
    }

    private static void threeInRowLine() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/three_in_row_input.txt"));
        int totalIterations = Integer.parseInt(input.get(0));
        List<Integer> dp = new ArrayList<>();
        dp.add(2);
        dp.add(4);
        dp.add(7);
        if (totalIterations <= 3) {
            System.out.println(dp.get(totalIterations - 1));
            return;
        }
        for (int i = 3; i < totalIterations; i++) {
            dp.add(dp.get(i - 1) + dp.get(i - 2) + dp.get(i - 3));
        }
        System.out.println(dp.get(dp.size() - 1));
    }

    private static void threeInRowPowOfTwo() throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/three_in_row_input.txt"));
        int source = Integer.parseInt(input.get(0));
        int totalIterations = (int) Math.pow(2, source);
        int n = 0;
        long startAt = System.currentTimeMillis();
        String format = "%0" + source + "d\n";
        for (int i = 0; i < totalIterations; i++) {
            if (Integer.toBinaryString(i).contains("111")) {
                n++;
            }
            System.out.printf(format, Integer.parseInt(Integer.toBinaryString(i)));
        }
        System.out.println(n);
        System.out.println(totalIterations);
        System.out.println(System.currentTimeMillis() - startAt);
    }
}
