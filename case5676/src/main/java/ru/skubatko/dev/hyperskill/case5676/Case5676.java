package ru.skubatko.dev.hyperskill.case5676;

import java.util.Scanner;

public class Case5676 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        printPreOrder(0, 0, n);
    }

    static void printPreOrder(int node, int level, int n) {
        System.out.print(node + " ");

        if (level == n) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            printPreOrder(3 * node + 1 + i, level + 1, n);
        }
    }
}
