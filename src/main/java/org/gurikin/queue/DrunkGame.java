package org.gurikin.queue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DrunkGame {
    /**
     * <pre>
     * В игре в пьяницу карточная колода раздается поровну двум игрокам.
     * Далее они вскрывают по одной верхней карте, и тот, чья карта старше,
     *   забирает себе обе вскрытые карты, которые кладутся под низ его колоды.
     * Тот, кто остается без карт – проигрывает. Для простоты будем считать,
     *   что все карты различны по номиналу, а также, что самая младшая карта
     *   побеждает самую старшую карту ("шестерка берет туза").
     * Игрок, который забирает себе карты, сначала кладет под низ своей колоды карту
     *   первого игрока, затем карту второго игрока
     *   (то есть карта второго игрока оказывается внизу колоды).
     * Напишите программу, которая моделирует игру в пьяницу и определяет, кто выигрывает.
     * В игре участвует 10 карт, имеющих значения от 0 до 9, большая карта побеждает меньшую,
     * карта со значением 0 побеждает карту 9.
     *
     * Формат ввода
     * Программа получает на вход две строки: первая строка содержит 5 чисел,
     *   разделенных пробелами — номера карт первого игрока,
     *   вторая – аналогично 5 карт второго игрока.
     * Карты перечислены сверху вниз, то есть каждая строка начинается с той карты,
     *   которая будет открыта первой.
     *
     * Формат вывода
     * Программа должна определить, кто выигрывает при данной раздаче, и вывести слово first или second, после чего вывести количество ходов, сделанных до выигрыша. Если на протяжении 106 ходов игра не заканчивается, программа должна вывести слово botva.
     * </pre>
     * @param args Программа получает на вход две строки: первая строка содержит 5 чисел, разделенных пробелами — номера карт первого игрока, вторая – аналогично 5 карт второго игрока. Карты перечислены сверху вниз, то есть каждая строка начинается с той карты, которая будет открыта первой.
     * @throws IOException Если не нашелся файл с входными данными
     */
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
