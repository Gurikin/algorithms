package org.gurikin.brackets;

public class BracketsRightSequence {
    public static void main(String[] args) {
        int n = 5;
        int openCnt = 0;
        int closeCnt = 0;
        String result = "";
        genBracketSequences(n, openCnt, closeCnt, result);
    }

    private static Integer cnt = 0;

    private static void genBracketSequences(int n, int openCnt, int closeCnt, String result) {
        if ((openCnt + closeCnt) >= 2 * n) {
            System.out.println(cnt++ + ". " + result);
            return;
        }
        if (openCnt < n) {
            genBracketSequences(n, openCnt + 1, closeCnt, result.concat("("));
        }
        if (openCnt > closeCnt) {
            genBracketSequences(n, openCnt, closeCnt + 1, result.concat(")"));
        }
    }
}
