package ru.skubatko.dev.hyperskill.case5731;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Case5731 {

    private static Map<String, int[]> prefixCache = new HashMap<>();

    private static int[] prefixFunction(String str) {
        if (prefixCache.containsKey(str)) {
            return prefixCache.get(str);
        }

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
        prefixCache.put(str, prefixFunc);
        return prefixFunc;
    }

    private static List<Integer> getBaseSubStrIdxList(String p, String t) {
        String str = p + "@" + t;
        int[] prefixFunc = prefixFunction(str);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < prefixFunc.length; i++) {
            if (prefixFunc[i] == p.length()) {
                result.add(i);
            }
        }
        return result;
    }

    private static int getNumberOfOccurrences(String[] p, String[] t, int start) {
        int pSize = p[0].length();
        List<Integer> resultIdxList = getBaseSubStrIdxList(p[0], t[start]);
        for (int i = 1; i < p.length; i++) {
            String str = p[i] + "@" + t[start + i];
            int[] prefixFunc = prefixFunction(str);
            resultIdxList.removeIf(idx -> prefixFunc[idx] != pSize);
            if (resultIdxList.isEmpty()) {
                return 0;
            }
        }
        return resultIdxList.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int px = sc.nextInt();
        int py = sc.nextInt();
        String[] pMatrix = new String[px];
        for (int i = 0; i < px; i++) {
            pMatrix[i] = sc.next();
        }

        int tx = sc.nextInt();
        int ty = sc.nextInt();
        String[] tMatrix = new String[tx];
        for (int i = 0; i < tx; i++) {
            tMatrix[i] = sc.next();
        }

        if (tx < px || ty < py) {
            System.out.println(0);
            return;
        }

        int dx = tx - px + 1;
        int result = 0;
        for (int i = 0; i < dx; i++) {
            result += getNumberOfOccurrences(pMatrix, tMatrix, i);
        }
        System.out.println(result);
    }
}
