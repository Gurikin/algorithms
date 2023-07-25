package org.gurikin.queue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * push n
 * Добавить в очередь число n (значение n задается после команды). Программа должна вывести ok.
 * <p>
 * pop
 * Удалить из очереди первый элемент. Программа должна вывести его значение.
 * <p>
 * front
 * Программа должна вывести значение первого элемента, не удаляя его из очереди.
 * <p>
 * size
 * Программа должна вывести количество элементов в очереди.
 * <p>
 * clear
 * Программа должна очистить очередь и вывести ok.
 * <p>
 * exit
 * Программа должна вывести bye и завершить работу.
 */
public class CustomQueue {

    private static final String OK = "ok";
    private static final String BYE = "bye";
    private static final String ERROR = "error";
    private static final List<Integer> queue = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Long startAt = System.currentTimeMillis();
        List<String> input = Files.readAllLines(Path.of("src/main/resources/queue_input.txt"));
        Iterator<String> inputIter = input.iterator();
        String commandResult = "";
        StringBuilder sb = new StringBuilder();
        while (!commandResult.equals(BYE)) {
            String source = inputIter.next();
            commandResult = execCommand(source.split("\\s"));
            // System.out.println(commandResult);
            sb.append(commandResult).append('\n');
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("output.txt")))) {
            bw.write(sb.toString());
            bw.flush();
        } catch (RuntimeException ignored) {}
        Long endAt = System.currentTimeMillis();
        System.out.println("Time = " + (endAt - startAt));
    }


    private static String execCommand(String[] source) {
        return switch (source[0]) {
            case "pop" -> pop(null);
            case "front" -> front(null);
            case "size" -> size(null);
            case "clear" -> clear(null);
            case "exit" -> BYE;
            default -> pushN(Integer.parseInt(source[1]));
        };
    }

    private static String pushN(Integer n) {
        queue.add(n);
        return OK;
    }

    private static String pop(Integer n) {
        if (queue.isEmpty()) {
            return ERROR;
        }
        return queue.remove(0).toString();
    }

    private static String front(Integer n) {
        if (queue.isEmpty()) {
            return ERROR;
        }
        return queue.get(0).toString();
    }

    private static String size(Integer n) {
        return String.valueOf(queue.size());
    }

    private static String clear(Integer n) {
        queue.clear();
        return OK;
    }
}
