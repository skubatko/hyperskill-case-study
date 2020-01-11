package ru.skubatko.dev.hyperskill.case5730;

import java.util.Arrays;
import java.util.Scanner;

public class Case5730 {

    private static int[] prefixFunction(String str) {
        int[] prefixFunc = new int[str.length()];
        for (int i = 1; i < str.length(); i++) {
            int j = prefixFunc[i - 1];
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = prefixFunc[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j += 1;
            }
            prefixFunc[i] = j;
        }
        return prefixFunc;
    }

    private static int numberOfSubstring(String s, char c) {
        String t = new StringBuilder(s).append(c).reverse().toString();
        int prefixMax = Arrays.stream(prefixFunction(t)).max().orElse(0);
        return s.length() + 1 - prefixMax;
    }

    public static void main(String[] args) {
        // https://e-maxx.ru/algo/prefix_function can help
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int result = 2;
        for (int i = 1; i < s.length(); i++) {
            result += numberOfSubstring(s.substring(0, i), s.toCharArray()[i]);
        }
        System.out.println(result);
    }
}