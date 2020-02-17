package ru.skubatko.dev.hyperskill.case5703;

import java.util.Scanner;

public class Case5703 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();

        Alignment result = proceed(s, t);

        System.out.println(result.editDistance);
        System.out.println(result.source);
        System.out.println(result.target);
    }

    static Alignment proceed(String s, String t) {
        if (s.isEmpty()) {
            return new Alignment(t.length(), t, t);
        }

        if (s.length() > t.length()) {
            return new Alignment(s.length(), s, s);
        }

        return approximateMatching(s, t);
    }

    static Alignment approximateMatching(String p, String t) {
        // https://www.coursera.org/lecture/dna-sequencing/lecture-a-new-solution-to-approximate-matching-ZkfNt
        // can help

        int insDelWeight = 3;
        int subWeight = 5;

        int[][] d = new int[p.length() + 1][t.length() + 1];

        for (int i = 0; i < p.length() + 1; i++) {
            d[i][0] = i * insDelWeight;
        }

        for (int j = 0; j < t.length() + 1; j++) {
            d[0][j] = 0;
        }

        for (int i = 1; i < p.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                int insCost = d[i][j - 1] + insDelWeight;
                int delCost = d[i - 1][j] + insDelWeight;
                int subCost = d[i - 1][j - 1] + match(p.charAt(i - 1), t.charAt(j - 1)) * subWeight;
                d[i][j] = Math.min(Math.min(insCost, delCost), subCost);
            }
        }

        int min = Integer.MAX_VALUE;
        int minPos = t.length();
        for (int i = 0; i < t.length() + 1; i++) {
            int cost = d[p.length()][i];
            if (cost < min) {
                min = cost;
                minPos = i;
            }
        }

        StringBuilder ssBuilder = new StringBuilder();
        StringBuilder ttBuilder = new StringBuilder();
        int i = p.length();
        int j = minPos;

        while (i > 0) {
            if (d[i][j] == d[i][j - 1] + insDelWeight) {
                ssBuilder.append("-");
                ttBuilder.append(t.charAt(j - 1));
                j -= 1;
            } else if (d[i][j] == d[i - 1][j] + insDelWeight) {
                ssBuilder.append(p.charAt(i - 1));
                ttBuilder.append("-");
                i -= 1;
            } else if (d[i][j] == (d[i - 1][j - 1] + match(p.charAt(i - 1), t.charAt(j - 1)) * subWeight)) {
                ssBuilder.append(p.charAt(i - 1));
                ttBuilder.append(t.charAt(j - 1));
                i -= 1;
                j -= 1;
            }
        }

        String ss = ssBuilder.reverse().toString();
        String tt = ttBuilder.reverse().toString();

        return new Alignment(min, ss, tt);
    }

    private static int match(char a, char b) {
        return (a == b) ? 0 : 1;
    }

    static class Alignment {
        int editDistance;
        String source;
        String target;

        Alignment(int editDist, String source, String target) {
            this.editDistance = editDist;
            this.source = source;
            this.target = target;
        }
    }
}
