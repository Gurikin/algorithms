package org.gurikin.stack.parentness;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Bracket {

    /**
     * <pre>
     *     Рассмотрим последовательность, состоящую из круглых, квадратных и фигурных скобок.
     *     Программа должна определить, является ли данная скобочная последовательность правильной.
     *     Пустая последовательность является правильной.
     *     Если A – правильная, то последовательности (A), [A], {A} – правильные. Если A и B – правильные последовательности, то последовательность AB – правильная.
     *     Формат ввода
     *     В единственной строке записана скобочная последовательность, содержащая не более 100000 скобок.
     *     Формат вывода
     *     Если данная последовательность правильная, то программа должна вывести строку yes, иначе строку no.
     * </pre>
     * <pre>
     *     Решаем с помощью стека.
     *     Создаем мапу вида '('->')', '['->']', '{'->'}'
     *     Создаем стек как LinkedList
     *     Создаем lastBracket = null
     *     Проходим циклом по массиву characters из строки входа
     *     Если текущий элемент является открытой скобкой - добавляем в стек элемент этот элемент
     *     Иначе, если текущий элемент равен полученному из мапы элементу по ключу lastBracket -
     *       удаляем последний элемент стека и устанавливаем lastBracket в след. элемент стека.
     *     Иначе возвращаем false
     * </pre>
     *
     * @param args В единственной строке записана скобочная последовательность, содержащая не более 100000 скобок.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String source = sc.next();
        System.out.println(checkBrackets(source) ? "yes" : "no");
    }

    private static boolean checkBrackets(String source) {
        Map<Character, Character> pairBrackets = Map.of('(', ')', '[', ']', '{', '}');
        LinkedList<Character> stack = new LinkedList<>();
        Character lastBracket = null;
        for (Character c :
                source.toCharArray()) {
            if (pairBrackets.containsKey(c)) {
                stack.push(c);
                lastBracket = c;
            } else if (pairBrackets.get(lastBracket).equals(c)) {
                stack.removeFirst();
                lastBracket = (stack.isEmpty()) ? Character.MIN_VALUE : stack.getFirst();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
