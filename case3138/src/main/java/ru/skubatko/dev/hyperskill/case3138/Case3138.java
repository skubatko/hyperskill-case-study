package ru.skubatko.dev.hyperskill.case3138;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Case3138 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        int value = sc.nextInt();

        System.out.println(
                Arrays.stream(proceed(array, value))
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    static int[] proceed(int[] array, int value) {
        int[] notFound = {-1};

        if (array.length == 0) {
            return notFound;
        }

        int currentRight = -1;
        int blockSize = (int) Math.sqrt(array.length);
        int lastRight = array.length - 1;
        while (currentRight < lastRight) {
            currentRight = Math.min(lastRight, currentRight + blockSize);

            if (array[currentRight] >= value) {
                break;
            }
        }

        if (currentRight == lastRight) {
            if (value <= array[currentRight]) {
                int lastBlockSize = array.length % blockSize;
                if (lastBlockSize != 0) {
                    blockSize = lastBlockSize;
                }
            } else {
                return notFound;
            }
        }

        return new int[]{currentRight - blockSize + 1, currentRight};
    }
}
