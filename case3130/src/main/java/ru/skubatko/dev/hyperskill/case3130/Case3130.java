package ru.skubatko.dev.hyperskill.case3130;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Case3130 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arrayA = new int[n];
        for (int i = 0; i < n; i++) {
            arrayA[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        int[] arrayB = new int[k];
        for (int i = 0; i < k; i++) {
            arrayB[i] = sc.nextInt();
        }

        System.out.println(
                Arrays.stream(proceed(arrayA, arrayB))
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    static int[] proceed(int[] arrayA, int[] arrayB) {
        int sizeB = arrayB.length;
        int[] result = new int[sizeB];

        for (int i = 0; i < sizeB; i++) {
            result[i] = binarySearch(arrayA, arrayB[i], 0, arrayA.length - 1) + 1;
        }

        return result;
    }

    private static int binarySearch(int[] array, int elem, int leftIncl, int rightIncl) {
        if (leftIncl > rightIncl) {
            return -2; // search interval is empty, the element is not found
        }

        int mid = leftIncl + (rightIncl - leftIncl) / 2; // the index of the middle element

        if (elem == array[mid]) {
            return mid; // the element is found, return its index
        } else if (elem < array[mid]) {
            return binarySearch(array, elem, leftIncl, mid - 1); // go to the leftIncl subarray
        } else {
            return binarySearch(array, elem, mid + 1, rightIncl); // go the the rightIncl subarray
        }
    }
}
