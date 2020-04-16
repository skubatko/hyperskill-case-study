package ru.skubatko.dev.hyperskill.case3658;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Case3658 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        List<String> result = proceed(s);
        result.forEach(System.out::println);
    }

    static List<String> proceed(String s) {
        List<String> result = new ArrayList<>();
        LinkedList<String> tokens = new LinkedList<>();

        for (int i = 0; i < s.length(); ) {
            StringBuilder tokenBuilder = new StringBuilder();
            if (s.charAt(i) == '<') {
                while (s.charAt(i) != '>') {
                    tokenBuilder.append(s.charAt(i++));
                }
                tokenBuilder.append(s.charAt(i++));
            } else {
                while (s.charAt(i) != '<') {
                    tokenBuilder.append(s.charAt(i++));
                }
            }
            String token = tokenBuilder.toString();

            if (token.contains("/")) {
                String toFind = tokenBuilder.deleteCharAt(tokenBuilder.indexOf("/")).toString();
                Iterator<String> iterator = tokens.descendingIterator();
                StringBuilder resultItem = new StringBuilder();
                while (iterator.hasNext()) {
                    String item = iterator.next();
                    if (item.equals(toFind)) {
                        break;
                    }
                    resultItem.insert(0, item);
                }
                result.add(resultItem.toString());
            }

            tokens.add(token);
        }

        return result;
    }
}
