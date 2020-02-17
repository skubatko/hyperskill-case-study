package ru.skubatko.dev.hyperskill.case5704;

import java.util.Scanner;

public class Case5704 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();
        String u = sc.nextLine();

        Alignment result = multipleSequenceAlignment(s, t, u);

        System.out.println(result.editDistance);
        System.out.println(result.s);
        System.out.println(result.t);
        System.out.println(result.u);
    }

    static Alignment multipleSequenceAlignment(String s, String t, String u) {
        int insDelWeight = 2;
        int subWeight = 1;

        int[][][] d = new int[s.length() + 1][t.length() + 1][u.length() + 1];

        for (int i = 0; i < s.length() + 1; i++) {
            d[i][0][0] = i;
        }

        for (int j = 0; j < t.length() + 1; j++) {
            d[0][j][0] = j;
        }

        for (int k = 0; k < u.length() + 1; k++) {
            d[0][0][k] = k;
        }

        int insCost;
        int delCost;
        int subCost;
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                for (int k = 1; k < u.length() + 1; k++) {
                    insCost = d[i][j - 1][k] + insDelWeight;
                    delCost = d[i - 1][j][k] + insDelWeight;
                    subCost = d[i - 1][j - 1][k] + match(s.charAt(i - 1), t.charAt(j - 1)) * subWeight;
                    int pairK = Math.min(Math.min(insCost, delCost), subCost);

                    insCost = d[i][j][k - 1] + insDelWeight;
                    delCost = d[i][j - 1][k] + insDelWeight;
                    subCost = d[i][j - 1][k - 1] + match(t.charAt(j - 1), u.charAt(k - 1)) * subWeight;
                    int pairI = Math.min(Math.min(insCost, delCost), subCost);

                    insCost = d[i][j][k - 1] + insDelWeight;
                    delCost = d[i - 1][j][k] + insDelWeight;
                    subCost = d[i - 1][j][k - 1] + match(s.charAt(i - 1), u.charAt(k - 1)) * subWeight;
                    int pairJ = Math.min(Math.min(insCost, delCost), subCost);

                    int trio = d[i - 1][j - 1][k - 1]
                            + match(s.charAt(i - 1), t.charAt(j - 1)) * subWeight
                            + match(t.charAt(j - 1), u.charAt(k - 1)) * subWeight
                            + match(u.charAt(k - 1), s.charAt(i - 1)) * subWeight;

                    d[i][j][k] = Math.min(Math.min(Math.min(pairI, pairJ), pairK), trio);
                }
            }
        }

        StringBuilder ssBuilder = new StringBuilder();
        StringBuilder ttBuilder = new StringBuilder();
        StringBuilder uuBuilder = new StringBuilder();
        int i = s.length();
        int j = t.length();
        int k = u.length();

        while (i > 0 || j > 0 || k > 0) {
            if (i > 0 && j > 0 && k > 0 && d[i][j][k] == d[i - 1][j - 1][k - 1]
                    + match(s.charAt(i - 1), t.charAt(j - 1)) * subWeight
                    + match(t.charAt(j - 1), u.charAt(k - 1)) * subWeight
                    + match(u.charAt(k - 1), s.charAt(i - 1)) * subWeight) {
                ssBuilder.append(s.charAt(i - 1));
                ttBuilder.append(t.charAt(j - 1));
                uuBuilder.append(t.charAt(k - 1));
                i -= 1;
                j -= 1;
                k -= 1;
            } else if (j > 0 && d[i][j][k] == d[i][j - 1][k] + insDelWeight) {
                ssBuilder.append("-");
                ttBuilder.append(t.charAt(j - 1));
                uuBuilder.append("-");
                j -= 1;
            } else if (i > 0 && d[i][j][k] == d[i - 1][j][k] + insDelWeight) {
                ssBuilder.append(s.charAt(i - 1));
                ttBuilder.append("-");
                uuBuilder.append("-");
                j -= 1;
            } else if (i > 0 && j > 0
                    && d[i][j][k] == d[i - 1][j - 1][k] + match(s.charAt(i - 1), t.charAt(j - 1)) * subWeight) {
                ssBuilder.append(s.charAt(i - 1));
                ttBuilder.append(t.charAt(j - 1));
                i -= 1;
                j -= 1;
            } else if (k > 0 && d[i][j][k] == d[i][j][k - 1] + insDelWeight) {
                ssBuilder.append("-");
                ttBuilder.append("-");
                uuBuilder.append(u.charAt(j - 1));
                j -= 1;
            } else if (j > 0 && k > 0
                    && d[i][j][k] == d[i][j - 1][k - 1] + match(t.charAt(j - 1), u.charAt(k - 1)) * subWeight) {
                ttBuilder.append(t.charAt(j - 1));
                uuBuilder.append(u.charAt(k - 1));
                j -= 1;
                k -= 1;
            } else if (k > 0 && i > 0
                    && d[i][j][k] == d[i - 1][j][k - 1] + match(u.charAt(k - 1), s.charAt(i - 1)) * subWeight) {
                uuBuilder.append(u.charAt(k - 1));
                ssBuilder.append(s.charAt(i - 1));
                i -= 1;
                j -= 1;
            }
        }

        String ss = ssBuilder.reverse().toString();
        String tt = ttBuilder.reverse().toString();
        String uu = uuBuilder.reverse().toString();

        return new Alignment(d[s.length()][t.length()][u.length()], ss, tt, uu);
    }

    private static int match(char a, char b) {
        return (a == b) ? 0 : 1;
    }

    static class Alignment {
        int editDistance;
        String s;
        String t;
        String u;

        public Alignment(int editDistance, String s, String t, String u) {
            this.editDistance = editDistance;
            this.s = s;
            this.t = t;
            this.u = u;
        }
    }
}
