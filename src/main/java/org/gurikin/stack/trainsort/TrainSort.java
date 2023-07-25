package org.gurikin.stack.trainsort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import static java.util.stream.Collectors.toCollection;

public class TrainSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer wagonsNum = Integer.parseInt(sc.nextLine());
        String wagonsString = sc.nextLine();
        LinkedList<Integer> wagonsChain =
                Arrays.stream(wagonsString.split("\\s"))
                        .map(Integer::parseInt)
                        .collect(toCollection(LinkedList::new));
        System.out.println(sortWagons(wagonsNum, wagonsChain) ? "YES" : "NO");
    }

    private static boolean sortWagons(Integer wagonsNum, LinkedList<Integer> wagonsChain) {
        LinkedList<Integer> deadEndStack = new LinkedList<>();
        LinkedList<Integer> way2List = new LinkedList<>();
        int lastWagonPulledToDeadEnd = 0;
        int lastWagonPulledToWay2 = 0;
        // Проверяем, можно ли передвинуть вагон в тупик. Если да, то передвигаем, если нет, то
        // Проверяем, можно ли передвинуть вагон на путь 2. Если да, то передвигаем, если нет, то заканчиваем неудачей.
        while (lastWagonPulledToDeadEnd != -1 || lastWagonPulledToWay2 != -1) {
            lastWagonPulledToDeadEnd = moveToDeadEnd(deadEndStack, wagonsChain);
            lastWagonPulledToWay2 = moveToWay2(deadEndStack, way2List);
        }
        return way2List.size() == wagonsNum;
    }

    private static int moveToDeadEnd(LinkedList<Integer> deadEndStack, LinkedList<Integer> wagonsChain) {
        if (!wagonsChain.isEmpty() && wagonsChain.getFirst() <= (deadEndStack.isEmpty() ? 101 : deadEndStack.getFirst())) {
            deadEndStack.push(wagonsChain.pop());
            return deadEndStack.getFirst();
        } else {
            return -1;
        }
    }

    private static int moveToWay2(LinkedList<Integer> deadEndStack, LinkedList<Integer> way2List) {
        if ((way2List.isEmpty() && deadEndStack.getFirst() != 1) || deadEndStack.isEmpty()) {
            return -1;
        }

        int currWagonNum = deadEndStack.getFirst();
        int prevWagonNum = way2List.isEmpty() ? 0 : way2List.getFirst();

        if (currWagonNum > prevWagonNum && (currWagonNum - prevWagonNum) == 1) {
            way2List.push(currWagonNum);
            deadEndStack.removeFirst();
            return way2List.getFirst();
        }
        return -1;
    }
}
