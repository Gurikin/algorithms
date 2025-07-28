package org.gurikin.maxmult;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MaxMult {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        var inputNumbers = Arrays.stream(reader.readLine().split(" ")).map(Long::parseLong).sorted().toList();
        List<Long> resultList = new ArrayList<>();

        var mult1 = inputNumbers.get(0) * inputNumbers.get(1) * inputNumbers.get(inputNumbers.size() - 1);
        var mult2 = inputNumbers.get(inputNumbers.size() - 1) * inputNumbers.get(inputNumbers.size() - 2) * inputNumbers.get(inputNumbers.size() - 3);

        if (mult1 > mult2) {
            resultList.add(inputNumbers.get(0));
            resultList.add(inputNumbers.get(1));
            resultList.add(inputNumbers.get(inputNumbers.size() - 1));
        } else {
            resultList.add(inputNumbers.get(inputNumbers.size() - 1));
            resultList.add(inputNumbers.get(inputNumbers.size() - 2));
            resultList.add(inputNumbers.get(inputNumbers.size() - 3));
        }

        writer.write(resultList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        reader.close();
        writer.close();
    }
}
