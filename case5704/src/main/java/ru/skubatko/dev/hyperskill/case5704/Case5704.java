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
        // https://www.cise.ufl.edu/~sahni/papers/triple_alignment.pdf can help

        int[][][] d = new int[s.length() + 1][t.length() + 1][u.length() + 1];

        initialConditions(s, t, u, d);

        constructMatrix(s, t, u, d);

        return getAlignment(s, t, u, d);
    }

    private static void initialConditions(String s, String t, String u, int[][][] d) {
        int insDelCost = 2;

        for (int i = 0; i < s.length() + 1; i++) {
            d[i][0][0] = 2 * i * insDelCost;
        }
        for (int j = 0; j < t.length() + 1; j++) {
            d[0][j][0] = 2 * j * insDelCost;
        }
        for (int k = 0; k < u.length() + 1; k++) {
            d[0][0][k] = 2 * k * insDelCost;
        }

        for (int i = 1, posS = 0; posS < s.length(); i++, posS++) {
            for (int j = 1, posT = 0; posT < t.length(); j++, posT++) {
                int align1 = d[i - 1][j - 1][0] + cost(s.charAt(posS), t.charAt(posT), '-');
                int align2 = d[i - 1][j][0] + cost(s.charAt(posS), '-', '-');
                int align3 = d[i][j - 1][0] + cost('-', t.charAt(posT), '-');
                d[i][j][0] = Math.min(Math.min(align1, align2), align3);
            }
        }
        for (int i = 1, posS = 0; posS < s.length(); i++, posS++) {
            for (int k = 1, posU = 0; posU < u.length(); k++, posU++) {
                int align1 = d[i - 1][0][k - 1] + cost(s.charAt(posS), '-', u.charAt(posU));
                int align2 = d[i - 1][0][k] + cost(s.charAt(posS), '-', '-');
                int align3 = d[i][0][k - 1] + cost('-', '-', u.charAt(posU));
                d[i][0][k] = Math.min(Math.min(align1, align2), align3);
            }
        }
        for (int j = 1, posT = 0; posT < t.length(); j++, posT++) {
            for (int k = 1, posU = 0; posU < u.length(); k++, posU++) {
                int align1 = d[0][j - 1][k - 1] + cost('-', t.charAt(posT), u.charAt(posU));
                int align2 = d[0][j - 1][k] + cost('-', t.charAt(posT), '-');
                int align3 = d[0][j][k - 1] + cost('-', '-', u.charAt(posU));
                d[0][j][k] = Math.min(Math.min(align1, align2), align3);
            }
        }
    }

    private static void constructMatrix(String s, String t, String u, int[][][] d) {
        for (int i = 1, posS = 0; posS < s.length(); i++, posS++) {
            for (int j = 1, posT = 0; posT < t.length(); j++, posT++) {
                for (int k = 1, posU = 0; posU < u.length(); k++, posU++) {
                    int align1 = d[i - 1][j - 1][k - 1] + cost(s.charAt(posS), t.charAt(posT), u.charAt(posU));
                    int align2 = d[i - 1][j - 1][k] + cost(s.charAt(posS), t.charAt(posT), '-');
                    int align3 = d[i - 1][j][k - 1] + cost(s.charAt(posS), '-', u.charAt(posU));
                    int align4 = d[i][j - 1][k - 1] + cost('-', t.charAt(posT), u.charAt(posU));
                    int align5 = d[i - 1][j][k] + cost(s.charAt(posS), '-', '-');
                    int align6 = d[i][j - 1][k] + cost('-', t.charAt(posT), '-');
                    int align7 = d[i][j][k - 1] + cost('-', '-', u.charAt(posU));

                    int align123 = Math.min(Math.min(align1, align2), align3);
                    int align456 = Math.min(Math.min(align4, align5), align6);
                    d[i][j][k] = Math.min(Math.min(align123, align456), align7);
                }
            }
        }
    }

    private static Alignment getAlignment(String s, String t, String u, int[][][] d) {
        StringBuilder ssBuilder = new StringBuilder();
        StringBuilder ttBuilder = new StringBuilder();
        StringBuilder uuBuilder = new StringBuilder();
        int i = s.length();
        int j = t.length();
        int k = u.length();
        int posS = s.length() - 1;
        int posT = t.length() - 1;
        int posU = u.length() - 1;

        while (i > 0 || j > 0 || k > 0) {
            int align1 = (i > 0 && j > 0 && k > 0)
                         ? d[i - 1][j - 1][k - 1] + cost(s.charAt(posS), t.charAt(posT), u.charAt(posU))
                         : 0;
            int align2 = (i > 0 && j > 0)
                         ? d[i - 1][j - 1][k] + cost(s.charAt(posS), t.charAt(posT), '-')
                         : 0;
            int align3 = (i > 0 && k > 0)
                         ? d[i - 1][j][k - 1] + cost(s.charAt(posS), '-', u.charAt(posU))
                         : 0;
            int align4 = (j > 0 && k > 0)
                         ? d[i][j - 1][k - 1] + cost('-', t.charAt(posT), u.charAt(posU))
                         : 0;
            int align5 = (i > 0)
                         ? d[i - 1][j][k] + cost(s.charAt(posS), '-', '-')
                         : 0;
            int align6 = (j > 0)
                         ? d[i][j - 1][k] + cost('-', t.charAt(posT), '-')
                         : 0;
            int align7 = (k > 0)
                         ? d[i][j][k - 1] + cost('-', '-', u.charAt(posU))
                         : 0;

            if (d[i][j][k] == align1) {
                ssBuilder.append(s.charAt(posS));
                ttBuilder.append(t.charAt(posT));
                uuBuilder.append(u.charAt(posU));
                i--;
                j--;
                k--;
                posS--;
                posT--;
                posU--;
                continue;
            }

            if (d[i][j][k] == align2) {
                ssBuilder.append(s.charAt(posS));
                ttBuilder.append(t.charAt(posT));
                uuBuilder.append('-');
                i--;
                j--;
                posS--;
                posT--;
                continue;
            }

            if (d[i][j][k] == align3) {
                ssBuilder.append(s.charAt(posS));
                ttBuilder.append('-');
                uuBuilder.append(u.charAt(posU));
                i--;
                k--;
                posS--;
                posU--;
                continue;
            }

            if (d[i][j][k] == align4) {
                ssBuilder.append('-');
                ttBuilder.append(t.charAt(posT));
                uuBuilder.append(u.charAt(posU));
                j--;
                k--;
                posT--;
                posU--;
                continue;
            }

            if (d[i][j][k] == align5) {
                ssBuilder.append(s.charAt(posS));
                ttBuilder.append('-');
                uuBuilder.append('-');
                i--;
                posS--;
                continue;
            }

            if (d[i][j][k] == align6) {
                ssBuilder.append('-');
                ttBuilder.append(t.charAt(posT));
                uuBuilder.append('-');
                j--;
                posT--;
                continue;
            }

            if (d[i][j][k] == align7) {
                ssBuilder.append('-');
                ttBuilder.append('-');
                uuBuilder.append(u.charAt(posU));
                k--;
                posU--;
            }
        }

        String ss = ssBuilder.reverse().toString();
        String tt = ttBuilder.reverse().toString();
        String uu = uuBuilder.reverse().toString();

        return new Alignment(d[s.length()][t.length()][u.length()], ss, tt, uu);
    }

    static int cost(char c1, char c2, char c3) {
        int penalty = 0;

        penalty += costPair(c1, c2);
        penalty += costPair(c2, c3);
        penalty += costPair(c1, c3);

        return penalty;
    }

    private static int costPair(char c1, char c2) {
        int insDelCost = 2;
        int subCost = 1;

        if (c1 == c2) {
            return 0;
        }

        if (c1 == '-' || c2 == '-') {
            return insDelCost;
        }

        return subCost;
    }

    static class Alignment {
        int editDistance;
        String s;
        String t;
        String u;

        Alignment(int editDistance, String s, String t, String u) {
            this.editDistance = editDistance;
            this.s = s;
            this.t = t;
            this.u = u;
        }
    }
}
