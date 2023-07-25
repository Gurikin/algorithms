package org.gurikin.prettystring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrettyStringSketch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int changeOpNum = sc.nextInt();
        String source = sc.next();
        System.out.println(maxPretty(changeOpNum, source));
    }

    private static int maxPretty(int changeOpNum, String source) {
        if (source.length() <= changeOpNum + 1) {
            return source.length();
        }
        byte[] sb = source.getBytes();
        String dict = "abcdefghijklmnopqrstuvwxyz";
        Map<Byte, List<ChainElement>> result = new HashMap<>();
        for (byte b:
             dict.getBytes()) {
            result.put(b, new ArrayList<>());
            for (int i = 0; i < sb.length; i++) {
                if (sb[i] == b) {
                    result.get(b).add(new ChainElement().setPos(i).setPoint(1));
                } else {
                    result.get(b).add(new ChainElement().setPos(i).setPoint(-1));
                }
            }
        }
        System.out.println(result);

        int maxPretty = 0;
        for (Map.Entry<Byte, List<ChainElement>> e :
                result.entrySet()) {
            System.out.println("=====================================\t" + e.getKey());
            int currentSum = 0;
            int currentPenalty = 0;
            int lp = 0;
            for (int rp = 0; rp < e.getValue().size(); rp++) {
                ChainElement rce = e.getValue().get(rp);
                ChainElement lce = e.getValue().get(lp);
                if (rce.point() > 0) {
                    currentSum++;
                } else {
                    currentPenalty++;
                }

                if ((rp - currentSum - changeOpNum) > lp) {
                    if (e.getValue().get(lp).point > 0) {
                        currentSum++;
                    } else {
                        currentPenalty++;
                    }
                    System.out.println("cs = " + currentSum);
                    lp++;
                }
                maxPretty = Math.max(maxPretty, currentSum + changeOpNum);
            }
        }
        return maxPretty;
    }

    private static class ChainElement {
        private int pos = 0;
        private int point = 0;

        public int pos() {
            return pos;
        }

        public ChainElement setPos(int pos) {
            this.pos = pos;
            return this;
        }

        public int point() {
            return point;
        }

        public ChainElement setPoint(int point) {
            this.point = point;
            return this;
        }

        @Override
        public String toString() {
            return "ChainElement{" +
                    "pos=" + pos +
                    ", point=" + point +
                    '}';
        }
    }
}
