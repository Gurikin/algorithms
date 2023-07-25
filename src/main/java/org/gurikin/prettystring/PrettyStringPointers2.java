package org.gurikin.prettystring;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class PrettyStringPointers2 {
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
        Map<Byte, ChainElement> chainInfo = new LinkedHashMap<>();
        int lp = 0;
        int rp = 0;
        int maxPretty = 0;
        while (rp < sb.length && lp < sb.length - changeOpNum) {
            if ((rp - changeOpNum) > lp) {
                lp++;
                maxPretty = Math.max(maxPretty, leftIncrementChainInfo(chainInfo, sb[lp], sb, lp, rp, changeOpNum));
                System.out.println("key=" + sb[rp] + ", value=" + chainInfo.get(sb[rp]));
                continue;
            }
            if (!chainInfo.containsKey(sb[rp])) {
                maxPretty = Math.max(maxPretty, insertChainInfo(chainInfo, sb[rp], rp, changeOpNum));
            } else {
                maxPretty = Math.max(maxPretty, rightIncrementChainInfo(chainInfo, sb[rp], sb, lp, rp, changeOpNum));
            }
            System.out.println("key=" + sb[rp] + ", value=" + chainInfo.get(sb[rp]));
            rp++;
        }
        return maxPretty;
    }

    private static int insertChainInfo(Map<Byte, ChainElement> chainInfo, byte byteToUpdate, int pos, int changeOpNum) {
        chainInfo.put(byteToUpdate, new ChainElement().leftBorder(pos).rightBorder(pos).chainLength(1).changeLength(0));
        return changeOpNum + 1;
    }

    private static int rightIncrementChainInfo(Map<Byte, ChainElement> chainInfo, byte currByte, byte[] sb, int lp, int rp, int changeOpNum) {
        int adjust = (rp - lp) <= 1 ? 0 : 1;
        ChainElement chainElement = chainInfo.get(currByte);
        if (chainElement.rightBorder() < rp) {
            chainElement.rightBorder(rp);
            chainElement.chainLength(chainElement.chainLength() + 1);
        }
        chainElement.changeLength(chainElement.rightBorder() - chainElement.leftBorder() - chainElement.chainLength() + adjust);
        if (chainElement.changeLength() < 0) {
            chainElement.changeLength(0);
        }
        chainInfo.put(currByte, chainElement);
        return (chainElement.rightBorder() - chainElement.leftBorder() - 1) + chainElement.chainLength();
    }

    private static int leftIncrementChainInfo(Map<Byte, ChainElement> chainInfo, byte currByte, byte[] sb, int lp, int rp, int changeOpNum) {
        int adjust = (rp - lp) <= 1 ? 0 : 1;
        ChainElement chainElement = chainInfo.get(currByte);
        if (chainElement.leftBorder() < lp) {
            chainElement.leftBorder(lp);
            if (lp != 0 && sb[lp] != sb[lp - 1])
                chainElement.chainLength(chainElement.chainLength() - 1);
        }
        chainElement.changeLength(chainElement.rightBorder() - chainElement.leftBorder() - chainElement.chainLength() + adjust);
        if (chainElement.changeLength() < 0) {
            chainElement.changeLength(0);
        }
        chainInfo.put(currByte, chainElement);
        return (chainElement.rightBorder() - chainElement.leftBorder() - 1) + chainElement.chainLength();
    }

    private static class ChainElement {
        private int leftBorder = 0;
        private int rightBorder = 0;
        private int chainLength = 0;
        private int changeLength = 0;

        public int leftBorder() {
            return leftBorder;
        }

        public ChainElement leftBorder(int leftBorder) {
            this.leftBorder = leftBorder;
            return this;
        }

        public int rightBorder() {
            return rightBorder;
        }

        public ChainElement rightBorder(int rightBorder) {
            this.rightBorder = rightBorder;
            return this;
        }

        public int chainLength() {
            return chainLength;
        }

        public ChainElement chainLength(int chainLength) {
            this.chainLength = chainLength;
            return this;
        }

        public int changeLength() {
            return changeLength;
        }

        public ChainElement changeLength(int changeLength) {
            this.changeLength = changeLength;
            return this;
        }

        @Override
        public String toString() {
            return "ChainElement{" +
                    "leftBorder=" + leftBorder +
                    ", rightBorder=" + rightBorder +
                    ", chainLength=" + chainLength +
                    ", changeLength=" + changeLength +
                    '}';
        }
    }
}
