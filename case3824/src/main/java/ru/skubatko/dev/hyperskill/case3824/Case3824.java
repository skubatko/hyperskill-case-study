package ru.skubatko.dev.hyperskill.case3824;

import java.util.Scanner;

public class Case3824 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        int value = sc.nextInt();
        System.out.println(proceed(array, value));

    }

    static int proceed(int[] array, int value) {
        int n = array.length;
        if (array[0] == value) {
            return 1;
        }

        int blockSize = (int) Math.sqrt(n);
        int blocks = n / blockSize;
        if (n % blockSize != 0) {
            blocks++;
        }

        int lastRight = n - 1;
        int currentRight = 0;
        for (int i = 0; i < blocks; i++) {
            currentRight = Math.min(lastRight, currentRight + blockSize);
            if (array[currentRight] >= value) {
                for (int j = 0; j < blockSize; j++) {
                    if (array[currentRight - j] <= value) {
                        return i + j + 2;
                    }
                }

                if ((currentRight == lastRight) && value > array[currentRight]) {
                    return blocks + 1;
                }

                return i + blockSize + 1;
            }
        }

        return blocks + 1;
    }
}
