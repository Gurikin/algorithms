package org.gurikin.stack.polskyexpression;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

public class PolskyExpression {
    private static final Map<String, BiFunction<Integer, Integer, Integer>> expressions = Map.of("+",
                                                                                                 PolskyExpression::sum,
                                                                                                 "-",
                                                                                                 PolskyExpression::subtract,
                                                                                                 "*",
                                                                                                 PolskyExpression::multi,
                                                                                                 "/",
                                                                                                 PolskyExpression::divide);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String source = sc.nextLine();
        System.out.println(calcExpression(source.split(" ")));
    }

    private static int calcExpression(String[] source) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String currElement:
             source) {
            if (isExpression(currElement) && stack.size() > 1) {
                Integer right = stack.pop();
                Integer left = stack.pop();
                stack.push(expressions.get(currElement).apply(left, right));
            } else if (isExpression(currElement) && stack.size() == 1) {
                Integer left = 0;
                Integer right = stack.pop();
                stack.push(expressions.get(currElement).apply(left, right));
            }
            if (isDigital(currElement)) {
                stack.push(Integer.parseInt(currElement));
            }
        }
        return stack.pop();
    }

    private static boolean isDigital(String source) {
        try {
            Integer.parseInt(source);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    private static boolean isExpression(String source) {
        return expressions.containsKey(source);
    }

    private static Integer sum(int left, int right) {
        return left + right;
    }

    private static Integer subtract(int left, int right) {
        return left - right;
    }

    private static Integer multi(int left, int right) {
        return left * right;
    }

    private static Integer divide(int left, int right) {
        return left / right;
    }
}
