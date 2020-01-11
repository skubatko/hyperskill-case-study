package ru.skubatko.dev.hyperskill.case5729;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Case5729 {

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String p = sc.nextLine();
        String t = sc.nextLine();

        if (t.isEmpty()) {
            System.out.println(0);
            System.out.println();
            return;
        }

        if (p.isEmpty()) {
            System.out.println(1);
            System.out.println(0);
            return;
        }

        String str = p + "@" + t;
        int[] prefixFunc = prefixFunction(str);
        List<Integer> result = new ArrayList<>();
        int lastFoundIndex = -p.length();
        for (int i = 0; i < prefixFunc.length; i++) {
            if (prefixFunc[i] == p.length() && p.length() <= (i - lastFoundIndex)) {
                result.add(i - p.length() * 2);
                lastFoundIndex = i;
            }
        }
        System.out.println(result.size());
        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
