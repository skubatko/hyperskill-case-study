package ru.skubatko.dev.hyperskill.case5722.case7259;

import java.util.Arrays;

public class Case7259 {

    public static void main(String[] args) {
        int[] a = {3, 0, 3, 9, 2, 1};

        int r = 7;
        for (int i = 0; i < a.length; i++) {
            int x = a[i];
            x = x * x - x;
            r += x;
        }
        System.out.println(r);

        r = 13;
        r += a[a[0]];
        r -= a[a[a.length - 1]];
        System.out.println(r);

        r = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < a.length) {
                r += a[a[i]];
            }
        }
        System.out.println(r);

        a = new int[]{9, 8, 3, 1, 5, 4};
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                a[i] += 1;
            } else if (a[i] < a.length) {
                a[i] += a[a[i]];
            }
        }
        System.out.println(Arrays.toString(a));

        a = new int[]{4, 0, 9, 2, 1};
        int[] b = {6, 3, 2, 9, 0};

        for (int i = 0; i < a.length; i++) {
            if (i % 2 == 0) {
                a[i] -= b[i];
            } else {
                b[i] -= a[i];
            }

            if (a[i] % 2 == 0) {
                a[i] += 1;
                b[i] += 1;
            }
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
