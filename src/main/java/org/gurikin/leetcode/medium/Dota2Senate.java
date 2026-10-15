package org.gurikin.leetcode.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Dota2Senate {
    /**
     * My solution (about 30ms)
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        if (!senate.contains("R")) {
            return "Dire";
        }
        if (!senate.contains("D")) {
            return "Radiant";
        }
        var mapToDel = new HashMap<Character, Integer>();
        var queue = new LinkedList<Character>();
        senate
                .chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> {
                    queue.add(c);
                });
        var pnt = 0;
        while (pnt++ <= senate.length() * 100 / 50) {
            var currentSenator = queue.pollFirst();
            if (mapToDel.containsKey(currentSenator) && mapToDel.get(currentSenator) > 0) {
                mapToDel.put(currentSenator, mapToDel.get(currentSenator) - 1);
                continue;
            }
            queue.addLast(currentSenator);
            var objToDelete = (currentSenator == 'R') ? 'D' : 'R';
            mapToDel.put(objToDelete, mapToDel.getOrDefault(objToDelete, 0) + 1);
        }
        return (queue.pollFirst() == 'R') ? "Radiant" : "Dire";
    }

    /**
     * One of the best solutions (about 11ms)
     *
     * @param senate
     * @return
     */
    public String predictPartyVictoryBest(String senate) {
        int n = senate.length();
        char[] chars = senate.toCharArray();
        Queue<Integer> radiants = new LinkedList<>();
        Queue<Integer> dires = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'R') {
                radiants.add(i);
            } else {
                dires.add(i);
            }
        }

        while (!radiants.isEmpty() && !dires.isEmpty()) {
            int rIndex = radiants.poll();
            int dIndex = dires.poll();
            if (rIndex < dIndex) {
                radiants.add(rIndex + n);
            } else {
                dires.add(dIndex + n);
            }
        }
        return radiants.isEmpty() ? "Dire" : "Radiant";
    }
}
