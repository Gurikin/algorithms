package org.gurikin.opencalculator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class OpenCalculator {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        long result = 0;

        var inputButtons = reader.readLine().split(" ");
        var buttonsSet = new HashSet<Integer>(
                Arrays.asList(inputButtons)
                        .stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );
        var inputNumber = reader.readLine();

        var uniqueNumbers = new HashSet<Integer>();
        for (char ch : inputNumber.toCharArray()) {
            uniqueNumbers.add(ch - '0');
        }
        result = uniqueNumbers.stream().filter(number -> !buttonsSet.contains(number)).count();
        writer.write(String.valueOf(result));
        reader.close();
        writer.close();
    }
}
