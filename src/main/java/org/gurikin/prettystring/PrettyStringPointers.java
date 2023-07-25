package org.gurikin.prettystring;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrettyStringPointers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int changeOpNum = sc.nextInt();
        String source = sc.next();
        System.out.println(maxPrettyN(changeOpNum, source));
    }

    private static int maxPrettyN(int changeOpNum, String source) {
        if (source.length() <= changeOpNum + 1) {
            return source.length();
        }
        byte[] sb = source.getBytes();
        Map<Byte, List<ChainElement>> chainInfo = new LinkedHashMap<>();
        int lp = 0;
        int rp = 0;
        while (rp < sb.length && lp < sb.length - changeOpNum) {
            if ((rp - changeOpNum) > lp) {
                lp++;
                continue;
            }
            if (rp != 0 && sb[rp] == sb[rp - 1]) {
                incrementChainInfo(chainInfo.get(sb[rp]), rp);
            }
            if (rp == 0 || sb[rp] != sb[rp - 1]) {
                insertChainInfo(chainInfo, sb[rp], rp);
            }
            rp++;
        }
        return calcMaxPretty(chainInfo, sb, changeOpNum);
    }

    private static int calcMaxPretty(
            Map<Byte, List<ChainElement>> chainInfo,
            byte[] sb,
            int changeOpNum
    ) {
        int maxPretty = 0;
        for (Map.Entry<Byte, List<ChainElement>> e :
                chainInfo.entrySet()) {
            System.out.println("Char := " + (char) e.getKey().intValue());
            List<ChainElement> currList = e.getValue();
            int currentSum = 0;
            int currentPenalty = 0;
            int posForChange = currList.get(0).pos() + currList.get(0).chainLength();
            int indexForChange = -1;
            int operationNumWithoutPenalty = 0;
            for (int i = 0; i < currList.size(); i++) {
                ChainElement ce = currList.get(i);
                System.out.println(ce);
                currentSum += ce.chainLength();
                if (i != 0) {
                    currentPenalty += incCurrPenalty(ce, currList, i);
                    System.out.println("Penalty := " + currentPenalty);
                }
                if (i != 0 && (ce.pos() - currentSum - (changeOpNum - currentPenalty) - 1) > (posForChange + 1)) {
                    indexForChange = i - operationNumWithoutPenalty;
                    System.out.println("indexForChange := " + indexForChange);
                    if (indexForChange < 0)
                        continue;
                    operationNumWithoutPenalty--;
                    posForChange = currList.get(indexForChange).pos;
                    System.out.println("PosForChange := " + posForChange);
                    currentSum = 0;
                    currentPenalty = 0;
                    for (int j = indexForChange + 1; j <= i; j++) {
                        currentSum += currList.get(j).chainLength();
                        currentPenalty += incCurrPenalty(currList.get(j), currList, j);
                    }
                }
                operationNumWithoutPenalty++;
                System.out.println("operationNumWithoutPenalty := " + operationNumWithoutPenalty);
                System.out.println("currentSum := " + currentSum);
            }
            maxPretty = Math.max(maxPretty, currentSum);
        }
        System.out.println(maxPretty + changeOpNum);
        return maxPretty + changeOpNum;
    }

    private static int decrCurrPenalty(List<ChainElement> currList, int j) {
        if (j - 1 < 0 || currList.get(j - 1) == null || (currList.get(j).pos() - currList.get(j - 1).pos()) <= 1) {
            return 0;
        }
        return Math.max((currList.get(j).pos() - currList.get(j).chainLength() - currList.get(j - 1).pos()), 0);
    }

    private static int incCurrPenalty(ChainElement ce, List<ChainElement> currList, int i) {
        if ((i - 1) < 0) {
            return 0;
        }
        return (ce.pos() - ce.chainLength()) - currList.get(i - 1).pos();
    }

    private static void insertChainInfo(Map<Byte, List<ChainElement>> chainInfo, byte byteToUpdate, int pos) {
        if (chainInfo.containsKey(byteToUpdate)) {
            chainInfo.get(byteToUpdate).add(new ChainElement(pos, 1));
        } else {
            chainInfo.put(byteToUpdate, new ArrayList<>(List.of(new ChainElement(pos, 1))));
        }
    }

    private static void incrementChainInfo(List<ChainElement> chainInfo, int pos) {
        ChainElement ce = chainInfo.get(chainInfo.size() - 1);
        ce.pos(pos).chainLength(ce.chainLength() + 1);
    }

    private static class ChainElement {
        private int pos = 0;
        private int chainLength = 0;

        public ChainElement() {
        }

        public ChainElement(int pos, int chainLength) {
            this.pos = pos;
            this.chainLength = chainLength;
        }

        public int pos() {
            return pos;
        }

        public ChainElement pos(int pos) {
            this.pos = pos;
            return this;
        }

        public int chainLength() {
            return chainLength;
        }

        public ChainElement chainLength(int chainLength) {
            this.chainLength = chainLength;
            return this;
        }

        @Override
        public String toString() {
            return '{' + "pos=" + pos + ", chainLength=" + chainLength + '}';
        }
    }
}
