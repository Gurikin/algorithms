package org.gurikin.stack;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * push n
 * Добавить в стек число n (значение n задается после команды). Программа должна вывести ok.
 * <p>
 * pop
 * Удалить из стека последний элемент. Программа должна вывести его значение.
 * <p>
 * back
 * Программа должна вывести значение последнего элемента, не удаляя его из стека.
 * <p>
 * size
 * Программа должна вывести количество элементов в стеке.
 * <p>
 * clear
 * Программа должна очистить стек и вывести ok.
 * <p>
 * exit
 * Программа должна вывести bye и завершить работу.
 */
public class CustomStack {

    private static final String OK = "ok";
    private static final String BYE = "bye";
    private static final String ERROR = "error";
    private static final LinkedList<Integer> stack = new LinkedList<>();
    private static final Map<String, Function<Integer, String>> commands =
            Map.of("push",
                   CustomStack::pushN,
                   "pop",
                   CustomStack::pop,
                   "back",
                   CustomStack::back,
                   "size",
                   CustomStack::size,
                   "clear",
                   CustomStack::clear,
                   "exit",
                   CustomStack::exit);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String commandResult = "";
        while (!commandResult.equals(BYE)) {
            String source = sc.nextLine();
            commandResult = execCommand(source.split("\\s"));
            System.out.println(commandResult);
        }
    }

    private static String execCommand(String[] source) {
        if (source.length > 1) {
            return commands.get(source[0]).apply(Integer.parseInt(source[1]));
        }
        return commands.get(source[0]).apply(null);
    }

    private static String pushN(Integer n) {
        stack.push(n);
        return OK;
    }

    private static String pop(Integer n) {
        if (stack.isEmpty()) {
            return ERROR;
        }
        return stack.pop().toString();
    }

    private static String back(Integer n) {
        if (stack.isEmpty()) {
            return ERROR;
        }
        return stack.getFirst().toString();
    }

    private static String size(Integer n) {
        return String.valueOf(stack.size());
    }

    private static String clear(Integer n) {
        stack.clear();
        return OK;
    }

    private static String exit(Integer n) {
        return BYE;
    }
}
