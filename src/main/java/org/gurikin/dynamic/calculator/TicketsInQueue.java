package org.gurikin.dynamic.calculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * dp[i] = min(sp[i-1] + A[i], dp[i-2] + B[i-1], dp[i-3] + C[i-2])
 * //dp[1] = A[1]
 * //dp[2] = min(A[1] + A[2], B[1])
 * //dp[3] = min(A[1] + A[2] + A[3], B[1]+A[3], A[1]+B[3], C[1])
 * dp[0] = 0
 * dp[-1] = 0
 * dp[-2] = 0
 * ABC[-2,-1,0]=Integer.MAX_VALUE
 * <p>
 * Формат ввода
 * На вход программы поступает сначала число N — количество покупателей в очереди (1 ≤ N ≤ 5000).
 * Далее идет N троек натуральных чисел Ai, Bi, Ci.
 * Каждое из этих чисел не превышает 3600.
 * Люди в очереди нумеруются, начиная от кассы.
 * </p>
 * <p>
 * Формат вывода
 * Требуется вывести одно число — минимальное время в секундах, за которое могли быть обслужены все покупатели.
 * </p>
 * <p>
 * input
 * 5
 * 5 10 15
 * 2 10 15
 * 5 5 5
 * 20 20 1
 * 20 1 1
 * output
 * 12
 * </p>
 */
public class TicketsInQueue {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/tickets_input.txt"));
        List<Map<String, Integer>> timeToByeTicket = new ArrayList<>();
        prepareTimeToTicket(input, timeToByeTicket);
        // System.out.println(timeToByeTicket);

        List<Integer> result = new ArrayList<>();
        prepareResult(result);
        calculateTimeToTickets(result, timeToByeTicket);
        System.out.println(result.get(result.size() - 1));
    }

    /**
     * dp[i] = min(dp[i-1] + A[i], dp[i-2] + B[i-1], dp[i-3] + C[i-2])
     */
    private static void calculateTimeToTickets(List<Integer> result, List<Map<String, Integer>> timeToByeTicket) {
        for (int i = 3; i < timeToByeTicket.size(); i++) {
            int min;
            min = Math.min(result.get(i - 1) + timeToByeTicket.get(i).get("a"),
                           result.get(i - 2) + timeToByeTicket.get(i - 1).get("b"));
            min = Math.min(min, result.get(i - 3) + timeToByeTicket.get(i - 2).get("c"));
            result.add(min);
        }
    }

    private static void prepareTimeToTicket(List<String> input, List<Map<String, Integer>> timeToByeTicket) {
        timeToByeTicket.add(Map.of("a", Integer.MAX_VALUE, "b", Integer.MAX_VALUE, "c", Integer.MAX_VALUE));
        timeToByeTicket.add(Map.of("a", Integer.MAX_VALUE, "b", Integer.MAX_VALUE, "c", Integer.MAX_VALUE));
        timeToByeTicket.add(Map.of("a", Integer.MAX_VALUE, "b", Integer.MAX_VALUE, "c", Integer.MAX_VALUE));
        for (int i = 1; i < input.size(); i++) {
            String[] inputLine = input.get(i).split(" ");
            timeToByeTicket.add(Map.of("a", parseInt(inputLine[0]), "b", parseInt(inputLine[1]), "c", parseInt(inputLine[2])));
        }
    }

    private static void prepareResult(List<Integer> result) {
        result.add(0);
        result.add(0);
        result.add(0);
    }
}
