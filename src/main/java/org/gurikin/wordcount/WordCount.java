package org.gurikin.wordcount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WordCount {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Set<String> result = new HashSet<>();

        String line = null;
        while ((line = reader.readLine()) != null) {
            result.addAll(
                    Arrays.stream(line.split(" ")).filter(s -> !s.isEmpty()).toList()
            );
        }
        writer.write(String.valueOf(result.size()));
        reader.close();
        writer.close();
    }
}
