package ru.skubatko.dev.hyperskill.case5695;

import java.util.Scanner;

public class Case5695 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();

        System.out.println(editDistance(s, t));
    }

    public static int editDistance(String s, String t) {
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

                // Substitution = Deletion + Insertion
                int subCost = d[i - 1][j - 1] + match(s.charAt(sPos), t.charAt(tPos)) * 2;
                d[i][j] = Math.min(Math.min(insCost, delCost), subCost);
            }
        }

        return d[s.length()][t.length()];
    }

    private static int match(char a, char b) {
        return (a == b) ? 0 : 1;
    }
}
