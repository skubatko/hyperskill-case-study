package ru.skubatko.dev.hyperskill.case1931;

import java.util.Scanner;

public class Case1931 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] arr = getSpiralMatrix(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int[][] getSpiralMatrix(int n) {
        int[][] arr = new int[n][n];

        int value = 1;
        for (int i = 0; i < n / 2; i++) {
            for (int up = i; up < n - 1 - i; up++) {
                arr[i][up] = value++;
            }
            for (int right = i; right < n - 1 - i; right++) {
                arr[right][n - i - 1] = value++;
            }
            for (int down = i; down < n - 1 - i; down++) {
                arr[n - i - 1][n - down - 1] = value++;
            }
            for (int left = i; left < n - 1 - i; left++) {
                arr[n - left - 1][i] = value++;
            }
        }

        if (n % 2 == 1) {
            arr[n / 2][n / 2] = value;
        }

        return arr;
    }
}
