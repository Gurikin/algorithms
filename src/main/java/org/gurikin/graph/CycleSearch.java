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

public class CycleSearch {
    private static final Integer WHITE = 0;
    private static final Integer GREY = 1;
    private static final Integer BLACK = 2;

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/cycle_search_input.txt"));
        int v = parseInt(input.get(0).trim().split(" ")[0]);
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= v; i++) {
            String[] row = input.get(i).trim().split(" ");
            for (int j = 1; j <= v; j++) {
                if (Integer.parseInt(row[j - 1]) == 0) {
                } else if (graph.containsKey(i)) {
                    graph.get(i).add(j);
                } else {
                    graph.put(i, new ArrayList<>());
                    graph.get(i).add(j);
                }
            }
        }
        Map<Integer, Integer> vColor = new LinkedHashMap<>();
        graph.keySet().forEach(vertex -> vColor.put(vertex, WHITE));
        LinkedList<Integer> result = new LinkedList<>();
        dfs(graph, vColor, 1, result);
        // System.out.println(vColor);
        // result.forEach(i -> System.out.print(i + " "));
    }

    private static void dfs(
            Map<Integer, List<Integer>> graph,
            Map<Integer, Integer> colorV,
            Integer currentVertex,
            LinkedList<Integer> result
    ) {
        colorV.put(currentVertex, GREY);
        result.push(currentVertex);
        for (Integer i :
                graph.getOrDefault(currentVertex, Collections.emptyList())) {
            if (GREY.equals(colorV.get(i))) {
                colorV.entrySet().stream().filter(e -> e.getKey().equals(currentVertex)).forEach(System.out::println);
            }
            if (WHITE.equals(colorV.get(i))) {
                dfs(graph, colorV, i, result);
            }
        }
        colorV.put(currentVertex, BLACK);
    }
}