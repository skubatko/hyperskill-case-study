package ru.skubatko.dev.hyperskill.case3825;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Case3825 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(
                Arrays.stream(proceed(n))
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    static int[] proceed(int n) {
        int[] array = new int[n];

        int jumpSize = (int) Math.sqrt(n);
        int lastRight = n - 1;

        int blocks = lastRight / jumpSize;
        int tailSize = lastRight % jumpSize;
        if (tailSize != 0) {
            blocks++;
        }

        int currentRight = 0;
        array[currentRight] = 1;
        for (int i = 0; i < blocks; i++) {
            currentRight = Math.min(lastRight, currentRight + jumpSize);
            array[currentRight] = i + 2;

            if (currentRight == lastRight && tailSize != 0) {
                jumpSize = tailSize;
            }

            for (int j = 1; j < jumpSize; j++) {
                array[currentRight - j] = array[currentRight] + j;
            }
        }

        return array;
    }
}
