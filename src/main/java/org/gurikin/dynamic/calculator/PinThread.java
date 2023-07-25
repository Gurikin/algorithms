package org.gurikin.dynamic.calculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * В дощечке в один ряд вбиты гвоздики. Любые два гвоздика можно соединить ниточкой.
 * Требуется соединить некоторые пары гвоздиков ниточками так, чтобы к каждому гвоздику
 * была привязана хотя бы одна ниточка, а суммарная длина всех ниточек была минимальна.
 * </p>
 * <p>
 * Формат ввода
 * В первой строке входных данных записано число N — количество гвоздиков (2 ≤ N ≤ 100).
 * В следующей строке заданы N чисел — координаты всех гвоздиков (неотрицательные целые числа, не превосходящие 10000).
 * </p>
 * <p>
 * Формат вывода
 * Выведите единственное число — минимальную суммарную длину всех ниточек.
 * </p>
 * Пример
 * <pre>
 *     Ввод
 * 6
 * 3 13 12 4 14 6
 * </pre>
 * <pre>
 *     Вывод
 * 5
 * </pre>
 */
public class PinThread {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/main/resources/pin_input.txt"));
        List<Integer> pinCoords = new ArrayList<>();
        for (String s :
                input.get(1).split(" ")) {
            pinCoords.add(Integer.parseInt(s));
        }
        pinCoords.sort(Integer::compareTo);
        System.out.println(maxThreadLength(pinCoords));
    }

    /**
     * <pre>
     * threadLefts.add(0);
     * threadRights.add(Integer.MAX_VALUE);
     * если pinCoords[i + 1] НЕ существует и
     *   (pinCoords[i] - pinCoords[i - 1] <= pinCoords[i + 1] - pinCoords[i]) - привязываем нитку
     *     от текущего гвоздя к предыдущему.
     *     (threadLefts.set(i, pinCoords[i] - pinCoords[i - 1])
     * иначе, если pinCoords[i + 1] не существует не делаем ничего
     * иначе, если pinCoords[i + 1] существует (threadRights.set(i, Integer.MAX_VALUE)
     * </pre>
     */
    private static Integer maxThreadLength(List<Integer> pinCoords) {
        // pinCoords.forEach(p -> System.out.printf("%15d", p));
        // System.out.println();

        if (pinCoords.size() == 2) {
            return pinCoords.get(1) - pinCoords.get(0);
        }

        List<Integer> threadLefts = new ArrayList<>();
        threadLefts.add(pinCoords.get(1) - pinCoords.get(0));
        threadLefts.add(pinCoords.get(2) - pinCoords.get(0));

        for (int i = 3; i < pinCoords.size(); i++) {
            threadLefts.add(Math.min(threadLefts.get(i - 2), threadLefts.get(i - 3)) + pinCoords.get(i) - pinCoords.get(i - 1));
        }
        // threadLefts.forEach(p -> System.out.printf("%15d", p));
        // System.out.println();

        return threadLefts.get(threadLefts.size() - 1);
    }
}
