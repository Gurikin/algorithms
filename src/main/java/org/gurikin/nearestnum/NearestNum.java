package org.gurikin.nearestnum;

import java.io.*;
import java.util.Arrays;

public class NearestNum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        var arrSize = Integer.parseInt(reader.readLine());
        var sourceArr = reader.readLine().split(" ");
        var searchNum = Integer.parseInt(reader.readLine());
        var result = Integer.MAX_VALUE;
        var prevDiff = Integer.MAX_VALUE;
        for (String iStr : sourceArr) {
            var i = Integer.parseInt(iStr);
            var diff = (searchNum > i) ? searchNum - i : i - searchNum;
            if (diff == 0) {
                result = i;
                break;
            }
            if (prevDiff > diff) {
                result = i;
                prevDiff = diff;
            }
        }
        writer.write(String.valueOf(result));
        reader.close();
        writer.close();
    }
}
