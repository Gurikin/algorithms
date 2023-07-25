package org.gurikin.queue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DrunkGame {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/drunk_input.txt"));

        Queue<Integer> first = new LinkedList<>();
        Queue<Integer> second = new LinkedList<>();

        String[] firstArr = input.get(0).split(" ");
        String[] secondArr = input.get(1).split(" ");
        for (int i = 0; i < firstArr.length; i++) {
            first.add(Integer.parseInt(firstArr[i]));
            second.add(Integer.parseInt(secondArr[i]));
        }

        int stepCnt = 0;
        while (stepCnt++ != 1000000 && !first.isEmpty() && !second.isEmpty()) {
            oneGameStep(first, second);
        }
        System.out.println(getWinner(first, second, stepCnt - 1));
    }

    private static void oneGameStep(Queue<Integer> first, Queue<Integer> second) {
        if (first.isEmpty() || second.isEmpty()) {
            return;
        }
        Integer firstCard = first.poll();
        Integer secondCard = second.poll();
        if ((secondCard == 0 && firstCard == 9)) {
            catchLoot(second, firstCard, secondCard);
            return;
        } else if (firstCard == 0 && secondCard == 9) {
            catchLoot(first, firstCard, secondCard);
            return;
        }
        if (firstCard > secondCard) {
            catchLoot(first, firstCard, secondCard);
            return;
        }
        catchLoot(second, firstCard, secondCard);
    }

    private static void catchLoot(Queue<Integer> gamer, Integer firstCard, Integer secondCard) {
        gamer.add(firstCard);
        gamer.add(secondCard);
    }

    private static String getWinner(Queue<Integer> first, Queue<Integer> second, int stepCnt) {
        if (first.isEmpty()) {
            return "second " + stepCnt;
        }
        if (second.isEmpty()) {
            return "first " + stepCnt;
        }
        return "botva";
    }
}
