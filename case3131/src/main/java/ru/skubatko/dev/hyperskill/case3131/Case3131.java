package ru.skubatko.dev.hyperskill.case3131;

import java.util.Scanner;

public class Case3131 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(proceed(array));
    }

    static boolean proceed(int[] array) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            boolean positive = binarySearch(array, i, i, i) != -1;
            boolean negative = binarySearch(array, -i, i, i) != -1;
            if (positive || negative){
                return true;
            }
        }
        return false;
    }

    private static int binarySearch(int[] array, int elem, int leftIncl, int rightIncl) {
        if (leftIncl > rightIncl) {
            return -1; // search interval is empty, the element is not found
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
