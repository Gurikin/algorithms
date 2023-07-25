package org.gurikin.prettystring;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PrettyStringOld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int changeOpNum = sc.nextInt();
        String source = sc.next();
        System.out.println(maxPretty(changeOpNum, source));
    }

    private static int maxPretty(int changeOpNum, String source) {
        int maxPretty = 0;
        if (source.length() <= changeOpNum) {
            return source.length();
        }
        Map<Byte, PrettyByteInfo> map = new HashMap<>();
        byte[] sb = source.getBytes();
        for (int i = 0; i < sb.length; i++) {
            putLeft(map, sb[i], i);
        }
        for (Map.Entry<Byte, PrettyByteInfo> e :
                map.entrySet()) {
            PrettyByteInfo prettyByteInfo = e.getValue();
            int currentPretty = 0;
            int i = 0;
            int prevPos = 0;
            int prevPretty = 0;
            for (Map.Entry<Integer, Integer> prettyEntry :
                    prettyByteInfo.prettyChain().entrySet()) {
                if (prevPos + currentPretty == prettyEntry.getKey() && prevPretty != 0) {
                    currentPretty = prevPretty + prettyEntry.getValue();
                } else if (prevPos + currentPretty > prettyEntry.getKey()) {
                    currentPretty = prevPretty + prettyEntry.getValue() - (prevPos + currentPretty - prettyEntry.getKey());
                } else {
                    currentPretty = prettyEntry.getValue() + changeOpNum;
                    prevPretty = currentPretty;
                }
                prevPos = prettyEntry.getKey();
                maxPretty = Math.max(maxPretty, currentPretty);
            }
        }
        return maxPretty == 0 ? changeOpNum + 1 : maxPretty;
    }

    private static void putLeft(Map<Byte, PrettyByteInfo> map, byte currLeftByte, int leftPos) {
        if (!checkIfExists(map, currLeftByte)) {
            map.put(currLeftByte, createPrettyByteInfo(leftPos));
            return;
        }
        updatePrettyInfoLeft(map.get(currLeftByte), leftPos, map.get(currLeftByte).firstLeftPosition());
    }

    private static boolean checkIfExists(Map<Byte, PrettyByteInfo> map, byte currLeftByte) {
        return map.containsKey(currLeftByte);
    }

    private static PrettyByteInfo createPrettyByteInfo(int pos) {
        PrettyByteInfo prettyByteInfo = new PrettyByteInfo();
        addChain(prettyByteInfo, 1, pos);
        prettyByteInfo.lastLeftPosition(pos);
        prettyByteInfo.firstLeftPosition(pos);
        return prettyByteInfo;
    }

    private static void updatePrettyInfoLeft(PrettyByteInfo prettyByteInfo, int currLeftPos, int firstChainPos) {
        if ((currLeftPos - prettyByteInfo.lastLeftPosition()) == 1) {
            addChain(prettyByteInfo, 1, firstChainPos);
            prettyByteInfo.lastLeftPosition(currLeftPos);
            prettyByteInfo.firstLeftPosition(firstChainPos);
            return;
        }
        addChain(prettyByteInfo, 1, currLeftPos);
        prettyByteInfo.lastLeftPosition(currLeftPos);
        prettyByteInfo.firstLeftPosition(currLeftPos);
    }

    private static void addChain(PrettyByteInfo prettyByteInfo, int prettyElement, int pos) {
        prettyByteInfo.prettyChain().put(pos, prettyByteInfo.prettyChain().getOrDefault(pos, 0) + prettyElement);
    }

    @Getter
    @Setter
    @Accessors(fluent = true, chain = true)
    private static class PrettyByteInfo {
        private Map<Integer, Integer> prettyChain = new HashMap<>();
        private Map<Integer, Integer> changeChain = new HashMap<>();
        private int lastLeftPosition;
        private int firstLeftPosition;

        // public Map<Integer, Integer> prettyChain() {
        //     return prettyChain;
        // }
        //
        // public PrettyByteInfo prettyChain(Map<Integer, Integer> prettyChain) {
        //     this.prettyChain = prettyChain;
        //     return this;
        // }
        //
        // public Map<Integer, Integer> changeChain() {
        //     return changeChain;
        // }
        //
        // public PrettyByteInfo changeChain(Map<Integer, Integer> changeChain) {
        //     this.changeChain = changeChain;
        //     return this;
        // }
        //
        // public int lastLeftPosition() {
        //     return lastLeftPosition;
        // }
        //
        // public PrettyByteInfo lastLeftPosition(int lastLeftPosition) {
        //     this.lastLeftPosition = lastLeftPosition;
        //     return this;
        // }
        //
        // public int firstLeftPosition() {
        //     return firstLeftPosition;
        // }
        //
        // public PrettyByteInfo firstLeftPosition(int firstLeftPosition) {
        //     this.firstLeftPosition = firstLeftPosition;
        //     return this;
        // }
    }
}
