package org.gurikin.symmetrystring;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class SymmetryString {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        var sourceLength = Integer.parseInt(reader.readLine());
        var sourceList = Arrays.asList(reader.readLine().split(" ")).stream().map(Integer::parseInt).toList();
        var resultList = new LinkedList<Integer>();
        int i, lp;
        i = lp = 0;
        int j = sourceLength - 1;
        while (i <= j) {
            if (!sourceList.get(i).equals(sourceList.get(j))) {
                for (int idx = lp; idx <= i; idx++) {
                    resultList.addFirst(sourceList.get(idx));
                }
                lp = ++i;
                j = sourceLength - 1;
            } else {
                i++;
                j--;
            }
        }
        writer.write(String.format("%s\n%s",
                resultList.size(),
                resultList.stream().map(Object::toString).collect(Collectors.joining(" "))
        ));
        reader.close();
        writer.close();
    }
}
