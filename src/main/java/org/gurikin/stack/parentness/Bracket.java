package org.gurikin.stack.parentness;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Bracket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String source = sc.next();
        System.out.println(checkBrackets(source) ? "yes" : "no");
    }

    private static boolean checkBrackets(String source) {
        Map<Character, Character> pairBrackets = Map.of('(', ')', '[', ']', '{', '}');
        LinkedList<Character> stack = new LinkedList<>();
        Character lastBracket = Character.MIN_VALUE;
        for (Character c :
                source.toCharArray()) {
            if (stack.isEmpty() && pairBrackets.get(c) == null) {
                return false;
            }
            if (stack.isEmpty()) {
                stack.push(c);
                lastBracket = c;
            } else {
                Character lastStackBracket = stack.pop();
                if (checkForOpenedChain(lastStackBracket, c, pairBrackets)) {
                    stack.push(lastStackBracket);
                    stack.push(c);
                    lastBracket = c;
                } else if (!pairBrackets.get(lastBracket).equals(c)) {
                    return false;
                } else {
                    lastBracket = (stack.isEmpty()) ? Character.MIN_VALUE : stack.getFirst();
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean checkForOpenedChain(Character left, Character right, Map<Character, Character> pairBrackets) {
        return pairBrackets.containsKey(left) && pairBrackets.containsKey(right);
    }
}
