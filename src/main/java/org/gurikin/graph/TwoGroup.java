package org.gurikin.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class TwoGroup {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/two_group_input.txt"));
        int v = parseInt(input.get(0).trim().split(" ")[0]);
        int e = parseInt(input.get(0).trim().split(" ")[1]);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= e; i++) {
            String[] edge = input.get(i).trim().split(" ");
            if (graph.containsKey(parseInt(edge[0]))) {
                graph.get(parseInt(edge[0])).add(parseInt(edge[1]));
            } else {
                graph.put(parseInt(edge[0]), new ArrayList<>());
                graph.get(parseInt(edge[0])).add(parseInt(edge[1]));
            }
            if (graph.containsKey(parseInt(edge[1]))) {
                graph.get(parseInt(edge[1])).add(parseInt(edge[0]));
            } else {
                graph.put(parseInt(edge[1]), new ArrayList<>());
                graph.get(parseInt(edge[1])).add(parseInt(edge[0]));
            }
        }
        Map<Integer, Integer> studentsByGroup = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (Integer i:
             graph.keySet()) {
            groupBy(graph, studentsByGroup, i, studentsByGroup.getOrDefault(i, 1), result);
        }
        System.out.println(result.isEmpty() ? "YES" : "NO");
    }

    private static void groupBy(
            Map<Integer, List<Integer>> graph,
            Map<Integer, Integer> studentsByGroup,
            Integer currentStudent,
            Integer currentGroup,
            StringBuilder result
    ) {
        if (!studentsByGroup.containsKey(currentStudent)) {
            studentsByGroup.put(currentStudent, currentGroup);
        }
        for (Integer i :
                graph.getOrDefault(currentStudent, Collections.emptyList())) {
            if (!studentsByGroup.containsKey(i)) {
                groupBy(graph, studentsByGroup, i, revertGroup(currentGroup), result);
            } else if (!revertGroup(currentGroup).equals(studentsByGroup.get(i))) {
                result.append("NO");
            }
        }
        // System.out.println(studentsByGroup);
    }

    private static Integer revertGroup(Integer currentGroup) {
        return currentGroup == 1 ? 2 : 1;
    }
}
