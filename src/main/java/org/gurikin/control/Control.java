package org.gurikin.control;

import java.util.Scanner;

public class Control {
    /**
     * Петя и Вася — одноклассники и лучшие друзья, поэтому они во всём помогают друг другу. Завтра у них контрольная по математике, и учитель подготовил целых K вариантов заданий.
     * <p>
     * В классе стоит один ряд парт, за каждой из них (кроме, возможно, последней) на контрольной будут сидеть ровно два ученика. Ученики знают, что варианты будут раздаваться строго по порядку: правый относительно учителя ученик первой парты получит вариант 1, левый — вариант 2, правый ученик второй парты получит вариант 3 (если число вариантов больше двух) и т.д. Так как K может быть меньше чем число учеников N, то после варианта K снова выдаётся вариант 1. На последней парте в случае нечётного числа учеников используется только место 1.
     * <p>
     * Петя самым первым вошёл в класс и сел на своё любимое место. Вася вошёл следом и хочет получить такой же вариант, что и Петя, при этом сидя к нему как можно ближе. То есть между ними должно оказаться как можно меньше парт, а при наличии двух таких мест с равным расстоянием от Пети Вася сядет позади Пети, а не перед ним. Напишите программу, которая подскажет Васе, какой ряд и какое место (справа или слева от учителя) ему следует выбрать. Если же один и тот же вариант Вася с Петей писать не смогут, то выдайте одно число -1.
     * <p>
     * Формат ввода
     * В первой строке входных данных находится количество учеников в классе 2≤N≤109. Во второй строке — количество подготовленных для контрольной вариантов заданий 2≤K≤N. В третьей строке — номер ряда, на который уже сел Петя, в четвёртой — цифра 1, если он сел на правое место, и 2, если на левое.
     * <p>
     * Формат вывода
     * Если Вася никак не сможет писать тот же вариант, что и Петя, то выведите -1. Если решение существует, то выведите два числа — номер ряда, на который следует сесть Васе, и 1, если ему надо сесть на правое место, или 2, если на левое. Разрешается использовать только первые N мест в порядке раздачи вариантов.
     */
    public static void main(String[] args) {
        int resultRow = -1;
        int resultRightLeft = -1;
        int currentVariant = -1;

        Scanner sc = new Scanner(System.in);
        int studentsNum = sc.nextInt();
        int variantsNum = sc.nextInt();
        int selectedRowNum = sc.nextInt();
        int rightLeft = sc.nextInt();

        int currentSit = ((selectedRowNum - 1) * 2) + rightLeft;
        if (currentSit < variantsNum) {
            currentVariant = currentSit;
        } else if ((currentSit % variantsNum) == 0) {
            currentVariant = variantsNum;
        } else {
            currentVariant = currentSit % variantsNum;
        }
        if ((studentsNum - currentVariant) < variantsNum) {
            System.out.println(resultRow);
        } else {
            int forwardResultRow = calcPossibleResultRow(currentSit, variantsNum, studentsNum, true);
            int backwardResultRow = calcPossibleResultRow(currentSit, variantsNum, studentsNum, false);

            if (forwardResultRow == -1) {
                System.out.println(backwardResultRow + " " + calcResultSide(calcResultSit(currentSit, variantsNum, false)));
                return;
            }

            if (backwardResultRow == -1) {
                System.out.println(forwardResultRow + " " + calcResultSide(calcResultSit(currentSit, variantsNum, true)));
                return;
            }

            if (isForward(forwardResultRow, backwardResultRow, selectedRowNum)) {
                System.out.println(forwardResultRow + " " + calcResultSide(calcResultSit(currentSit, variantsNum, true)));
            } else {
                System.out.println(backwardResultRow + " " + calcResultSide(calcResultSit(currentSit, variantsNum, false)));
            }
        }
    }

    private static int calcResultSit(int currentSit, int variantsNum, boolean isForward) {
        if (isForward) {
            return currentSit - variantsNum;
        }
        return currentSit + variantsNum;
    }

    private static int calcPossibleResultRow(int currentSit, int variantsNum, int studentsNum, boolean isForward) {
        int forwardResultRow = (currentSit - variantsNum) / 2 + (currentSit - variantsNum) % 2;
        int backwardResultRow = (currentSit + variantsNum) / 2 + (currentSit + variantsNum) % 2;

        if (isForward && forwardResultRow < 1) {
            return -1;
        }

        if (!isForward && (currentSit + variantsNum) > studentsNum) {
            return -1;
        }

        return isForward ? forwardResultRow : backwardResultRow;
    }

    private static boolean isForward(int forwardRowNum, int backwardRowNum, int selectedRowNum) {
        return (selectedRowNum - forwardRowNum) < (backwardRowNum - selectedRowNum);
    }

    private static int calcResultSide(int resultSit) {
        return (resultSit % 2 == 0) ? 2 : 1;
    }
}
