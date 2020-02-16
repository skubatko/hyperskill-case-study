package ru.skubatko.dev.hyperskill.case5697;

import java.util.Scanner;

public class Case5697 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();
        System.out.println(editDistance(s, t));
    }

    static int editDistance(String s1, String s2) {
        if (s1.isEmpty()){
            return s2.length();
        }

        if (s2.isEmpty()){
            return s1.length();
        }

        String s;
        String t;
        if (s1.length() < s2.length()) {
            s = s1;
            t = s2;
        } else {
            s = s2;
            t = s1;
        }

        int[] d = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            int match = match(s.charAt(0), t.charAt(0));
            d[i] = i + match;
        }

        for (int v = 0; v < t.length(); v++) {
            int prevSubCost = v;
            for (int h = 0; h < s.length(); h++) {
                int insCost = d[h] + 1;
                int delCost = h == 0 ? v + 2 : d[h - 1] + 1;
                int subCost = prevSubCost + match(s.charAt(h), t.charAt(v));
                prevSubCost = d[h];

                d[h] = Math.min(Math.min(insCost, delCost), subCost);
            }
        }

        return d[s.length() - 1];
    }

    private static int match(char a, char b) {
        return (a == b) ? 0 : 1;
    }
}
