package org.gurikin.nodnok;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class NodNok {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        var input = reader.readLine().split(" ");
        var left = Long.max(Long.parseLong(input[0]), Long.parseLong(input[1]));
        var nodLeft = left;
        var right = Long.min(Long.parseLong(input[0]), Long.parseLong(input[1]));
        var nodRight = right;
        long nod = 0;
        var remainder = Long.MIN_VALUE;
        while (remainder != 0) {
            remainder = nodLeft % nodRight;
            if (remainder == 0) {
                nod = nodRight;
            }
            nodLeft = nodRight;
            nodRight = remainder;
        }
        long nok = (left * right) / nod;
        writer.write(String.format("%d %d", nod, nok));
        reader.close();
        writer.close();
    }

}
