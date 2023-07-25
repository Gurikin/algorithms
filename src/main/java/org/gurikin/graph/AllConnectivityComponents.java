package org.gurikin.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Integer.parseInt;

public class AllConnectivityComponents {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/all_connectivity_components_input.txt"));
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
        Map<Integer, Set<Integer>> components = new HashMap<>();
        allConnectivityComponents(graph, components, v);
        printAnswer(components);
    }

    private static void allConnectivityComponents(Map<Integer, List<Integer>> graph, Map<Integer, Set<Integer>> components, Integer v) {
        Set<Integer> globalVisited = new HashSet<>();
        int comp = 1;
        for (int i = 1; i <= v; i++) {
            if (!globalVisited.contains(i)) {
                components.put(comp, new TreeSet<>());
                dfs(graph, components.get(comp), i);
                globalVisited.addAll(components.get(comp));
                comp++;
            }
        }
    }

    private static void dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, Integer now) {
        visited.add(now);
        for (Integer i:
                graph.getOrDefault(now, Collections.emptyList())) {
            if (!visited.contains(i)) {
                dfs(graph, visited, i);
            }
        }
    }

    private static void printAnswer(Map<Integer, Set<Integer>> components) {
        System.out.println(components.size());
        for (Set<Integer> component:
             components.values()) {
            System.out.println(component.size());
            component.forEach(i -> System.out.print(i + " "));
            System.out.println();
        }
    }
}
