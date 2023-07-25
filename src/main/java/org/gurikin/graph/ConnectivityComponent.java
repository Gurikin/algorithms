package org.gurikin.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Integer.parseInt;

public class ConnectivityComponent {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/connectivity_component_input.txt"));
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
        Set<Integer> visited = new TreeSet<>();
        connectivityComponent(graph, visited, 1);
        System.out.println(visited.size());
        visited.forEach(i -> System.out.print(i + " "));
    }

    private static void connectivityComponent(Map<Integer, List<Integer>> graph, Set<Integer> visited, Integer now) {
        visited.add(now);
        for (Integer i:
                graph.getOrDefault(now, Collections.emptyList())) {
            if (!visited.contains(i)) {
                connectivityComponent(graph, visited, i);
            }
        }
    }
}
