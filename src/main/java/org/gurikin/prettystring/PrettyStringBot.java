package org.gurikin.prettystring;

import java.util.Scanner;

public class PrettyStringBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        String s = scanner.next();
        int[] cnt = new int[26];
        int maxCnt = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
            maxCnt = Math.max(maxCnt, cnt[s.charAt(i) - 'a']);
        }
        int ans = Math.min(n, maxCnt + k);
        System.out.println(ans);
    }
}

