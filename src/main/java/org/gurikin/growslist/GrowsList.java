package org.gurikin.growslist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GrowsList {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        var result = "NO";

        var input = reader.readLine().split(" ");
        Long prevNumber = Long.MIN_VALUE;
        for (String numberStr : input) {
            var currNumber = Long.parseLong(numberStr);
            if (prevNumber >= currNumber) {
                result = "NO";
                break;
            } else {
                prevNumber = currNumber;
                result = "YES";
            }
        }
        writer.write(result);
        reader.close();
        writer.close();
    }
}
