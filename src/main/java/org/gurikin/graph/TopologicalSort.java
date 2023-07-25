package org.gurikin.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class TopologicalSort {
    private static final Integer GREY = 1;
    private static final Integer BLACK = 2;

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/topological_sort_input.txt"));
        int v = parseInt(input.get(0).trim().split(" ")[0]);
        int e = parseInt(input.get(0).trim().split(" ")[1]);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inputEdges = new HashMap<>();
        for (int i = 1; i <= e; i++) {
            String[] edge = input.get(i).trim().split(" ");
            if (graph.containsKey(parseInt(edge[0]))) {
                graph.get(parseInt(edge[0])).add(parseInt(edge[1]));
            } else {
                graph.put(parseInt(edge[0]), new ArrayList<>());
                graph.get(parseInt(edge[0])).add(parseInt(edge[1]));
            }
            inputEdges.put(parseInt(edge[1]), inputEdges.getOrDefault(parseInt(edge[1]), 0) + 1);
        }
        Map<Integer, Integer> studentsByGroup = new HashMap<>();
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 1; i <= v; i++) {
            if (!studentsByGroup.containsKey(i)) {
                groupBy(graph, studentsByGroup, i, result);
            }
            if (studentsByGroup.containsKey(-1)) {
                break;
            }
        }
        // System.out.println(studentsByGroup);
        if (studentsByGroup.containsKey(-1)) {
            System.out.println(-1);
            return;
        }
        result.forEach(i -> System.out.print(i + " "));
    }

    private static void groupBy(
            Map<Integer, List<Integer>> graph,
            Map<Integer, Integer> studentsByGroup,
            Integer currentStudent,
            LinkedList<Integer> result
    ) {
        if (!studentsByGroup.containsKey(currentStudent)) {
            studentsByGroup.put(currentStudent, 1);
        }
        for (Integer i :
                graph.getOrDefault(currentStudent, Collections.emptyList())) {
            if (GREY.equals(studentsByGroup.get(i))) {
                studentsByGroup.put(-1, -1);
                return;
            }
            groupBy(graph, studentsByGroup, i, result);
        }
        if (GREY.equals(studentsByGroup.get(currentStudent))) {
            studentsByGroup.put(currentStudent, BLACK);
            result.push(currentStudent);
        }
    }
}
