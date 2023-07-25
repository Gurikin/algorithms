package org.gurikin.dynamic.tanks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Tanks {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/tanks.txt"));
        String[] inputArr = input.get(0).split(" ");
        final int n = Integer.parseInt(inputArr[0]);
        List<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(3);
        if (n == 0 || n == 1) {
            System.out.println(result.get(n));
            return;
        }
        for (int i = 2; i <= n; i++) {
            result.add(result.get(i - 1) * 2 + result.get(i - 2) * 2);
        }
        System.out.println(result.get(result.size() - 1));
    }
}