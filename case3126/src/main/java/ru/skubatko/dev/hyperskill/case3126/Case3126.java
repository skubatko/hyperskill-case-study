package ru.skubatko.dev.hyperskill.case3126;

import java.util.Scanner;

public class Case3126 {
    // can help https://introcs.cs.princeton.edu/java/23recursion/Partition.java.html

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        proceed(n);
    }

    private static void proceed(int n) {
        proceed(n, n, "");
    }

    private static void proceed(int n, int m, String out) {
        if (n == 0) {
            System.out.println(out);
        }
        for (int i = 1; i <= Math.min(n, m); i++) {
            proceed(n - i, i, out + " " + i);
        }
    }
}
