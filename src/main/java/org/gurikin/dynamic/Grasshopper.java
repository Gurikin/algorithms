package org.gurikin.dynamic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Grasshopper {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/grasshopper_input.txt"));
        String[] inputArr = input.get(0).split(" ");
        final int n = Integer.parseInt(inputArr[0]);
        final int k = Integer.parseInt(inputArr[1]);
        List<Integer> result = new ArrayList<>();
        result.add(1);
        for (int i = 1; i < n; i++) {
            int point = i;
            int currCellResult = 0;
            while (point > (i - k) && point > 0) {
                currCellResult += result.get(--point);
            }
            result.add(currCellResult);
        }
        System.out.println(result.get(result.size() - 1));
    }
}
