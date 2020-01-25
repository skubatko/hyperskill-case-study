package ru.skubatko.dev.hyperskill.case3655;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Case3655 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(proceed(s1, s2));
    }

    static int proceed(String s1, String s2) {
        if (s1.isEmpty()) {
            return s2.length();
        }
        if (s2.isEmpty()) {
            return s1.length();
        }

        Map<Character, Integer> map1 = parse(s1.toLowerCase());
        Map<Character, Integer> map2 = parse(s2.toLowerCase());

        Set<Character> set = new HashSet<>(map1.keySet());
        for (Character c : set) {
            if (map2.containsKey(c)) {
                Integer num1 = map1.get(c);
                Integer num2 = map2.get(c);
                if (num1.equals(num2)) {
                    map1.remove(c);
                    map2.remove(c);
                } else if (num1.compareTo(num2) > 0) {
                    map2.remove(c);
                    map1.put(c, num1 - num2);
                } else {
                    map1.remove(c);
                    map2.put(c, num2 - num1);
                }
            }
        }

        Map<Character, Integer> result = new HashMap<>(map1);
        result.putAll(map2);
        return result.values().stream()
                       .reduce(Integer::sum)
                       .orElse(0);
    }

    private static Map<Character, Integer> parse(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer num = map.putIfAbsent(c, 1);
            if (num != null) {
                map.put(c, num + 1);
            }
        }

        return map;
    }
}
