package org.gurikin.queue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * push_front n
 * Добавить (положить) в начало дека новый элемент. Программа должна вывести ok.
 * <p>
 * push_back n
 * Добавить (положить) в конец дека новый элемент. Программа должна вывести ok.
 * <p>
 * pop_front
 * Извлечь из дека первый элемент. Программа должна вывести его значение.
 * <p>
 * pop_back
 * Извлечь из дека последний элемент. Программа должна вывести его значение.
 * <p>
 * front
 * Узнать значение первого элемента (не удаляя его). Программа должна вывести его значение.
 * <p>
 * back
 * Узнать значение последнего элемента (не удаляя его). Программа должна вывести его значение.
 * <p>
 * size
 * Вывести количество элементов в деке.
 * <p>
 * clear
 * Очистить дек (удалить из него все элементы) и вывести ok.
 * <p>
 * exit
 * Программа должна вывести bye и завершить работу.
 */
public class CustomDeque {

    private static final String OK = "ok";
    private static final String BYE = "bye";
    private static final String ERROR = "error";
    private static final Deque<Integer> deque = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        // Long startAt = System.currentTimeMillis();
        List<String> input = Files.readAllLines(Path.of("src/main/resources/deque_input.txt"));
        Iterator<String> inputIter = input.iterator();
        String commandResult = "";
        StringBuilder sb = new StringBuilder();
        while (!commandResult.equals(BYE)) {
            String source = inputIter.next();
            commandResult = execCommand(source.split("\\s"));
            sb.append(commandResult).append('\n');
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("output.txt")))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (RuntimeException ignored) {
        }
        // Long endAt = System.currentTimeMillis();
        // System.out.println("Time = " + (endAt - startAt));
    }


    private static String execCommand(String[] source) {
        return switch (source[0]) {
            case "push_front" -> pushFront(Integer.parseInt(source[1]));
            case "push_back" -> pushBack(Integer.parseInt(source[1]));
            case "pop_front" -> popFront();
            case "pop_back" -> popBack();
            case "front" -> front();
            case "back" -> back();
            case "size" -> size();
            case "clear" -> clear();
            default -> BYE;
        };
    }

    private static String pushFront(Integer n) {
        deque.push(n);
        return OK;
    }

    private static String pushBack(Integer n) {
        deque.add(n);
        return OK;
    }

    private static String popFront() {
        Integer popped = deque.pollFirst();
        if (popped == null) {
            return ERROR;
        }
        return popped.toString();
    }

    private static String popBack() {
        Integer popped = deque.pollLast();
        if (popped == null) {
            return ERROR;
        }
        return popped.toString();
    }

    private static String front() {
        if (deque.isEmpty()) {
            return ERROR;
        }
        return deque.getFirst().toString();
    }

    private static String back() {
        if (deque.isEmpty()) {
            return ERROR;
        }
        return deque.getLast().toString();
    }

    private static String size() {
        return String.valueOf(deque.size());
    }

    private static String clear() {
        deque.clear();
        return OK;
    }
}
