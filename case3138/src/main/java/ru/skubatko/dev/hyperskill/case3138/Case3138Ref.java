package ru.skubatko.dev.hyperskill.case3138;

import java.util.Scanner;

public class Case3138Ref {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        final int target = scanner.nextInt();

        int left = 0;
        int right = 0;

        int blockSize = (int) Math.sqrt(array.length);

        while (right < array.length - 1) {
            right = Math.min(array.length, left + blockSize) - 1;

            if (array[right] >= target) {
                System.out.println(left + " " + right);
                return;
            }

            left = right + 1;
        }

        System.out.println("-1");
    }
}
