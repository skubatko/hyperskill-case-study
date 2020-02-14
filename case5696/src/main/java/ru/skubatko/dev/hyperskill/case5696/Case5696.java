package ru.skubatko.dev.hyperskill.case5696;

import java.util.Scanner;

public class Case5696 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = sc.nextInt();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.nextLine();
        }

        Result result = proceed(s, words);
        System.out.println(result.ed);
        System.out.println(result.t);
    }

    static Result proceed(String s, String[] words) {
        String t = "";
        int ed = Integer.MAX_VALUE;

        for (String word : words) {
            int wordEd = editDistance(s, word);
            if (wordEd < ed) {
                ed = wordEd;
                t = word;
            }
        }

        return new Result(ed, t);
    }

    private static int editDistance(String s, String t) {
        String scoringString = "asdbnm";
        int[][] scoringMatrix = getScoringMatrix();

        int[][] d = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i < s.length() + 1; i++) {
            d[i][0] = i;
        }

        for (int j = 0; j < t.length() + 1; j++) {
            d[0][j] = j;
        }

        for (int i = 1, sPos = 0; sPos < s.length(); i++, sPos++) {
            for (int j = 1, tPos = 0; tPos < t.length(); j++, tPos++) {
                int insCost = d[i][j - 1] + 1;
                int delCost = d[i - 1][j] + 1;

                char sChar = s.charAt(sPos);
                char tChar = t.charAt(tPos);
                int scorePosS = scoringString.indexOf(sChar);
                int scorePosT = scoringString.indexOf(tChar);
                int score = scoringMatrix[scorePosS][scorePosT];
                int subCost = d[i - 1][j - 1] + match(sChar, tChar) * score;

                d[i][j] = Math.min(Math.min(insCost, delCost), subCost);
            }
        }

        return d[s.length()][t.length()];
    }

    private static int[][] getScoringMatrix() {
        return new int[][]{
                {0, 1, 2, 5, 6, 7},
                {1, 0, 1, 5, 6, 7},
                {2, 1, 0, 5, 6, 7},
                {5, 6, 7, 0, 1, 2},
                {5, 6, 7, 1, 0, 1},
                {5, 6, 7, 2, 1, 0}
        };
    }

    private static int match(char a, char b) {
        return (a == b) ? 0 : 1;
    }

    static class Result {
        int ed;
        String t;

        public Result(int ed, String t) {
            this.ed = ed;
            this.t = t;
        }
    }
}
