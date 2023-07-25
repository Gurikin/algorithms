package org.gurikin.stack.lineland;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LineLand {
    public static void main(String[] args) throws IOException {
        Long startAt = System.currentTimeMillis();
        List<String> input = Files.readAllLines(Path.of("src/main/resources/lineland_input.txt"));
        Integer citiesNum = Integer.valueOf(input.get(0));
        String citiesLifeCostInfoString = input.get(1);
        Long endAt = System.currentTimeMillis();
        // System.out.println("Time = " + (endAt - startAt));
        startAt = System.currentTimeMillis();
        System.out.println(fillLineLandGreatEscape(citiesNum, citiesLifeCostInfoString.split("\\s")));
        endAt = System.currentTimeMillis();
        // System.out.println("Time = " + (endAt - startAt));
    }

    private static String fillLineLandGreatEscape(Integer citiesNum, String[] citiesLifeCostInfo) {
        Map<Integer, CityCostPair> result = new HashMap<>();
        List<CityCostPair> stack = new ArrayList<>();
        for (int i = 0; i < citiesNum; i++) {
            result.putIfAbsent(i, new CityCostPair().setPos(-1));
            CityCostPair currCity = new CityCostPair().setPos(i).setCost(Integer.parseInt(citiesLifeCostInfo[i]));
            if (stack.isEmpty()) {
                stack.add(currCity);
                continue;
            }
            CityCostPair lastStackCity = stack.get(stack.size() - 1);
            if (lastStackCity.cost() <= currCity.cost()) {
                stack.add(currCity);
            } else {
                while (!stack.isEmpty() &&
                        Optional.ofNullable(stack.get(stack.size() - 1)).orElse(new CityCostPair()).cost() > Integer.parseInt(
                                citiesLifeCostInfo[i])) {
                    result.put(stack.get(stack.size() - 1).pos(), currCity);
                    stack.remove(stack.size() - 1);
                }
                stack.add(currCity);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < citiesNum; i++) {
            sb.append(result.get(i).pos());
            if (i != citiesNum - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private static class CityCostPair {
        private int pos = -1;
        private int cost = -1;

        public int pos() {
            return pos;
        }

        public CityCostPair setPos(int pos) {
            this.pos = pos;
            return this;
        }

        public int cost() {
            return cost;
        }

        public CityCostPair setCost(int cost) {
            this.cost = cost;
            return this;
        }

        @Override
        public String toString() {
            return "{" + "pos=" + pos + ", cost=" + cost + '}';
        }
    }
}
