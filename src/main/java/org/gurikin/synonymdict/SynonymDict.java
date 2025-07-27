package org.gurikin.synonymdict;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SynonymDict {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, String> dict = new HashMap<>();
        var pairNum = Long.parseLong(reader.readLine());
        for (int i = 0; i < pairNum; i++) {
            var dictRow = reader.readLine().split(" ");
            dict.put(dictRow[0], dictRow[1]);
            dict.put(dictRow[1], dictRow[0]);
        }
        var searchWord = reader.readLine();
        writer.write(dict.get(searchWord));
        reader.close();
        writer.close();
    }
}
