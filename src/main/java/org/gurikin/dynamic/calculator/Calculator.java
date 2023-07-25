package org.gurikin.dynamic.calculator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class Calculator {
    private static final List<List<Function<Integer, Integer>>> FUNCTION_LIST = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int target = sc.nextInt();
        init();
        LinkedList<Integer> result = minCalculations(target);
        Set<Integer> resultSet = new TreeSet<>(result);
        System.out.println(resultSet.size() - 1);
        resultSet.forEach(i -> System.out.print(i + " "));
    }

    @SuppressWarnings("Duplicates")
    private static void init() {
        FUNCTION_LIST.add(List.of(Calculator::sum, Calculator::sum, Calculator::sum));
        FUNCTION_LIST.add(List.of(Calculator::sum, Calculator::sum, Calculator::multi2));
        FUNCTION_LIST.add(List.of(Calculator::sum, Calculator::sum, Calculator::multi3));
        FUNCTION_LIST.add(List.of(Calculator::sum, Calculator::multi2, Calculator::sum));
        FUNCTION_LIST.add(List.of(Calculator::sum, Calculator::multi2, Calculator::multi2));
        FUNCTION_LIST.add(List.of(Calculator::sum, Calculator::multi2, Calculator::multi3));
        FUNCTION_LIST.add(List.of(Calculator::sum, Calculator::multi3, Calculator::sum));
        FUNCTION_LIST.add(List.of(Calculator::sum, Calculator::multi3, Calculator::multi2));
        FUNCTION_LIST.add(List.of(Calculator::sum, Calculator::multi3, Calculator::multi3));
        FUNCTION_LIST.add(List.of(Calculator::multi2, Calculator::sum, Calculator::sum));
        FUNCTION_LIST.add(List.of(Calculator::multi2, Calculator::sum, Calculator::multi2));
        FUNCTION_LIST.add(List.of(Calculator::multi2, Calculator::sum, Calculator::multi3));
        FUNCTION_LIST.add(List.of(Calculator::multi2, Calculator::multi2, Calculator::sum));
        FUNCTION_LIST.add(List.of(Calculator::multi2, Calculator::multi2, Calculator::multi2));
        FUNCTION_LIST.add(List.of(Calculator::multi2, Calculator::multi2, Calculator::multi3));
        FUNCTION_LIST.add(List.of(Calculator::multi2, Calculator::multi3, Calculator::sum));
        FUNCTION_LIST.add(List.of(Calculator::multi2, Calculator::multi3, Calculator::multi2));
        FUNCTION_LIST.add(List.of(Calculator::multi2, Calculator::multi3, Calculator::multi3));
        FUNCTION_LIST.add(List.of(Calculator::multi3, Calculator::sum, Calculator::sum));
        FUNCTION_LIST.add(List.of(Calculator::multi3, Calculator::sum, Calculator::multi2));
        FUNCTION_LIST.add(List.of(Calculator::multi3, Calculator::sum, Calculator::multi3));
        FUNCTION_LIST.add(List.of(Calculator::multi3, Calculator::multi2, Calculator::sum));
        FUNCTION_LIST.add(List.of(Calculator::multi3, Calculator::multi2, Calculator::multi2));
        FUNCTION_LIST.add(List.of(Calculator::multi3, Calculator::multi2, Calculator::multi3));
        FUNCTION_LIST.add(List.of(Calculator::multi3, Calculator::multi3, Calculator::sum));
        FUNCTION_LIST.add(List.of(Calculator::multi3, Calculator::multi3, Calculator::multi2));
        FUNCTION_LIST.add(List.of(Calculator::multi3, Calculator::multi3, Calculator::multi3));
    }

    private static LinkedList<Integer> minCalculations(int target) {
        Map<Integer, Integer> calculation = new LinkedHashMap<>();
        calculation.put(0, target);
        int pSum = -2;
        int pMulti2 = -1;
        int pMulti3 = 0;
        LinkedList<Integer> result = new LinkedList<>();
        result.add(target);
        while (!result.contains(1)) {
            int min = Integer.MAX_VALUE;
            int minFunctionsIndex = 0;
            int minOpsNums = 0;
            Integer prevBase = Integer.MAX_VALUE;
            for (int i = 0; i < FUNCTION_LIST.size(); i++) {
                List<Integer> list = new ArrayList<>();
                list.add(calculation.getOrDefault(0, result.getLast()));
                list.add(calculation.getOrDefault(1, result.getLast()));
                list.add(calculation.getOrDefault(2, result.getLast()));
                for (Integer j :
                        list) {
                    int currOpsNumLeft = 3;
                    List<Function<Integer, Integer>> currList = FUNCTION_LIST.get(i);
                    int currMin = j;
                    for (Function<Integer, Integer> func :
                            currList) {
                        if (currMin == 1) {
                            break;
                        }
                        currMin = func.apply(currMin);
                        currOpsNumLeft--;
                    }
                    if (currMin <= min && currOpsNumLeft >= minOpsNums) {
                        minFunctionsIndex = i;
                        min = currMin;
                        minOpsNums = currOpsNumLeft;
                        prevBase = j;
                    }
                }
            }
            while (result.getLast() < prevBase) {
                result.pollLast();
            }
            for (int i = 0; i < FUNCTION_LIST.get(minFunctionsIndex).size() - minOpsNums; i++) {
                result.add(FUNCTION_LIST.get(minFunctionsIndex).get(i).apply(result.getLast()));
                if (result.getLast() == 1) {
                    break;
                }
                calculation.put(i, result.getLast());
            }
        }
        return result;
    }

    private static Integer sum(Integer source) {
        return source - 1;
    }

    private static Integer multi2(Integer source) {
        return (source % 2) == 0 ? source / 2 : source;
    }

    private static Integer multi3(Integer source) {
        return (source % 3) == 0 ? source / 3 : source;
    }
}
