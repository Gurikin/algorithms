package org.gurikin.prettystring;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PrettyString {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/pretty_string_input.txt"));
        int sourceChangeNums = Integer.parseInt(input.remove(0));
        byte[] source = input.remove(0).getBytes();
        byte[] dict = "abcdefghijklmnopqrstuvwxyz".getBytes();
        int maxPretty = 0;
        for (byte currByte:
             dict) {
            int lp = 0;
            int currMaxPretty = 0;
            int currChangeNums = sourceChangeNums;
            for (int rp = 0; rp < source.length; rp++) {
                if (currChangeNums < 0) {
                    while (currChangeNums < 0) {
                        if (source[lp] != currByte) {
                            currChangeNums++;
                        } else {
                            currMaxPretty--;
                        }
                        lp++;
                    }
                }
                if (source[rp] == currByte) {
                    currMaxPretty++;
                } else {
                    currChangeNums--;
                }
                maxPretty = Math.max(maxPretty, currMaxPretty + sourceChangeNums);
            }
        }
        System.out.println(maxPretty);
    }
}
